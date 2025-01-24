package com.ttrpg.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
@Getter
@Entity
public class Pitanje {

    @EmbeddedId
    private PitanjeId id;
    private String questionText;
    @ManyToOne
    //@MapsId("gameId")
    @JoinColumn(name = "pitanjeGameId", referencedColumnName = "gameId", nullable = false)  // Foreign key to User
    private Igra game;

    public Pitanje(String questionText, Igra game) {
        this.id = new PitanjeId(game.getId(), questionText);
        this.game = game;
        this.questionText = questionText;
    }

    public Pitanje() {

    }
    @Embeddable
    public static class PitanjeId implements Serializable {
        @Column(name="pitanjeGameId")
        private Long gameId;
        @Column(name="questionText")
        private String questionText;

        public PitanjeId(Long gameId, String questionText) {
            this.gameId = gameId;
            this.questionText = questionText;
        }

        public PitanjeId() {

        }
        public Long getGameId() {
            return gameId;
        }
        public String getQuestionText() {
            return questionText;
        }
    }


}
