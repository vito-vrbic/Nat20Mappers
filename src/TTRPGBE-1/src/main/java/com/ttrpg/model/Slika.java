package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Slika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String imageUrl;

    @ManyToOne
    private OrgProfil orgProfil;

    // Constructors, getters, setters
    public Slika() {}

    public Slika(String imageUrl, OrgProfil orgProfil) {
        this.imageUrl = imageUrl;
        this.orgProfil = orgProfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public OrgProfil getOrgProfil() {
        return orgProfil;
    }

    public void setOrgProfil(OrgProfil orgProfil) {
        this.orgProfil = orgProfil;
    }
}

