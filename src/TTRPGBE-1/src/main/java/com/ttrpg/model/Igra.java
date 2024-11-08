package com.ttrpg.model;
import java.sql.Timestamp;

import jakarta.persistence.*;
@Entity
@Table(name = "Igra")  // The name of the table in the database
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class Igra {

	
	
	
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "game_id")
	    private Integer gameId;

	    @Column(name = "game_name", length = 50, nullable = false)
	    private String gameName;

	    @Column(name = "game_des", length = 400)
	    private String gameDes;

	    @Column(name = "max_player")
	    private Integer maxPlayer;

	    @Column(name = "is_private")
	    private Boolean isPrivate;

	    @Column(name = "is_homebrew")
	    private Boolean isHomebrew;

	    @Column(name = "requires_form")
	    private Boolean requiresForm;

	    @Column(name = "start_ts")
	    private Timestamp startTs;

	    @Column(name = "est_length", length = 30)
	    private String estLength;

	    @Column(name = "rec_exp", length = 20)
	    private String recExp;

	    @Column(name = "comm_channel", length = 100)
	    private String commChannel;

	    @Column(name = "ruleset_id")
	    private Integer rulesetId;

	    @Column(name = "sys_id")
	    private Integer sysId;

	    @Column(name = "gm_user_id")
	    private Integer gmUserId;

	    @Column(name = "template_loc", length = 50)
	    private String templateLoc;
	
	

}