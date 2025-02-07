package com.ttrpg.model;

import jakarta.persistence.*;

@PrimaryKeyJoinColumn(name = "userId")
@Entity
public class PoslovniKorisnik extends Korisnik {
    
    // OneToOne veza s OrgProfilom, gdje "business_user" označava mappedBy
    @OneToOne(cascade = CascadeType.ALL)
    private OrgProfil company;

    // Konstruktor bez argumenata
    public PoslovniKorisnik() {}

    public PoslovniKorisnik(String username, String password, String email, OrgProfil company) {
        super(username, password, email);
        this.company = company;
    }

    @Override
    public String toString() {
        // Ispis korisničkog objekta s njegovim ID-jem, korisničkim imenom i lozinkom
        return "Business User { "
            + "id: '" + super.getUserId() + "', "
            + "username: '" + super.getUsername() + "', "
            + "password: '" + super.getPassword() + "'}";
    }

    // Getter za OrgProfil (tvrtku)
    public OrgProfil getCompany() {
        return company;
    }

    // Setter za OrgProfil (tvrtku)
    public void setCompany(OrgProfil company) {
        this.company = company;
    }
}
