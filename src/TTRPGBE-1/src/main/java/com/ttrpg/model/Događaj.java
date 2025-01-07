package com.ttrpg.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity // Oznaka da je ovo JPA entitet
@Table(name = "događaj") // Definira naziv tablice u bazi podataka
public class Događaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatsko generiranje ID-a
    @Column(name = "event_id") // Ime kolone u bazi podataka
    private Long eventId;  // Jedinstveni identifikator događaja

    @ManyToOne(fetch = FetchType.LAZY) // M:N veza s entitetom Igra
    @JoinColumn(name = "game_id") // Ime kolone koja predstavlja vezu s igrama
    private Igra game; // Veza prema igri kojoj događaj pripada

    @Column(name = "event_name", length = 50) // Ime kolone i maksimalna dužina
    private String eventName; // Naziv događaja

    @Column(name = "event_timestamp") // Ime kolone za datum i vrijeme događaja
    private Timestamp eventTimestamp; // Datum i vrijeme kada se događaj odvija

}
