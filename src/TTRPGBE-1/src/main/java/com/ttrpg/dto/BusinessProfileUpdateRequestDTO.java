package com.ttrpg.dto;

import lombok.Data;

@Data
public class BusinessProfileUpdateRequestDTO {
    private String logo;
    private String companyName;
    private String companyPhone;
    private String companyDescription;
    private String companyWebsite;
    private String companyAddress;

    public boolean isValid() {
        return logo != null && !logo.trim().isEmpty() &&
                companyName != null && !companyName.trim().isEmpty() &&
                companyPhone != null && !companyPhone.trim().isEmpty() &&
                companyDescription != null && !companyDescription.trim().isEmpty() &&
                companyWebsite != null && !companyWebsite.trim().isEmpty() &&
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

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
    
    
}
