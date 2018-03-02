package com.technovation.technovation20;

/**
 * Created by Anshuman-HP on 02-03-2018.
 */

public class EventDetails {
    String eventName;
    String eventImage;
    String eventDetails;
    String eventType;
    String eventCoordinatorName;
    String eventCoordinatorPhone;
    String eventVenue;
    String eventTiming;


    public EventDetails() {
    }

    public EventDetails(String eventName, String eventImage, String eventDetails, String eventType, String eventCoordinatorName, String eventCoordinatorPhone, String eventVenue, String eventTiming) {
        this.eventName = eventName;
        this.eventImage = eventImage;
        this.eventDetails = eventDetails;
        this.eventType = eventType;
        this.eventCoordinatorName = eventCoordinatorName;
        this.eventCoordinatorPhone = eventCoordinatorPhone;
        this.eventVenue = eventVenue;
        this.eventTiming = eventTiming;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventImage() {
        return this.eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventDetails() {
        return this.eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventCoordinatorName() {
        return this.eventCoordinatorName;
    }

    public void setEventCoordinatorName(String eventCoordinatorName) {
        this.eventCoordinatorName = eventCoordinatorName;
    }

    public String getEventCoordinatorPhone() {
        return this.eventCoordinatorPhone;
    }

    public void setEventCoordinatorPhone(String eventCoordinatorPhone) {
        this.eventCoordinatorPhone = eventCoordinatorPhone;
    }

    public String getEventVenue() {
        return this.eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventTiming() {
        return this.eventTiming;
    }

    public void setEventTiming(String eventTiming) {
        this.eventTiming = eventTiming;
    }
}
