package se.plushogskolan.restcaseservice.model;

public final class AccessBean {

	private String accessToken;
	
	private String expirationTime;
	
	public AccessBean(String accessToken, String expirationTime){
		this.accessToken = accessToken;
		this.expirationTime = expirationTime;
	}
	
	public String getExpirationTime() {
		return expirationTime;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
}
