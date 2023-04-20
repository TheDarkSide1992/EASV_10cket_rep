package be;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.sql.Time;
import java.time.LocalDate;

public class Event implements Comparable<Event>{
    private int eventID;
    private String eventTitle;
    private LocalDate eventDate;
    private Time eventStartTime;
    private String eventLocation;
    private String eventLocationURL;
    private String eventDescription;

    private String eventCollaborator;

    private String eventCoordinator;
    private int eventTicketAmount;
    private int ticketsSold;
    private boolean eventIsActive;
    private Image eventImage;
    private byte[]  imageBytes;
    public Event(int eventID, String eventTitle, LocalDate eventDate, Time eventStartTime, String eventLocation, String eventLocationURL, String eventDescription, boolean eventIsActive, String eventCollaborator, String eventCoordinator) {
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventLocation = eventLocation;
        this.eventLocationURL = eventLocationURL;
        this.eventDescription = eventDescription;
        this.eventCollaborator = eventCollaborator;
        this.eventCoordinator = eventCoordinator;
        this.eventIsActive = eventIsActive;
    }

    public Event(String eventTitle, LocalDate eventDate, Time eventStartTime, String eventLocation, String eventLocationURL, String eventDescription, boolean eventIsActive) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventLocation = eventLocation;
        this.eventLocationURL = eventLocationURL;
        this.eventDescription = eventDescription;
        this.eventIsActive = eventIsActive;
    }

    public Event(String eventTitle, LocalDate eventDate) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
    }

    public Event(String title, String location, String locationURL, String eventCollaborator, LocalDate date, Time startTime, String description, int eventTicketTotal, int eventTicketsSold, boolean isActive, byte[] eventImage) {
        this.eventTitle = title;
        this.eventDate = date;
        this.eventStartTime = startTime;
        this.eventLocation = location;
        this.eventLocationURL = locationURL;
        this.eventDescription = description;
        this.eventCollaborator = eventCollaborator;
        this.eventIsActive = isActive;
        this.eventTicketAmount = eventTicketTotal;
        this.ticketsSold = eventTicketsSold;
        this.imageBytes = eventImage;
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
        if(eventDate.isAfter(LocalDate.now())){
            return eventIsActive;
        }
        else{
            return false;
        }

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
    public byte[] getImageByte() throws Exception{
        return imageBytes;
    }

    public void setByteImage(byte[] byteImage) {
        imageBytes = byteImage;
    }

    public void setImageWithByte(byte[] byteImage) throws Exception{
        Image img = new Image(new ByteArrayInputStream(byteImage));
        eventImage = img;
    }

    public String getEventCollaborator() {
        return eventCollaborator;
    }

    public void setEventCollaborator(String eventCollaborator) {
        this.eventCollaborator = eventCollaborator;
    }

    public String getEventCoordinator() {
        return eventCoordinator;
    }

    public void setEventCoordinator(String eventCoordinator) {
        this.eventCoordinator = eventCoordinator;
    }

    @Override
    public int compareTo(Event e) {
        if(this.getEventDate().isBefore(e.getEventDate())){
            return -1;
        } else if (this.getEventDate().isEqual(e.getEventDate())) {
            return 0;
        }
        else {
            return 1;
        }


    }

    public String getEventLocationURL() {
        return eventLocationURL;
    }

    public void setEventLocationURL(String eventLocationURL) {
        this.eventLocationURL = eventLocationURL;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }
}
