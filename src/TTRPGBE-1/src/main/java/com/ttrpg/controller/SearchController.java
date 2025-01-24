package com.ttrpg.controller;

import com.ttrpg.dto.SearchDTO;
import com.ttrpg.model.*;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.service.IgraService;
import com.ttrpg.service.KorisnikService;
import com.ttrpg.service.SearchRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/data/search") // Ruta za pretraživanje igara
public class SearchController {

    @Autowired
    private IgraService igraService; // Servis za manipulaciju podacima o igrama
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KorisnikRepository korisnikRepository;

    @PostMapping // Obrada POST zahtjeva za pretraživanje igara
    public ResponseEntity<HashMap<String, List<SearchDTO>>> searchIgre22(@RequestBody SearchRequest sr) {
        
        // Dohvaća kriterije pretraživanja iz zahtjeva
        // String available = sr.getGameAvailability();
        // String tipIgre = sr.getGameType();
        Boolean trebaPrijavnica = sr.getApplicationRequired();
        // Boolean ukljucipune = sr.getIncludeFullGames();
        Double radius = 0.0;

        // Pokušava parsirati radius iz zahtjeva
        try {
            radius = Double.parseDouble(sr.getRadius());
        } catch (IllegalArgumentException e) {
            radius = 0.0; // Ako parsiranje ne uspije, postavlja radius na 0
        }

        MapLocation ml = sr.getMapLocation();
        // Boolean ukljucenePoslovne = sr.getIncludeBusinessMadeGames();
        // Boolean ukljucenePrivatne = sr.getIncludeUserMadeGames();
        List<Igra> li = igraService.searchIgraService(sr.getGameTitle());
        // Integer stranica = sr.getPage();

        // Filtrira igre na osnovu uvjeta iz zahtjeva
        Stream<Igra> liStream = li.stream();

        li = liStream.filter(s -> {
            Boolean puna;
            Boolean punamoze = sr.getIncludeFullGames();
            Boolean fail;

            // Provjerava je li igra puna
            if (igraService.getPlayerCount(s.getId()) < s.getMaxPlayerCount()) {
                puna = false;
            } else {
                puna = true;
            }

            // Ovisno o preferencijama uključivanja punih igara, odlučuje treba li igra proći filter
            fail = !(puna && !punamoze);

            // Ako je unesen naziv igre, filtrira igre prema nazivu
            if (!sr.getGameTitle().equals("")) {
                return (s.getApplicationRequired() == trebaPrijavnica) && fail &&
                       (s.getTitle().equals(sr.getGameTitle()));
            } else {
                return (s.getApplicationRequired() == trebaPrijavnica) && fail;
            }
        }).collect(Collectors.toList());

        // Uklanja igre koje nisu unutar definiranog radijusa
        Iterator<Igra> it = li.iterator();
        if(radius > 0) {
            while (it.hasNext()) {
                Igra igraIt = it.next();
                if(igraIt instanceof TocnoLokacijskaIgra) {
                    if(!(((TocnoLokacijskaIgra)igraIt).getLocation().inRadius(ml, radius))) {
                        it.remove();
                    }
                }
                //if (!igraIt.getLocation().inRadius(ml, radius)) {
                //    it.remove();
                //}
            }
        }
        //while (it.hasNext()) {
        //    Igra igraIt = it.next();
        //    if (!igraIt.getLocation().inRadius(ml, radius)) {
        //        it.remove();
        //    }
        //}

        // Filtrira igre prema tipu kreatora (poslovni ili privatni korisnici)
        it = li.iterator();
        while (it.hasNext()) {
            Igra igraIt = it.next();
            if (!sr.getIncludeBusinessMadeGames() && igraIt.getCreatedBy() instanceof PoslovniKorisnik) {
                it.remove();
            }
        }

        it = li.iterator();
        while (it.hasNext()) {
            Igra igraIt = it.next();
            if (!sr.getIncludeUserMadeGames() && igraIt.getCreatedBy() instanceof PrivatniKorisnik) {
                it.remove();
            }
        }
        if(sr.getGameAvailability().contains("all games")) {}
        else if (sr.getGameAvailability().contains("public")) {
            it= li.iterator();
            while(it.hasNext()) {
                Igra igraIt = it.next();
                if(igraIt.getAvailability().equalsIgnoreCase("private")) {
                    it.remove();
                }
            }
        } else if (sr.getGameAvailability().contains("private")) {
            it= li.iterator();
            while(it.hasNext()) {
                Igra igraIt = it.next();
                if(igraIt.getAvailability().equalsIgnoreCase("public")) {
                    it.remove();
                }
            }
        }
        // Filtrira igre prema tipu (sve, online ili lokalne)
        if (sr.getGameType().contains("all types")) {
            // Zadržava sve igre
        } else if (sr.getGameType().contains("online")) {
            it = li.iterator();
            while (it.hasNext()) {
                Igra igraIt = it.next();
                //if (igraIt.getType().contains("offline")) {
                //    it.remove();
                //}
                if(!(igraIt instanceof OnlineIgra)) {
                    it.remove();
                }
            }
        } else if (sr.getGameType().contains("local")) {
            it = li.iterator();
            while (it.hasNext()) {
                Igra igraIt = it.next();
                if (!(igraIt instanceof LokaliziranaIgra)) {
                    it.remove();
                }
            }
        }

        it=li.iterator();
        List<SearchDTO> searches = new ArrayList<>();
        while (it.hasNext()) {
            Igra igraIt = it.next();
            Korisnik voditelj = igraIt.getCreatedBy();
            SearchDTO search = new SearchDTO(igraIt.getId(), igraIt.getTitle(), igraIt.getApplicationRequired());
            if(igraIt instanceof TocnoLokacijskaIgra) {
                search.setType("exact");
                search.setLocation(((TocnoLokacijskaIgra)igraIt).getLocation());
            }
            else if(igraIt instanceof OnlineIgra) search.setType("online");
            else if(igraIt instanceof LokaliziranaIgra) {
                search.setType("local");
                search.setLocation(((LokaliziranaIgra)igraIt).getFakeLocation());
            }
            if(voditelj instanceof PoslovniKorisnik) search.setCreatedBy("business");
            else search.setCreatedBy("user");
            searches.add(search);


        }
        HashMap<String, List<SearchDTO>> hmm = new HashMap<>();
        hmm.put("games", searches);
        // Priprema konačne rezultate u mapu i vraća kao odgovor
        //HashMap<String, List<Igra>> hm = new HashMap<>();
        //hm.put("games", li);
        //System.out.println("Ovo se izvršava " + li.size() + " " + sr.getGameTitle());
        return ResponseEntity.ok(hmm);
    }
}

