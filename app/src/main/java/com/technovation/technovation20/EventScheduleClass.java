package com.technovation.technovation20;

/**
 * Created by Anshuman-HP on 02-03-2018.
 */

public class EventScheduleClass  {
    String eventName;
    String eventImage;
    String eventTiming;
    String eventVenue;
    String eventType;

    public EventScheduleClass() {
    }

    public EventScheduleClass(String eventName, String eventImage, String eventTiming, String eventVenue, String eventType) {
        this.eventName = eventName;
        this.eventImage = eventImage;
        this.eventTiming = eventTiming;
        this.eventVenue = eventVenue;
        this.eventType = eventType;
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

    public String getEventTiming() {
        return this.eventTiming;
    }

    public void setEventTiming(String eventTiming) {
        this.eventTiming = eventTiming;
    }

    public String getEventVenue() {
        return this.eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}

