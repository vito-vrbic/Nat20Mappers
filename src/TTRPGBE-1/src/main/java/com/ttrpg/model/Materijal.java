package com.ttrpg.model;

import jakarta.persistence.Column;
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

    @Column(nullable = false)
    private String materialName;

    @ManyToOne
    @JoinColumn(name = "ruleset_id")
    private Pravilnik ruleset;

    public Materijal() {}

    public Materijal(int materialId, String materialName, Pravilnik ruleset) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.ruleset = ruleset;
    }

    public int getMaterialId() {
        return materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    @Override
    public String toString() {
        return
            "Material { "
            + "id: '" +  materialId + "', "
            + "name: '" + materialName + "'}";
    }
}
