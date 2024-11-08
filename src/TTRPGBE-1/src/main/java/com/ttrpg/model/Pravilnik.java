package com.ttrpg.model;

public class Pravilnik {
    private int rulesetId;
    private String rulesetName;
    private Materijal[] materials;

    public Pravilnik(int rulesetId, String rulesetName, Materijal[] materials) {
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

    public Materijal[] getAllMaterials() {
        return materials;
    }
}
