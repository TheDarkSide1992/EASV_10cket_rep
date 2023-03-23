package be;

import javafx.scene.image.Image;


import java.sql.Time;
import java.time.LocalDate;

public class Event {
    private int eventID;
    private String eventTitle;
    private LocalDate eventDate;
    private Time eventStartTime;
    private String eventLocation;
    private String eventDescription;
    private int eventTicketAmount;
    private boolean eventIsActive;
    private Image eventImage;
    public Event(String eventTitle, LocalDate eventDate, Time eventStartTime, String eventLocation, String eventDescription, boolean eventIsActive) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.eventIsActive = eventIsActive;
    }


    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Time eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getEventTicketAmount() {
        return eventTicketAmount;
    }

    public void setEventTicketAmount(int eventTicketAmount) {
        this.eventTicketAmount = eventTicketAmount;
    }

    public boolean isEventIsActive() {
        return eventIsActive;
    }

    public void setEventIsActive(boolean eventIsActive) {
        this.eventIsActive = eventIsActive;
    }

    public Image getEventImage() {
        return eventImage;
    }
    public void setEventImage(Image eventImage) {
        this.eventImage = eventImage;
    }

}
