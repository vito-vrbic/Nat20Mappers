package com.ttrpg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
@Inheritance(strategy = InheritanceType.JOINED) // Definira strategiju naslijeđivanja entiteta
@Entity // Oznaka da je ovo JPA entitet
@Table(name = "Igra") // Definira naziv tablice u bazi podataka

public class Igra {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatsko generiranje ID-a
    @Column(name = "gameId") // Ime kolone u bazi podataka
    private Long id; // Jedinstveni identifikator igre

    @JsonProperty("title") // Serijalizira ovaj atribut pod nazivom "title"
    private String gameName; // Naziv igre

    //@JsonProperty("type") // Serijalizira ovaj atribut pod nazivom "type"
    //private String type; // Tip igre (npr. online, offline)

    //@Embedded // Oznaka za ugrađeni entitet
    //private MapLocation location; // Lokacija igre (geografska)

    @JsonProperty("availability") // Serijalizira ovaj atribut pod nazivom "availability"
    private String availability; // Dostupnost igre


    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "userId", nullable = false)  // Foreign key to User
    private Korisnik createdBy;

    //@JsonProperty("createdBy") // Serijalizira ovaj atribut pod nazivom "createdBy"
    //private String createdBy; // Kreator igre (npr. korisnik ili poslovni entitet)

    @JsonProperty("applicationRequired") // Serijalizira ovaj atribut pod nazivom "applicationRequired"
    private Boolean applicationRequired; // Da li je prijava potrebna za igru

    @JsonProperty("complexity") // Serijalizira ovaj atribut pod nazivom "complexity"
    private String complexity; // Kompleksnost igre

    @JsonProperty("estimatedLength") // Serijalizira ovaj atribut pod nazivom "estimatedLength"
    private String estimatedLength; // Procijenjeno trajanje igre

    @JsonProperty("startTimestamp") // Serijalizira ovaj atribut pod nazivom "startTimestamp"
    private String startTimestamp; // Početno vrijeme igre

    @JsonProperty("description") // Serijalizira ovaj atribut pod nazivom "description"
    private String description; // Opis igre

    @JsonProperty("pravilnik") // Serijalizira ovaj atribut pod nazivom "pravilnik"
    private String ruleset; // Pravila igre

    @JsonProperty("requiresForm") // Serijalizira ovaj atribut pod nazivom "requiresForm"
    private Boolean requiresForm; // Da li je potrebno ispuniti obrazac za igru

    //@JsonProperty("currentPlayerCount") // Serijalizira ovaj atribut pod nazivom "currentPlayerCount"
    //private Integer currentPlayerCount; // Trenutni broj igrača u igri

    @JsonProperty("maxPlayerCount") // Serijalizira ovaj atribut pod nazivom "maxPlayerCount"
    private Integer maxPlayerCount; // Maksimalni broj igrača u igri

    @JsonProperty("communicationChannel") // Serijalizira ovaj atribut pod nazivom "communicationChannel"
    private String communicationChannel; // Kanal komunikacije (npr. Discord)

    @JsonProperty("isHomebrew") // Serijalizira ovaj atribut pod nazivom "isHomebrew"
    private Boolean isHomebrew; // Da li je igra prilagođena (homebrew) ili standardna

    // Getters and Setters (metode za pristup i postavljanje vrijednosti atributa)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return gameName;
    }

    public void setTitle(String gameName) {
        this.gameName = gameName;
    }

    //public String getType() {
      //  return type;
    //}

    //public void setType(String type) {
      //  this.type = type;
    //}

   // public MapLocation getLocation() {
   //     return location;
   // }

    //public void setLocation(MapLocation location) {
    //    this.location = location;
    //}

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Korisnik getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Korisnik createdBy) {
        this.createdBy = createdBy;
    }

   public Boolean getApplicationRequired() {
       return applicationRequired;
   }

    public void setApplicationRequired(Boolean applicationRequired) {
        this.applicationRequired = applicationRequired;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getEstimatedLength() {
        return estimatedLength;
    }

    public void setEstimatedLength(String estimatedLength) {
        this.estimatedLength = estimatedLength;
    }

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRuleset() {
        return ruleset;
    }

    public void setRuleset(String pravilnik) {
        this.ruleset = pravilnik;
    }

    public Boolean getRequiresForm() {
        return requiresForm;
    }

    public void setRequiresForm(Boolean requiresForm) {
        this.requiresForm = requiresForm;
    }

    //public void setCurrentPlayerCount(Integer currentPlayerCount) {
    //    this.currentPlayerCount = currentPlayerCount;
    //}

    public Integer getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public void setMaxPlayerCount(Integer maxPlayerCount) {
        this.maxPlayerCount = maxPlayerCount;
    }

    public String getCommunicationChannel() {
        return communicationChannel;
    }

    public void setCommunicationChannel(String communicationChannel) {
        this.communicationChannel = communicationChannel;
    }

    public Boolean getIsHomebrew() {
        return isHomebrew;
    }

    public void setIsHomebrew(Boolean isHomebrew) {
        this.isHomebrew = isHomebrew;
    }

    // Konstruktor s parametrima


    public Igra(Long id, String gameName, String availability, Korisnik createdBy, Boolean applicationRequired,
                String complexity, String estimatedLength, String startTimestamp,
                String description, String ruleset, Boolean requiresForm, Integer maxPlayerCount,
                String communicationChannel, Boolean isHomebrew) {
        this.id = id;
        this.gameName = gameName;
        this.availability = availability;
        this.createdBy = createdBy;
        this.applicationRequired = applicationRequired;
        this.complexity = complexity;
        this.estimatedLength = estimatedLength;
        this.startTimestamp = startTimestamp;
        this.description = description;
        this.ruleset = ruleset;
        this.requiresForm = requiresForm;
        this.maxPlayerCount = maxPlayerCount;
        this.communicationChannel = communicationChannel;
        this.isHomebrew = isHomebrew;
    }
    public Igra(String gameName, String availability, Korisnik createdBy, Boolean applicationRequired,
                String complexity, String estimatedLength, String startTimestamp,
                String description, String ruleset, Boolean requiresForm, Integer maxPlayerCount,
                String communicationChannel, Boolean isHomebrew) {
        this.gameName = gameName;
        this.availability = availability;
        this.createdBy = createdBy;
        this.applicationRequired = applicationRequired;
        this.complexity = complexity;
        this.estimatedLength = estimatedLength;
        this.startTimestamp = startTimestamp;
        this.description = description;
        this.ruleset = ruleset;
        this.requiresForm = requiresForm;
        this.maxPlayerCount = maxPlayerCount;
        this.communicationChannel = communicationChannel;
        this.isHomebrew = isHomebrew;
    }

    public Igra(Long id) {
        this.id = id;
    }

    // Defaultni konstruktor
    public Igra() {
    }
}
