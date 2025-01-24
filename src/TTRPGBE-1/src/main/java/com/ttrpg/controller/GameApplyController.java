package com.ttrpg.controller;

import com.ttrpg.dto.ApplyDTO;
import com.ttrpg.model.*;
import com.ttrpg.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/auth/apply")
public class GameApplyController {

    @Autowired
    public IgraRepository igraRepository;
    @Autowired
    public KorisnikRepository korisnikRepository;
    @Autowired
    public PitanjeRepository pitanjeRepository;
    @Autowired
    public PrijavaRepository prijavaRepository;
    @Autowired
    public OdgovorRepository odgovorRepository;

    @PostMapping
    public ResponseEntity<?> applyToGame(@RequestBody ApplyDTO applyDTO) {
        try {
            String status= "Waiting";
            Igra igra = igraRepository.findGameById(applyDTO.getGameId()).getFirst();

            if(korisnikRepository.findByUserId(applyDTO.getUserId()).getFirst() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "User not found"));
            }
            Korisnik korisnik = korisnikRepository.findByUserId(applyDTO.getUserId()).getFirst();
            if(!(korisnik instanceof PrivatniKorisnik)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Unable to process application. Please try again later." ));
            }
            Prijava prijava = new Prijava(new ArrayList<Odgovor>(),igra, (PrivatniKorisnik) korisnik );

            prijava.setStatus(status);
            prijavaRepository.save(prijava);
            if(applyDTO.getAnswersToQuestions() != null) {
                for(Map.Entry<String, String> entry: applyDTO.getAnswersToQuestions().entrySet()) {
                    Pitanje pitanje = pitanjeRepository.findById_GameIdAndId_QuestionText(applyDTO.getGameId(), entry.getKey()).getFirst();
                    Odgovor odgovor = new Odgovor(pitanje, entry.getValue(), prijava);
                    odgovorRepository.save(odgovor);
                }
            }

            return ResponseEntity.ok().body(Map.of("success", true, "message", "Game apply completed successfully"));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Unable to process application. Please try again later." ));
        }

    }


}
