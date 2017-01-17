package se.plushogskolan.restcaseservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import se.plushogskolan.restcaseservice.exception.UnathorizedException;
import se.plushogskolan.restcaseservice.exception.WebInternalErrorException;
import se.plushogskolan.restcaseservice.model.Admin;
import se.plushogskolan.restcaseservice.repository.AdminRepository;

@Service
public class AdminService {
	
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
			String token = newToken();
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
		return true;
	}
	
	
	private Admin createAdmin(String username, String password) {
		//TODO implement
		return null;
	}
	
	private boolean authenticateLogin(Admin admin, String password) {
		//TODO implement
		return false;
	}
	
	private String newToken() {
		//TODO secure.random-ly generate Token
		return null;
	}
	
	private byte[] hash(String string) {
		//TODO hash
		return null;
	}
}
