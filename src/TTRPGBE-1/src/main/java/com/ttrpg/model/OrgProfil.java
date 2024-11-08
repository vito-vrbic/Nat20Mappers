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
public class OrgProfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String companyPhone;

    @Column(nullable = false)
    private String companyDes;

    @Column(nullable = false) 
    private String companyWeb;

    @Column(nullable = false)
    private String companyAdress;

    @OneToMany(mappedBy = "org_profile", cascade = CascadeType.ALL)
    private List<Slika> companyLogos;

    public OrgProfil() {}

    public OrgProfil(int companyId, String companyName, String companyPhone, String companyDes, String companyWeb, String companyAdress, List<Slika> companyLogos) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.companyDes = companyDes;
        this.companyWeb = companyWeb;
        this.companyAdress = companyAdress;
        this.companyLogos = companyLogos;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyDes(String companyDes) {
        this.companyDes = companyDes;
    }

    public String getCompanyDes() {
        return companyDes;
    }

    public void setCompanyWeb(String companyWeb) {
        this.companyWeb = companyWeb;
    }

    public String getCompanyWeb() {
        return companyWeb;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setLogo(Slika companyLogo) {
        companyLogos.add(companyLogo);
    }
    
    @Override
    public String toString() {
        return "OrgProfil { "
                + "companyId: '" + companyId + "', "
                + "companyName: '" + companyName + "', "
                + "companyPhone: '" + companyPhone + "', "
                + "companyDes: '" + companyDes + "', "
                + "companyWeb: '" + companyWeb + "', "
                + "companyAdress: '" + companyAdress + "', "
                + " }";
    }
}
