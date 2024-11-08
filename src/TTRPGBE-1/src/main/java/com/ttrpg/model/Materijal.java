package com.ttrpg.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Materijal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int materialId;

    @Nonnull
    private String materialName;

    @ManyToOne
    @JoinColumn(name = "ruleset_id")
    private Pravilnik pravilnik;

    public Materijal() {}

    public Materijal(int materialId, String materialName, Pravilnik pravilnik) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.pravilnik = pravilnik;
    }

    public int getMaterialId() {
        return materialId;
    }

    public String getMaterialName() {
        return materialName;
    }
}
