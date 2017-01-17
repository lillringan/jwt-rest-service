package se.plushogskolan.restcaseservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import se.plushogskolan.casemanagement.model.AbstractEntity;

@Entity
public class Admin extends AbstractEntity {

	@Column(unique=true)
	private String username;
	
	//hehehe don't hate me if I butchered your code
	@Column(unique=true)
	private byte[] salt;
	
	private byte[] hashedPassword;
	
	private LocalDateTime timestamp;
	
	@Column(unique=true)
	private String token;
	
	protected Admin(){
	}
	
	//TODO should be replaced with byte[] salt
//	public Admin(byte[] password, String username, String salt){
//		this.hashedPassword = password;
//		this.username = username;
//		this.salt = salt;
//	}
	
	public Admin(byte[] password, String username, byte[] salt){
		this.hashedPassword = password;
		this.username = username;
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}
	
	public byte[] getSalt() {
		return salt;
	}
	
	public byte[] getHashedPassword() {
		return hashedPassword;
	}
	
	public LocalDateTime getTimestamp() {
		
		return LocalDateTime.from(timestamp);
	}
	
	public String getToken() {
		return token;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
