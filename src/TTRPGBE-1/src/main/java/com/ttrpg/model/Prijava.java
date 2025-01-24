package com.ttrpg.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
    @JoinColumn(name = "prijavaGameId",referencedColumnName = "gameId", insertable = false, updatable = false)
    private Igra game;


    @ManyToOne
    @JoinColumn(name = "prijavaUserId",referencedColumnName = "userId" ,insertable = false, updatable = false)
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
    public PrijavaId getPrijavaId() {
        return id;
    }
    public void addAnswer(Odgovor answer) {
        this.answers.add(answer);
    }



    // Metoda za ispis objekta prijave u formatu 'user: <username>, document: <docName>'
    @Override
    public String toString() {
        return "Form { user: " + privateUser.getUsername() + " }";
    }


    @Embeddable
    public class PrijavaId implements Serializable {
        @Column(name = "prijavaGameId")
        private Long gameId;  // Foreign key to Igra
        @Column(name = "prijavaUserId")
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

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            PrijavaId prijavaId = (PrijavaId) o;
            return getUserId() == prijavaId.getUserId() && Objects.equals(getGameId(), prijavaId.getGameId());
        }

        @Override
        public int hashCode() {
            int result = Objects.hashCode(getGameId());
            result = 31 * result + getUserId();
            return result;
        }
    }
}
