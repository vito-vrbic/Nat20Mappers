package com.ttrpg.model;

import jakarta.persistence.*;

@Entity
@Table(name="Dokument")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docLoc;  // Assuming this should be a numeric ID

    @Column(nullable = false)
    private String docName;

    // Default constructor
    public Document() {}

    // Constructor with fields
    public Document(String docName) {
        this.docName = docName;
    }

    // Getter for docLoc
    public Long getDocLoc() {
        return docLoc;
    }

    // Getter for docName
    public String getDocName() {
        return docName;
    }

    @Override
    public String toString() {
        return "Document { "
            + "loc: '" + docLoc + "', "
            + "name: '" + docName + "'}";
    }
}
