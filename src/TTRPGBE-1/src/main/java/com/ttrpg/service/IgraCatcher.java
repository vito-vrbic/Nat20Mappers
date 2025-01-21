package com.ttrpg.service;
import java.util.Map;
import java.util.Objects;
public class IgraCatcher {


	    private String id;
	    private String title;
	    private String type;
	    private Location location; 
	     private String timezone;
	    private String availability; 
	     private String createdBy; 
	    private boolean applicationRequired;
	    private String complexity; 
	    private String estimatedLength;
	    private String startTimestamp;
	    private String description;
	    private String pravilnik; 
	    	private boolean requiresForm;
	    private Map<String, String> formQuestions; 
	    private int currentPlayerCount;
	    private String maxPlayerCount;
	    private String communicationChannel;
	    private boolean isHomebrew;

	    public static class Location {
	        private Double lat; 
	        private Double lng; 

	        public Location(Double lat, Double lng) {
	            this.lat = lat;
	            this.lng = lng;
	        }

	        // Getters and Setters
	        public Double getLat() {
	            return lat;
	        }

	        public void setLat(Double lat) {
	            this.lat = lat;
	        }

	        public Double getLng() {
	            return lng;
	        }

	        public void setLng(Double lng) {
	            this.lng = lng;
	        }

	        @Override
	        public boolean equals(Object o) {
	            if (this == o) return true;
	            if (o == null || getClass() != o.getClass()) return false;
	            Location location = (Location) o;
	            return Objects.equals(lat, location.lat) && Objects.equals(lng, location.lng);
	        }

	        @Override
	        public int hashCode() {
	            return Objects.hash(lat, lng);
	        }

	        @Override
	        public String toString() {
	            return "Location{" +
	                    "lat=" + lat +
	                    ", lng=" + lng +
	                    '}';
	        }
	    }

	   
	    public IgraCatcher(){
	    }

	    public IgraCatcher(String id, String title, String type, Location location, String timezone, String availability,
	                String createdBy, boolean applicationRequired, String complexity, String estimatedLength,
	                String startTimestamp, String description, String pravilnik, boolean requiresForm,
	                Map<String, String> formQuestions, int currentPlayerCount, String maxPlayerCount,
	                String communicationChannel, boolean isHomebrew) {
	        this.id = id;
	        this.title = title;
	        this.type = type;
	        this.location = location;
	        this.timezone = timezone;
	        this.availability = availability;
	        this.createdBy = createdBy;
	        this.applicationRequired = applicationRequired;
	        this.complexity = complexity;
	        this.estimatedLength = estimatedLength;
	        this.startTimestamp = startTimestamp;
	        this.description = description;
	        this.pravilnik = pravilnik;
	        this.requiresForm = requiresForm;
	        this.formQuestions = formQuestions;
	        this.currentPlayerCount = currentPlayerCount;
	        this.maxPlayerCount = maxPlayerCount;
	        this.communicationChannel = communicationChannel;
	        this.isHomebrew = isHomebrew;
	    }

	    // Getters and Setters
	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public Location getLocation() {
	        return location;
	    }

	    public void setLocation(Location location) {
	        this.location = location;
	    }

	    public String getTimezone() {
	        return timezone;
	    }

	    public void setTimezone(String timezone) {
	        this.timezone = timezone;
	    }

	    public String getAvailability() {
	        return availability;
	    }

	    public void setAvailability(String availability) {
	        this.availability = availability;
	    }

	    public String getCreatedBy() {
	        return createdBy;
	    }

	    public void setCreatedBy(String createdBy) {
	        this.createdBy = createdBy;
	    }

	    public boolean isApplicationRequired() {
	        return applicationRequired;
	    }

	    public void setApplicationRequired(boolean applicationRequired) {
	        this.applicationRequired = applicationRequired;
	    }

	    public String getComplexity() {
	        return complexity;
	    }

	    public void setComplexity(String complexity) {
	        this.complexity = complexity;
	    }

	    public String getEstimatedLength() {
	        return estimatedLength;
	    }

	    public void setEstimatedLength(String estimatedLength) {
	        this.estimatedLength = estimatedLength;
	    }

	    public String getStartTimestamp() {
	        return startTimestamp;
	    }

	    public void setStartTimestamp(String startTimestamp) {
	        this.startTimestamp = startTimestamp;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getPravilnik() {
	        return pravilnik;
	    }

	    public void setPravilnik(String pravilnik) {
	        this.pravilnik = pravilnik;
	    }

	    public boolean isRequiresForm() {
	        return requiresForm;
	    }

	    public void setRequiresForm(boolean requiresForm) {
	        this.requiresForm = requiresForm;
	    }

	    public Map<String, String> getFormQuestions() {
	        return formQuestions;
	    }

	    public void setFormQuestions(Map<String, String> formQuestions) {
	        this.formQuestions = formQuestions;
	    }

	    public int getCurrentPlayerCount() {
	        return currentPlayerCount;
	    }

	    public void setCurrentPlayerCount(int currentPlayerCount) {
	        this.currentPlayerCount = currentPlayerCount;
}
}