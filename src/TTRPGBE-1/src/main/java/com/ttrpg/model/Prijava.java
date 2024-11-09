package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prijava {
    @Id
    private Long id;  // Assuming you want to add an ID for Prijava

    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign key for PrivatniKorisnik
    private PrivatniKorisnik privateUser;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document filledDocument;

    public Prijava() {}

    public Prijava(PrivatniKorisnik privateUser, Document filledDocument) {
        this.privateUser = privateUser;
        this.filledDocument = filledDocument;
    }

    public PrivatniKorisnik getPrivateUser() {
        return privateUser;
    }

    public Document getFilledDocument() {
        return filledDocument;
    }

    @Override
    public String toString() {
        return "Form { user: " + privateUser.getUsername() + ", document: " + filledDocument.getDocName() + " }";
    }
}
