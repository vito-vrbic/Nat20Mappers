package com.ttrpg.model;

public class Materijal {
    private int materialId;
    private String materialName;

    public Materijal(int materialId, String materialName) {
        this.materialId = materialId;
        this.materialName = materialName;
    }

    public int getMaterialId() {
        return materialId;
    }

    public String getMaterialName() {
        return materialName;
    }
}
