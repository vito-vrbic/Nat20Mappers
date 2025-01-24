package com.ttrpg.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Odgovor {
    @EmbeddedId
    private OdgovorId id;  // Composite primary key
    //private String answerText;

    @ManyToOne
    //@MapsId("prijavaId")
    @JoinColumns({
            @JoinColumn(name = "prijavaGameId", referencedColumnName = "prijavaGameId", insertable = false, updatable = false),
            @JoinColumn(name = "prijavaUserId", referencedColumnName = "prijavaUserId", insertable = false, updatable = false)
    })
    private Prijava prijava;

    @ManyToOne
    //@MapsId("pitanjeId") // Maps this part of the embedded ID to the Prijava relationship
    @JoinColumns({
            @JoinColumn(name = "pitanjeGameId", referencedColumnName = "pitanjeGameId",insertable = false, updatable = false),
            @JoinColumn(name = "questionText", referencedColumnName = "questionText",insertable = false, updatable = false)
    })
    private Pitanje pitanje;

    public Odgovor() {

    }

    public OdgovorId getOdgovorId() {
        return id;
    }
    public Odgovor(Pitanje pitanje, String answerText, Prijava prijava) {
        this.prijava = prijava;
        //this.answerText = answerText;
        this.pitanje = pitanje;
        this.id = new OdgovorId(pitanje.getId(), answerText, prijava.getPrijavaId());
    }

    @Embeddable
    public class OdgovorId implements Serializable {
        @Embedded
        private Pitanje.PitanjeId pitanjeId;
        @Embedded
        private Prijava.PrijavaId prijavaId;
        @Column(name="answerText")
        private String answerText;
        public OdgovorId() {}
        public OdgovorId(Pitanje.PitanjeId pitanjeId, String answerText, Prijava.PrijavaId prijavaId) {
            this.pitanjeId = pitanjeId;
            this.answerText = answerText;
            this.prijavaId = prijavaId;

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

        public Prijava.PrijavaId getPrijavaId() {
            return prijavaId;
        }
        public void setPrijavaId(Prijava.PrijavaId prijavaId) {
            this.prijavaId = prijavaId;
        }
    }
}
