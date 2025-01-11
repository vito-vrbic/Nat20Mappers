package com.ttrpg.dto;

import com.ttrpg.model.Korisnik;

public class ValidationResponseDTO {
    private final String message;
    private final Korisnik userData;
    public String getMessage() {
        return message;
    }

    public Korisnik getUserData() {
        return userData;
    }
    public ValidationResponseDTO(String message, Korisnik userData) {
        this.message = message;
        this.userData = userData;
    }
}
