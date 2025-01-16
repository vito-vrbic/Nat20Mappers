package com.ttrpg.dto;

import lombok.Data;

@Data
public class BusinessProfileUpdateRequestDTO {
    private String logo;
    private String companyName;
    private String companyPhone;
    private String companyDes;
    private String companyWeb;
    private String companyAddress;

    public boolean isValid() {
        return logo != null && !logo.trim().isEmpty() &&
                companyName != null && !companyName.trim().isEmpty() &&
                companyPhone != null && !companyPhone.trim().isEmpty() &&
                companyDes != null && !companyDes.trim().isEmpty() &&
                companyWeb != null && !companyWeb.trim().isEmpty() &&
                companyAddress != null && !companyAddress.trim().isEmpty();
    }
}
