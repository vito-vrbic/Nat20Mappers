package com.ttrpg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity  // Ova anotacija označava da je klasa entitet koja se mapira u bazu podataka
public class Materijal {

    @Id  // Ova anotacija označava da je id polje primarni ključ
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatski generira vrijednost ID-a
    private int materialId;  // Jedinstveni identifikator materijala

    @Column(nullable = false)  // Ova anotacija označava da naziv materijala ne može biti null
    private String materialName;  // Naziv materijala

    @ManyToOne  // Ova anotacija označava da je Materijal povezan s jednim pravilnikom
    @JoinColumn(name = "ruleset_id")  // Ova anotacija označava naziv stranog ključa u tablici
    private Pravilnik ruleset;  // Veza prema entitetu Pravilnik (ruleset)

    // Podrazumijevani konstruktor
    public Materijal() {}

    // Konstruktor s parametrima za inicijalizaciju polja
    public Materijal(int materialId, String materialName, Pravilnik ruleset) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.ruleset = ruleset;
    }

    // Getter za materialId
    public int getMaterialId() {
        return materialId;
    }

    // Getter za materialName
    public String getMaterialName() {
        return materialName;
    }

    // Prepisana toString metoda za ispis objekta Materijal u čitljivom formatu
    @Override
    public String toString() {
        return
            "Material { "
            + "id: '" +  materialId + "', "
            + "name: '" + materialName + "'}";  // Ispis ID-a i naziva materijala
    }
}
