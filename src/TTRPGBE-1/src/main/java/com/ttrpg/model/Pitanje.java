package com.ttrpg.model;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Pitanje {

    @EmbeddedId
    private PitanjeId id;
    private String questionText;
    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)  // Foreign key to User
    private Igra game;

    public Pitanje(String questionText, Igra game) {
        this.id = new PitanjeId(game.getId(), questionText);
        this.game = game;
        this.questionText = questionText;
    }

    public Pitanje() {

    }
    @Embeddable
    public class PitanjeId implements Serializable {
        private Long gameId;
        private String questionText;

        public PitanjeId(Long gameId, String questionText) {
            this.gameId = gameId;
            this.questionText = questionText;
        }

        public PitanjeId() {

        }
    }


}
