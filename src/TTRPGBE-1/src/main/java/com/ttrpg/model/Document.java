package com.ttrpg.model;

import jakarta.persistence.*;

@Entity // Oznaka da je ovo JPA entitet
@Table(name = "Dokument") // Definira naziv tablice u bazi podataka
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Strategija nasljeđivanja - sve klase u istoj tablici
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatsko generiranje ID-a
    private Long docLoc; // Jedinstveni identifikator dokumenta

    @Column(nullable = false) // Polje ne može biti prazno
    private String docName; // Naziv dokumenta

    // Zadani konstruktor
    public Document() {}

    // Konstruktor s parametrima
    public Document(String docName) {
        this.docName = docName;
    }

    // Getter za docLoc
    public Long getDocLoc() {
        return docLoc;
    }

    // Getter za docName
    public String getDocName() {
        return docName;
    }

    @Override
    public String toString() {
        // Override metode za prikaz objekta u čitljivom formatu
        return "Document { "
            + "loc: '" + docLoc + "', "
            + "name: '" + docName + "'}";
    }
}
