package com.ttrpg.model;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	    @Column(name = "title", length = 50, nullable = false)
	   
	    private String title;

	    @Column(name = "game_des", length = 400)
	    @JsonProperty("description")
	    private String gameDes;

	    @Column(name = "max_player")
	    @JsonProperty("maxPlayerCount")
	    private Integer maxPlayer;

	    @Column(name = "is_private")
	    private Boolean isPrivate;

	    @Column(name = "is_homebrew")
	    private Boolean isHomebrew;

	    @Column(name = "requires_form")
	    @JsonProperty("applicationRequired")
	    private Boolean requiresForm;

	    @Column(name = "start_ts")
	    @JsonProperty("startTimeStamp")
	    private String startTs;

	    @Column(name = "est_length", length = 30)
	    @JsonProperty("estimatedLength")
	    private String estLength;

	    @Column(name = "rec_exp", length = 20)
	    private String recExp;

	    @Column(name = "comm_channel", length = 100)
	    @JsonProperty("communicationChannel")
	    private String commChannel;

	    @Column(name = "ruleset_id")
	    private Integer rulesetId;

	    @Column(name = "sys_id")
	    private Integer sysId;

	    @Column(name = "gm_user_id")
	    private Integer gmUserId;

	    @Column(name = "template_loc", length = 50)
	    @JsonProperty("location")
	    private String templateLoc;
	    
	    @Column 

		public Integer getId() {
			return gameId;
		}

		public void setGameId(Integer gameId) {
			this.gameId = gameId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String gameName) {
			this.title = gameName;
		}

		public String getDescription() {
			return gameDes;
		}

		public void setGameDes(String gameDes) {
			this.gameDes = gameDes;
		}

		public Integer getMaxPlayer() {
			return maxPlayer;
		}

		public void setMaxPlayer(Integer maxPlayer) {
			this.maxPlayer = maxPlayer;
		}

		public Boolean getIsPrivate() {
			return isPrivate;
		}

		public void setIsPrivate(Boolean isPrivate) {
			this.isPrivate = isPrivate;
		}

		public Boolean getIsHomebrew() {
			return isHomebrew;
		}

		public void setIsHomebrew(Boolean isHomebrew) {
			this.isHomebrew = isHomebrew;
		}

		public Boolean getRequiresForm() {
			return requiresForm;
		}

		public void setRequiresForm(Boolean requiresForm) {
			this.requiresForm = requiresForm;
		}

		public String getStartTimestamp() {
			return startTs;
		}

		public void setStartTs(String startTs) {
			this.startTs = startTs;
		}

		public String getEstimatedLength() {
			return estLength;
		}

		public void setEstLength(String estLength) {
			this.estLength = estLength;
		}

		public String getRecExp() {
			return recExp;
		}

		public void setRecExp(String recExp) {
			this.recExp = recExp;
		}

		public String getCommunicationChannel() {
			return commChannel;
		}

		public void setCommChannel(String commChannel) {
			this.commChannel = commChannel;
		}

		public Integer getRulesetId() {
			return rulesetId;
		}

		public void setRulesetId(Integer rulesetId) {
			this.rulesetId = rulesetId;
		}

		public Integer getSysId() {
			return sysId;
		}

		public void setSysId(Integer sysId) {
			this.sysId = sysId;
		}

		public Integer getGmUserId() {
			return gmUserId;
		}

		public void setGmUserId(Integer gmUserId) {
			this.gmUserId = gmUserId;
		}

		public String getTemplateLoc() {
			return templateLoc;
		}

		public void setTemplateLoc(String templateLoc) {
			this.templateLoc = templateLoc;
		}

		public Igra(Integer gameId, String title, String gameDes, Integer maxPlayer, Boolean isPrivate,
				Boolean isHomebrew, Boolean requiresForm, String startTs, String estLength, String recExp,
				String commChannel, Integer rulesetId, Integer sysId, Integer gmUserId, String templateLoc) {
			super();
			this.gameId = gameId;
			this.title = title;
			this.gameDes = gameDes;
			this.maxPlayer = maxPlayer;
			this.isPrivate = isPrivate;
			this.isHomebrew = isHomebrew;
			this.requiresForm = requiresForm;
			this.startTs = startTs;
			this.estLength = estLength;
			this.recExp = recExp;
			this.commChannel = commChannel;
			this.rulesetId = rulesetId;
			this.sysId = sysId;
			this.gmUserId = gmUserId;
			this.templateLoc = templateLoc;
		}
	
	 public Igra() {}

}
