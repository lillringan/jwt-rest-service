package se.plushogskolan.restcaseservice.model;

public final class AccessBean {

	private String access_token;
	
	private String expiration_time;
	
	public AccessBean(String accessToken, String expirationTime){
		this.access_token = accessToken;
		this.expiration_time = expirationTime;
	}
	
	public String getExpirationTime() {
		return expiration_time;
	}
	
	public String getAccessToken() {
		return access_token;
	}
	
}
