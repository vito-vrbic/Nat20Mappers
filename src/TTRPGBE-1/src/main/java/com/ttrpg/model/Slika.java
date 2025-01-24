package com.ttrpg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Slika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String imgLoc;

    @Column(nullable = false)
    private String imgName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private OrgProfil orgProfil;

    public Slika() {}

    public Slika(String imgLoc, String imgName) {
        this.imgLoc = imgLoc;
        this.imgName = imgName;
    }

    public String getImgLoc() {
        return imgLoc;
    }

    public String getImgName() {
        return imgName;
    }

    @Override
    public String toString() {
        return "Slika { " +
                "imgLoc: '" + imgLoc + "', " +
                "imgName: '" + imgName + "' " +
                "}";
    }
}
