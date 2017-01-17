package se.plushogskolan.restcaseservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import se.plushogskolan.casemanagement.model.AbstractEntity;

@Entity
public class Admin extends AbstractEntity {

	private String username;
	
	private String salt;
	
	private String hashedPassword;
	
	private LocalDateTime timestamp;
	
	private String token;
	
	protected Admin(){
	}
	
	public Admin(String password, String username){
		this.hashedPassword = password;
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}
	
	public LocalDateTime getTimestamp() {
		
		return LocalDateTime.from(timestamp);
	}
	
	public String getToken() {
		return token;
	}
}
