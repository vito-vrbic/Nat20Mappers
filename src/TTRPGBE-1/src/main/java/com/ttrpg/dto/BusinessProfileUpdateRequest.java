package com.ttrpg.dto;

public class BusinessProfileUpdateRequest {
    private String logo;
    private String companyName;
    private String companyPhone;
    private String companyDes;
    private String companyWeb;
    private String companyAddress;

    public BusinessProfileUpdateRequest() {
    }

    public BusinessProfileUpdateRequest(String logo, String companyName, String companyPhone, String companyDes, String companyWeb, String companyAddress) {
        this.logo = logo;
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.companyDes = companyDes;
        this.companyWeb = companyWeb;
        this.companyAddress = companyAddress;
    }

    public boolean isValid() {
        return logo != null && !logo.trim().isEmpty() &&
                companyName != null && !companyName.trim().isEmpty() &&
                companyPhone != null && !companyPhone.trim().isEmpty() &&
                companyDes != null && !companyDes.trim().isEmpty() &&
                companyWeb != null && !companyWeb.trim().isEmpty() &&
                companyAddress != null && !companyAddress.trim().isEmpty();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyDes() {
        return companyDes;
    }

    public void setCompanyDes(String companyDes) {
        this.companyDes = companyDes;
    }

    public String getCompanyWeb() {
        return companyWeb;
    }

    public void setCompanyWeb(String companyWeb) {
        this.companyWeb = companyWeb;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
