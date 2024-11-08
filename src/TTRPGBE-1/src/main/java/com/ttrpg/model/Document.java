package com.ttrpg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String docLoc;

    @Column(nullable = false)
    private String docName;

    public Document() {}

    public Document(String docLoc, String docName) {
        this.docLoc = docLoc;
        this.docName = docName;
    }

    @Override
    public String toString() {
        return
            "Ruleset { "
            + "loc: '" + docLoc + "', "
            + "name: '" + docName + "'}";
    }
}
