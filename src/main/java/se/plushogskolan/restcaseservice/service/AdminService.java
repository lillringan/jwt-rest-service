package se.plushogskolan.restcaseservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import se.plushogskolan.restcaseservice.exception.WebInternalErrorException;
import se.plushogskolan.restcaseservice.model.Admin;
import se.plushogskolan.restcaseservice.repository.AdminRepository;

@Component
public class AdminService {
	
	private AdminRepository adminRepository;
	
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	public Admin save(String username, String password) {
		Admin admin = createAdmin(username, password);
		try {
			admin = adminRepository.save(admin);
		} catch(DataAccessException e) {
			throw new WebInternalErrorException("Could not save admin");
		}
		return admin;
	}

	private Admin createAdmin(String username, String password) {
		
		return null;
	}
}
