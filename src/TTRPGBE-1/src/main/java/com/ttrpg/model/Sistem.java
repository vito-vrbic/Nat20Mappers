package com.ttrpg.model;

public class Sistem {
    private int sysId;
    private String sysName;
    private Pravilnik[] rulesets;

    public Sistem(int sysId, String sysName, Pravilnik[] rulesets) {
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

    public Pravilnik[] getAllRulesets() {
        return rulesets;
    }
}
