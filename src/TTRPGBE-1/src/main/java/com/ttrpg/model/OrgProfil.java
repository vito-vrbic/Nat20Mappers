package com.ttrpg.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class OrgProfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @Column(nullable = false)
    private String companyName;

    //@Column(nullable = false)
    private String companyPhone;

    //@Column(nullable = false)
    private String companyDes;

    //@Column(nullable = false)
    private String companyWeb;

    //@Column(nullable = false)
    private String companyAddress;
    private String companyLogo;


    // OneToMany veza sa slikama, gdje "orgProfil" u klasi Slika označava mappedBy
    //@OneToMany(mappedBy = "orgProfil", cascade = CascadeType.ALL)
    //private List<Slika> companyLogos;

    // OneToOne veza s PoslovnimKorisnikom, mappedBy na polje business_user
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private PoslovniKorisnik businessUser;

    // Konstruktor, getter-i, setter-i, toString
    public OrgProfil() {

    }

    public OrgProfil(String companyName, String companyPhone, String companyDes, String companyWeb, String companyAdress, String companyUrl) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.companyDes = companyDes;
        this.companyWeb = companyWeb;
        this.companyAddress = companyAdress;
        this.companyLogo = companyUrl;
    }
    public OrgProfil(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyName(String companyName) {
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

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAdress() {
        return companyAddress;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public PoslovniKorisnik getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(PoslovniKorisnik business_user) {
        this.businessUser = business_user;
    }

    @Override
    public String toString() {
        return "OrgProfil { "
                + "companyId: '" + companyId + "', "
                + "companyName: '" + companyName + "', "
                + "companyPhone: '" + companyPhone + "', "
                + "companyDes: '" + companyDes + "', "
                + "companyWeb: '" + companyWeb + "', "
                + "companyAdress: '" + companyAddress + "' "
                + "}"; // Metoda za ispis objekta kao string
    }
}
