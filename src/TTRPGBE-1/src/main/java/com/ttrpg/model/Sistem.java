package com.ttrpg.model;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Sistem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sysId;

    @Nonnull
    private String sysName;

    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL)
    private List<Pravilnik> rulesets;

    public Sistem() {}

    public Sistem(int sysId, String sysName, List<Pravilnik> rulesets) {
        this.sysId = sysId;
        this.sysName = sysName;
        this.rulesets = rulesets; 
    }

    public int getSysId() {
        return sysId;
    }

    public String getSysName() {
        return sysName;
    }

    public List<Pravilnik> getAllRulesets() {
        return rulesets;
    }
}
