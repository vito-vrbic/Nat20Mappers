package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prijava {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private PrivatniKorisnik privateUser;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document filledDocument;

    public Prijava() {}

    public Prijava(PrivatniKorisnik privateUser, Document filledDocument) {
        this.privateUser = privateUser;
        this.filledDocument = filledDocument;
    }

    public Document getFilledDocument() {
        return filledDocument;
    }

    @Override
    public String toString() {
        return
            "Form {}";
    }
}
