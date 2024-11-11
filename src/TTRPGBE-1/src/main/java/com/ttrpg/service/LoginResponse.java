package com.ttrpg.service;

import java.util.ArrayList;

public class LoginResponse {

	String message;
	String token;
	UserData ud;
	public LoginResponse(String message, String token, UserData ud) {
		super();
		this.message = message;
		this.token = token;
		this.ud = ud;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserData getUd() {
		return ud;
	}
	public void setUd(UserData ud) {
		this.ud = ud;
	}
	
	
	

}
