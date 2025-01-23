package com.ttrpg.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Prijava {

    @EmbeddedId
    private PrijavaId id;  // Jedinstveni identifikator prijave (ID)
    private String status;

    // ManyToOne veza s entitetom 'PrivatniKorisnik', gdje 'user_id' označava strani ključ

    @OneToMany(mappedBy = "prijava", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Odgovor> answers;

    public List<Odgovor> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Odgovor> answers) {
        this.answers = answers;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "gameId", insertable = false, updatable = false)
    private Igra game;


    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private PrivatniKorisnik privateUser; // Privatni korisnik koji je podnio prijavu

    // Default konstruktor
    public Prijava() {}

    // Konstruktor s argumentima za privatnog korisnika i ispunjeni dokument


    public Prijava(List<Odgovor> answers, Igra game, PrivatniKorisnik privateUser) {
        this.id = new PrijavaId(game.getId(), privateUser.getUserId());
        this.answers = answers;
        this.game = game;
        this.privateUser = privateUser;
    }

    // Getter za privatnog korisnika
    public PrivatniKorisnik getPrivateUser() {
        return privateUser;
    }



    // Metoda za ispis objekta prijave u formatu 'user: <username>, document: <docName>'
    @Override
    public String toString() {
        return "Form { user: " + privateUser.getUsername() + " }";
    }


    @Embeddable
    public class PrijavaId implements Serializable {

        private Long gameId;  // Foreign key to Igra
        private int userId;  // Foreign key to PrivatniKorisnik

        public PrijavaId() {
        }

        public PrijavaId(Long gameId, int userId) {
            this.gameId = gameId;
            this.userId = userId;
        }
        public Long getGameId() {
            return gameId;
        }
        public void setGameId(Long gameId) {
            this.gameId = gameId;
        }

        public int getUserId() {
            return userId;
        }
        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
