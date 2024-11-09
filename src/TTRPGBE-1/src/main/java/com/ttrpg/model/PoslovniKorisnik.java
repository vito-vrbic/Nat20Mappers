package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class PoslovniKorisnik extends Korisnik {
    
    @OneToOne(mappedBy = "business_user")
    private OrgProfil company;

    // Constructors, getters, setters
    public PoslovniKorisnik() {}

    @Override
    public String toString() {
        return "Business User { "
            + "id: '" + super.getUserId() + "', "
            + "username: '" + super.getUsername() + "', "
            + "password: '" + super.getPassword() + "'}";
    }

    public OrgProfil getCompany() {
        return company;
    }

    public void setCompany(OrgProfil company) {
        this.company = company;
    }
}
