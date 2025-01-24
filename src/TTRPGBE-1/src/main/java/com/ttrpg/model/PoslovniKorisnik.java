package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class PoslovniKorisnik extends Korisnik {
    
    @OneToOne(mappedBy = "business_user")
    private OrgProfil company;

    @Override
    public String toString() {
        return
            "Business User { "
            + "id: '" + super.getUserId() + "', "
            + "username: '" + super.getUsername() + "', "
            + "password: '" + super.getPassword() + "'}";
    }
}
