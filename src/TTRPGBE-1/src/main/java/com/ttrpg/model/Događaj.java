package com.ttrpg.model;


	import jakarta.persistence.*;
	import java.sql.Timestamp;

	@Entity
	@Table(name = "događaj") 
	public class Događaj {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "event_id")
	    private Long eventId;  

	    @ManyToOne(fetch = FetchType.LAZY) 
	    @JoinColumn(name = "game_id") 
	    private Igra game; 

	    @Column(name = "event_name", length = 50)
	    private String eventName; 

	    @Column(name = "event_timestamp")
	    private Timestamp eventTimestamp;

}
