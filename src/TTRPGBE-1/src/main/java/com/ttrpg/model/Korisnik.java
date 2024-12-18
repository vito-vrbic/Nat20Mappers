package com.ttrpg.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "Korisnik")  // Ime tablice u bazi podataka
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Strategija naslijeđivanja (jedna tablica za sve klase koje naslijeđuju)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)  // Kolona koja označava vrstu naslijeđene klase
public class Korisnik {

    @Id  // Označava primarni ključ
    private int userId;  // ID korisnika

    @Column(unique = true)  // Osigurava da je username jedinstven u bazi
    private String username;  // Korisničko ime

    private String password;  // Lozinka korisnika
    private String role;  // Uloga korisnika (npr. admin, korisnik)
    private String email;  // Email korisnika

    // Getter i Setter za email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter i Setter za role (uloga korisnika)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;  // Postavljanje uloge korisnika
    }

    // Konstruktor bez argumenata
    public Korisnik() {}

    // Konstruktor s parametrima za inicijalizaciju korisničkog objekta
    public Korisnik(int userId, String username, String email, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    // Getter i Setter za userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter i Setter za username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter i Setter za password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
