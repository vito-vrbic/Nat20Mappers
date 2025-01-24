package com.ttrpg.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;
    private String role;
    private String organizationName;

    public RegisterRequestDTO() {}
    public RegisterRequestDTO(String username, String email, String password, String role, String organizationName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.organizationName = organizationName;
    }
    public RegisterRequestDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role=null;
        this.organizationName=null;
    }

}
