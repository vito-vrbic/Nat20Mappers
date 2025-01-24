package com.ttrpg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ttrpg.dto.BusinessProfileDetailsDTO;
import com.ttrpg.dto.BusinessProfileUpdateRequestDTO;
import com.ttrpg.service.KorisnikService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private KorisnikService userService;

    // GET PROFILE DATA
    @GetMapping("/{username}")
    public ResponseEntity<?> getProfile(@PathVariable String username) {
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Invalid username"));
        }

        try {
            BusinessProfileDetailsDTO profile = userService.getProfileByUsername(username);
            if (profile == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Profile not found"));
            }

            return ResponseEntity.ok(Map.of(
                    "message", "Profile retrieved successfully",
                    "data", profile
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An unexpected error occurred"));
        }
    }

    // UPDATE PROFILE DATA
    @PutMapping("/edit-profile")
    public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String authToken,
                                            @RequestBody BusinessProfileUpdateRequestDTO updateRequest) {
        if (authToken == null || !userService.isValidToken(authToken.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "No token found or invalid token"));
        }

        //if (!updateRequest.isValid()) {
        //    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        //            .body(Map.of("message", "Invalid input data"));
        //}

        try {
            BusinessProfileDetailsDTO updatedProfile = userService.updateProfile(authToken.replace("Bearer ", ""), updateRequest);
            return ResponseEntity.ok(Map.of(
                    "message", "Profile updated successfully",
                    "updatedProfile", updatedProfile
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An unexpected error occurred"));
        }
    }
}
