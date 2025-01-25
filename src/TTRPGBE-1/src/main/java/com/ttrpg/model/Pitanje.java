package com.ttrpg.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
public class Pitanje {

    @EmbeddedId
    private PitanjeId id;
    //private String questionText;
    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "pitanjeGameId", referencedColumnName = "gameId", nullable = false)  // Foreign key to User
    private Igra game;

    public Pitanje(String questionText, Igra game) {
        this.id = new PitanjeId(game.getId(), questionText);
        this.game = game;
        //this.questionText = questionText;
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

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            PitanjeId pitanjeId = (PitanjeId) o;
            return Objects.equals(getGameId(), pitanjeId.getGameId()) && Objects.equals(getQuestionText(), pitanjeId.getQuestionText());
        }

        @Override
        public int hashCode() {
            int result = Objects.hashCode(getGameId());
            result = 31 * result + Objects.hashCode(getQuestionText());
            return result;
        }
    }
	public PitanjeId getId() {
		return id;
	}

	public void setId(PitanjeId id) {
		this.id = id;
	}

	public Igra getGame() {
		return game;
	}

	public void setGame(Igra game) {
		this.game = game;
	}


}
