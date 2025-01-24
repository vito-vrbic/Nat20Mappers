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
}
