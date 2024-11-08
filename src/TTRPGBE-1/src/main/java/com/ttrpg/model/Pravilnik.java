package com.ttrpg.model;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pravilnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rulesetId;

    @Nonnull
    private String rulesetName;
    
    @ManyToOne
    @JoinColumn(name = "sys_id")
    private Sistem system;

    @OneToMany(mappedBy = "ruleset", cascade = CascadeType.ALL)
    private List<Materijal> materials;

    public Pravilnik () {}

    public Pravilnik(int rulesetId, String rulesetName, List<Materijal> materials) {
        this.rulesetId = rulesetId;
        this.rulesetName = rulesetName;
        this.materials = materials; 
    }

    public int getRulesetId() {
        return rulesetId;
    }

    public String getRulesetName() {
        return rulesetName;
    }

    public List<Materijal> getAllMaterials() {
        return materials;
    }
}
