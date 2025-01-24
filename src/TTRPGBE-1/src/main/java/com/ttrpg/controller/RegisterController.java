package com.ttrpg.controller;


import com.ttrpg.dto.RegisterRequestDTO;
import com.ttrpg.model.OrgProfil;
import com.ttrpg.model.PoslovniKorisnik;
import com.ttrpg.model.PrivatniKorisnik;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.repository.OrgRepository;
import com.ttrpg.service.KorisnikService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/signup")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private OrgRepository orgRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private KorisnikService userService;
    @PostMapping
    public ResponseEntity<?> signup(@RequestBody RegisterRequestDTO registerDTO) {
        boolean imeUnutra =false;
        boolean emailUnutra= false;
        imeUnutra = userService.isUsernameTaken(registerDTO.getUsername());
        //emailUnutra = userService.isEmailTaken(ud2.getEmail());
        if(imeUnutra) {

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already in use");
            //ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        else {
            if(registerDTO.getRole().equalsIgnoreCase("private")) {
                PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(registerDTO.getUsername(), registerDTO.getEmail(), registerDTO.getPassword());
                korisnikRepository.save(privatniKorisnik);
                return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);

            }
            else if(registerDTO.getRole().equalsIgnoreCase("business") && registerDTO.getOrganizationName()!=null) {
                OrgProfil orgProfil = new OrgProfil(registerDTO.getOrganizationName());
                orgRepository.save(orgProfil);
                PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik(registerDTO.getUsername(), registerDTO.getEmail(), registerDTO.getPassword(), orgProfil);
                korisnikRepository.save(poslovniKorisnik);
                return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
            }
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't signup");

        }

    }

}
