package com.ttrpg.controller;


import com.ttrpg.dto.GetApplicationsDTO;
import com.ttrpg.model.Odgovor;
import com.ttrpg.model.Prijava;
import com.ttrpg.repository.PrijavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game-applications")
public class ApplicationGetController {

    @Autowired
    private PrijavaRepository prijavaRepository;

    @GetMapping
    public ResponseEntity<?> getGameApplications(@RequestParam Long id) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to fetch data"));
        }
        try {

            List<Prijava> gameApplications = prijavaRepository.findByIdGameId(id);
            GetApplicationsDTO dtos = new GetApplicationsDTO();
            for(Prijava gameApplication : gameApplications) {
                GetApplicationsDTO.GetApplicationDTO dto = new GetApplicationsDTO.GetApplicationDTO();
                dto.setId(gameApplication.getPrijavaId().getGameId());
                dto.setUserId(gameApplication.getPrivateUser().getUserId());
                dto.setUsername(gameApplication.getPrivateUser().getUsername());
                List<Odgovor> odgovori = gameApplication.getAnswers();
                for(Odgovor odgovor : odgovori) {
                    dto.addEntry(odgovor.getOdgovorId().getPitanjeId().getQuestionText(), odgovor.getOdgovorId().getAnswerText());
                }
                dtos.addAppliedUsers(dto);
            }
            return ResponseEntity.ok(dtos);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to fetch data"));
        }

    }

}
