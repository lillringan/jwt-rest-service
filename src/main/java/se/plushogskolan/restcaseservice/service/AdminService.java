package se.plushogskolan.restcaseservice.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import se.plushogskolan.restcaseservice.exception.UnathorizedException;
import se.plushogskolan.restcaseservice.exception.WebInternalErrorException;
import se.plushogskolan.restcaseservice.model.Admin;
import se.plushogskolan.restcaseservice.repository.AdminRepository;

@Service
public class AdminService {
	
	private final int ITERATIONS = 100;
	private AdminRepository adminRepository;
	
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	public Admin save(String username, String password) {
		Admin admin = createAdmin(username, password);
		try {
			return adminRepository.save(admin);
		} catch(DataAccessException e) {
			throw new WebInternalErrorException("Could not save admin");
		}
	}
	
	public String login(String username, String password) {
		Admin admin = adminRepository.findByUsername(username);
		if(authenticateLogin(admin, password)) {
			String token = generateToken();
			admin.setToken(token);
			adminRepository.save(admin);
			return token;
		}
		else
			throw new UnathorizedException("Invalid login");
	}
	
	public boolean authenticateToken(String token) {
		Admin admin;
		try {
			admin = adminRepository.findByToken(token);
		} catch(DataAccessException e) {
			throw new WebInternalErrorException("Internal error");
		}
		if(admin == null)
			throw new UnathorizedException("Token not found");
		else if(admin.getTimestamp().isAfter(LocalDateTime.now())) {
			throw new UnathorizedException("Token has run out");
		}
		else
			return true;
	}
	
	private Admin createAdmin(String username, String password) {
		byte[] salt = generateSalt(password);
		byte[] hash = generateHash(password, salt);
		return new Admin(hash, username, salt);
	}
	
	private byte[] generateSalt(String password) {
		byte[] bytes = new byte[256-password.length()];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return Base64.getEncoder().encode(bytes);
	}
	
	private byte[] generateHash(String arg, byte[] salt) {
		byte[] hashToReturn = null;
		char[] password = arg.toCharArray();
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, 256);
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			hashToReturn =  factory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return hashToReturn;
	}
	
	//returns null?
	private boolean authenticateLogin(Admin admin, String password) {
		return generateHash(password, admin.getSalt()).equals(admin.getHashedPassword());
	}
	
	private String generateToken() {
		byte[] bytes = new byte[256];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return new String(Base64.getEncoder().encode(bytes));
	}
	
	//No timestamp function must also be saved!!
}
