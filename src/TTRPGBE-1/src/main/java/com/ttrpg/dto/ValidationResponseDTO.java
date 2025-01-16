package com.ttrpg.dto;

import com.ttrpg.model.Korisnik;
import lombok.Data;

@Data
public class ValidationResponseDTO {
    private final String message;
    private final Korisnik userData;
}
