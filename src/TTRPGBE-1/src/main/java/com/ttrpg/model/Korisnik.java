package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Korisnik {

    @Id
    private int userId;

    private String username;
    private String password;

    // Constructors, getters, and setters
    public Korisnik() {}

    public Korisnik(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
