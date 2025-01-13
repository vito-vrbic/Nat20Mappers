package com.ttrpg.service;

public class UserData2 {
   
    private String username;
    private String email;
    private String password;
    private String role;
    private String organizationName;

   
    public UserData2(String username,String password, String email, String role, String organizationName) {
        this.password=password;
        this.username = username;
        this.email = email;
        this.role = role;
        this.organizationName = organizationName;
    }
  
  
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getOrganizationName() { return organizationName; }


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}

