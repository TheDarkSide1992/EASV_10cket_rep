package be;

import java.sql.Date;
import java.sql.Time;

public class Event {

    private int eventID;
    private String eventTitle;
    private Date eventDate;
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

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
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
