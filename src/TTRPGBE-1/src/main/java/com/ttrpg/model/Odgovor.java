package com.ttrpg.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Odgovor {
    @EmbeddedId
    private OdgovorId id;  // Composite primary key
    private String answerText;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "gameId", referencedColumnName = "gameId", insertable = false, updatable = false),
            @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    })
    private Prijava prijava;
    @Embeddable
    public class OdgovorId implements Serializable {
        private Pitanje.PitanjeId pitanjeId;
        private String answerText;
        public OdgovorId() {}
        public OdgovorId(Pitanje.PitanjeId id, String answerText) {
            this.pitanjeId = id;
            this.answerText = answerText;

        }
        public Pitanje.PitanjeId getPitanjeId() {
            return pitanjeId;
        }
        public void setPitanjeId(Pitanje.PitanjeId id) {
            this.pitanjeId = id;
        }
        public String getAnswerText() {
            return answerText;
        }
        public void setAnswerText(String answerText) {
            this.answerText = answerText;
        }

    }
}
