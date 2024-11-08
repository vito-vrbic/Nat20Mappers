package com.ttrpg.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class PrivatniKorisnik extends Korisnik{

    @OneToMany(mappedBy = "private_user", cascade = CascadeType.ALL)
    private List<Prijava> outgoingForms = new ArrayList<>();

    void createForm(Prijava form) {
        outgoingForms.add(form);
    }

    @Override
    public String toString() {
        return
            "Private User { "
            + "id: '" + super.getUserId() + "', "
            + "username: '" + super.getUsername() + "', "
            + "password: '" + super.getPassword() + "'}";
    }
}
