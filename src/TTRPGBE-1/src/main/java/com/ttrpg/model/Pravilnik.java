package com.ttrpg.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
    private int rulesetId; // Jedinstveni identifikator pravilnika

    @Column(nullable = false)
    private String rulesetName; // Naziv pravilnika
    
    // ManyToOne veza s entitetom 'Sistem', gdje 'sys_id' označava strani ključ
    @ManyToOne
    @JoinColumn(name = "sys_id")
    private Sistem system; // Sistem kojem pravilnik pripada

    // OneToMany veza s entitetom 'Materijal', gdje 'ruleset' označava mappedBy
    @OneToMany(mappedBy = "ruleset", cascade = CascadeType.ALL)
    private List<Materijal> materials; // Popis materijala povezanih s pravilnikom

    // Default konstruktor
    public Pravilnik () {}

    // Konstruktor s argumentima
    public Pravilnik(int rulesetId, String rulesetName, Sistem system, List<Materijal> materials) {
        this.rulesetId = rulesetId;
        this.rulesetName = rulesetName;
        this.system = system;
        this.materials = materials; 
    }

    // Getter za id pravilnika
    public int getRulesetId() {
        return rulesetId;
    }

    // Getter za naziv pravilnika
    public String getRulesetName() {
        return rulesetName;
    }

    // Getter za sve materijale povezane s pravilnikom
    public List<Materijal> getAllMaterials() {
        return materials;
    }

    @Override
    public String toString() {
        // Ispis objekta pravilnika s njegovim ID-em i nazivom
        return
            "Ruleset { "
            + "id: '" + rulesetId + "', "
            + "name: '" + rulesetName + "'}";
    }
}
