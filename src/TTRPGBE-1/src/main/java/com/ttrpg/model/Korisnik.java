package com.ttrpg.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "Korisnik")  // The name of the table in the database
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique=true)
    private String username;
    private String password;
    private String role;
    private String email;
    private String organisationalName;
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		role = role;
	}

	// Constructors, getters, and setters
    public Korisnik() {}

    public Korisnik(String username,String email, String password, String role, String organisationalName) {
        
        this.username = username;
        this.password = password;
        this.role=role;
        this.email=email;
        this.organisationalName=organisationalName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

	public String getOrganisationalName() {
		return organisationalName;
	}

	public void setOrganisationalName(String organisationalName) {
		this.organisationalName = organisationalName;
	}
    
    
}
