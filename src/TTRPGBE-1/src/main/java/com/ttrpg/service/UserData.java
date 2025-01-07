package com.ttrpg.service;

public class UserData {
    private String id;
    private String username;
    private String email;
    private String role;
    private String organizationName;

    // Konstruktor
    public UserData(String id, String username, String email, String role, String organizationName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.organizationName = organizationName;
    }

    // Getteri i setteri
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getOrganizationName() { return organizationName; }
}

