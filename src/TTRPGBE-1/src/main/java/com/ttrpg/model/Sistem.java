package com.ttrpg.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Sistem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sysId;  // Jedinstveni identifikator sistema

    @Column(nullable = false)
    private String sysName;  // Naziv sistema

    // OneToMany veza s entitetom 'Pravilnik', gdje 'system' označava povezanu stranu
    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL)
    private List<Pravilnik> rulesets;  // Lista pravilnika povezanih s ovim sistemom

    // Konstruktor bez parametara
    public Sistem() {}

    // Konstruktor s parametrima
    public Sistem(int sysId, String sysName, List<Pravilnik> rulesets) {
        this.sysId = sysId;
        this.sysName = sysName;
        this.rulesets = rulesets;
    }

    // Getter za ID sistema
    public int getSysId() {
        return sysId;  // Vraća ID sistema
    }

    // Getter za naziv sistema
    public String getSysName() {
        return sysName;  // Vraća naziv sistema
    }

    // Getter za sve pravilnike povezane s ovim sistemom
    public List<Pravilnik> getAllRulesets() {
        return rulesets;  // Vraća listu pravilnika
    }

    // Override metoda za ispis objekta Sistema u formatu: 'System { id: <sysId>, name: <sysName> }'
    @Override
    public String toString() {
        return
            "System { "
            + "id: '" + sysId + "', "
            + "name: '" + sysName + "'}";
    }
}
