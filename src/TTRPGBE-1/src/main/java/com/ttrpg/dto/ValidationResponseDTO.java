package com.ttrpg.dto;

import com.ttrpg.model.Korisnik;
import com.ttrpg.service.UserData;
import lombok.Data;

@Data
public class ValidationResponseDTO {
    private  String message;
    private  UserData userData;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	public ValidationResponseDTO(String message, UserData userData) {
		super();
		this.message = message;
		this.userData = userData;
	}
    
    
}
