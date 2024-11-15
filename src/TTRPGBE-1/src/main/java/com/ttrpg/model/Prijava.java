package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prijava {

    @Id
    private Long id;  // Jedinstveni identifikator prijave (ID)

    // ManyToOne veza s entitetom 'PrivatniKorisnik', gdje 'user_id' označava strani ključ
    @ManyToOne
    @JoinColumn(name = "user_id")  // Vanjski ključ koji povezuje s PrivatniKorisnik
    private PrivatniKorisnik privateUser; // Privatni korisnik koji je podnio prijavu

    // ManyToOne veza s entitetom 'Document', gdje 'document_id' označava strani ključ
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document filledDocument; // Ispunjen dokument povezani s prijavom

    // Default konstruktor
    public Prijava() {}

    // Konstruktor s argumentima za privatnog korisnika i ispunjeni dokument
    public Prijava(PrivatniKorisnik privateUser, Document filledDocument) {
        this.privateUser = privateUser;
        this.filledDocument = filledDocument;
    }

    // Getter za privatnog korisnika
    public PrivatniKorisnik getPrivateUser() {
        return privateUser;
    }

    // Getter za ispunjeni dokument
    public Document getFilledDocument() {
        return filledDocument;
    }

    // Metoda za ispis objekta prijave u formatu 'user: <username>, document: <docName>'
    @Override
    public String toString() {
        return "Form { user: " + privateUser.getUsername() + ", document: " + filledDocument.getDocName() + " }";
    }
}
