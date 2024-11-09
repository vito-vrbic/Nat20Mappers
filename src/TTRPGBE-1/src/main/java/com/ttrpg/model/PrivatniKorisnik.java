package com.ttrpg.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("PrivatniKorisnik") 
public class PrivatniKorisnik extends Korisnik {

    @OneToMany(mappedBy = "privateUser", cascade = CascadeType.ALL)  // Use "privateUser" here
    private List<Prijava> outgoingForms = new ArrayList<>();

    public void createForm(Prijava form) {
        outgoingForms.add(form);
    }

    public List<Prijava> getOutgoingForms() {
        return outgoingForms;
    }

    @Override
    public String toString() {
        return "Private User { "
            + "id: '" + super.getUserId() + "', "
            + "username: '" + super.getUsername() + "', "
            + "password: '" + super.getPassword() + "'}";
    }
}

