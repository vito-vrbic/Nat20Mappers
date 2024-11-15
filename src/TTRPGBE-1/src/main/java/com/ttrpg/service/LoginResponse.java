package com.ttrpg.service;

public class LoginResponse {

    private String message;  // Poruka koja se šalje u odgovoru
    private String token;    // Token koji se koristi za autentifikaciju korisnika
    private UserData userData;  // Podaci o korisniku koji su povezani s login odgovorom

    // Konstruktor za inicijalizaciju objekta LoginResponse
    public LoginResponse(String message, String token, UserData userData) {
        this.message = message;  // Postavljanje poruke
        this.token = token;      // Postavljanje tokena
        this.userData = userData;  // Postavljanje podataka o korisniku
    }

    // Getter i setter za poruku
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    // Getter i setter za token
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    // Getter i setter za podatke o korisniku
    public UserData getUserData() { return userData; }
    public void setUserData(UserData userData) { this.userData = userData; }
}
