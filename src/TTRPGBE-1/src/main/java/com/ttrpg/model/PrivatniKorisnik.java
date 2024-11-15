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

    // OneToMany veza s entitetom 'Prijava', gdje 'privateUser' označava povezanu stranu
    @OneToMany(mappedBy = "privateUser", cascade = CascadeType.ALL)  // Povezivanje s 'Prijava' entitetom
    private List<Prijava> outgoingForms = new ArrayList<>();  // Lista svih prijava koje je korisnik poslao

    // Metoda za stvaranje nove prijave i dodavanje u listu
    public void createForm(Prijava form) {
        outgoingForms.add(form);  // Dodaje novu prijavu u listu poslanih prijava
    }

    // Getter za listu poslanih prijava
    public List<Prijava> getOutgoingForms() {
        return outgoingForms;  // Vraća listu svih poslanih prijava
    }

    // Override metoda za ispis objekta PrivatniKorisnik u formatu: 'Private User { id: <userId>, username: <username>, password: <password> }'
    @Override
    public String toString() {
        return "Private User { "
            + "id: '" + super.getUserId() + "', "
            + "username: '" + super.getUsername() + "', "
            + "password: '" + super.getPassword() + "'}";
    }
}
