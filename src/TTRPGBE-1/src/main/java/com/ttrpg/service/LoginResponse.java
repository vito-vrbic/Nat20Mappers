package com.ttrpg.service;

public class LoginResponse {

    private String message;
    private String token;
    private UserData userData; 
    public LoginResponse(String message, String token, UserData userData) {
        this.message = message;
        this.token = token;
        this.userData = userData;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public UserData getUserData() { return userData; }
    public void setUserData(UserData userData) { this.userData = userData; }
}
