package se.plushogskolan.restcaseservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import se.plushogskolan.casemanagement.model.AbstractEntity;

@Entity
public class Admin extends AbstractEntity {

	private String username;
	
	private String salt;
	
	private String hashedPassword;
	
	private LocalDate timestamp;
	
	private String token;
	
	protected Admin(){
		
	}
}
