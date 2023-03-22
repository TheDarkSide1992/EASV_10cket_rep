package be;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {
    public Event(String eventTitle, LocalDate eventDate, Time eventStartTime, String eventLocation, String eventDescription) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
    }

    private int eventID;
    private String eventTitle;
    private LocalDate eventDate;
    private Time eventStartTime;
    private String eventLocation;
    private String eventDescription;
    private int eventTicketAmount;


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
}