/*

public ResponseEntity<List<Igra>> searchIgre(
	    @RequestParam(required = false) String gameName,            // gameName
	    @RequestParam(required = false) String type,                // type
	    @RequestParam(required = false) String location,            // location
	    @RequestParam(required = false) String availability,        // availability
	    @RequestParam(required = false) String createdBy,           // createdBy
	    @RequestParam(required = false) Boolean applicationRequired, // applicationRequired
	    @RequestParam(required = false) String complexity,          // complexity
	    @RequestParam(required = false) String estimatedLength,     // estimatedLength
	    @RequestParam(required = false) String startTimestamp,      // startTimestamp
	    @RequestParam(required = false) String description,         // description
	    @RequestParam(required = false) String pravilnik,           // pravilnik
	    @RequestParam(required = false) Boolean requiresForm,       // requiresForm
	    @RequestParam(required = false) Integer currentPlayerCount,  // currentPlayerCount
	    @RequestParam(required = false) Integer maxPlayerCount,      // maxPlayerCount
	    @RequestParam(required = false) String communicationChannel, // communicationChannel
	    @RequestParam(required = false) Boolean isHomebrew          // isHomebrew{
){
	 List<Igra> results = igraService.searchIgre(
	            gameName, type, location, availability, createdBy, 
	            applicationRequired, complexity, estimatedLength, 
	            startTimestamp, description, pravilnik, requiresForm, 
	            currentPlayerCount, maxPlayerCount, communicationChannel, 
	            isHomebrew
	    );

   

        return ResponseEntity.ok(results);
    }*/


/*@GetMapping("/searchByName")
public List<Igra> searchGamesByName(@RequestParam String name) {
    return IgraService.findByName(name);*/