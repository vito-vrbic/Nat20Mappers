package com.ttrpg.dto;

import com.ttrpg.model.Korisnik;
import com.ttrpg.service.UserData;
import lombok.Data;

@Data
public class ValidationResponseDTO {
    private final String message;
    private final UserData userData;
}
