package be;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;

public class Request {

    private int requestID;
    private String eventTitle;
    private LocalDate eventDate;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private int tickets;
    private String typeOfTicket;
    private int ticketPrice;
    private int ticketID;
    private int event_ID;
    private boolean paymentReceived;
    private boolean ticketSentToCustomer;
    public Request(int requestID, String eventTitle, LocalDate eventDate, String customerName, String customerEmail, String customerPhone, int tickets, String typeOfTicket, int ticketPrice, boolean paymentReceived, boolean ticketSentToCustomer, int event_ID) {
        this.requestID = requestID;
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.tickets = tickets;
        this.typeOfTicket = typeOfTicket;
        this.ticketPrice = ticketPrice;
        this.paymentReceived = paymentReceived;
        this.ticketSentToCustomer = ticketSentToCustomer;
        this.event_ID = event_ID;
    }

    public Request(String customerName, int tickets, String typeOfTicket, String customerPhone, String customerEmail, int event_ID) {
        this.customerName = customerName;
        this.tickets = tickets;
        this.typeOfTicket = typeOfTicket;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.event_ID = event_ID;
    }

    public boolean isPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(boolean paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public boolean isTicketSentToCustomer() {
        return ticketSentToCustomer;
    }

    public void setTicketSentToCustomer(boolean ticketSentToCustomer) {
        this.ticketSentToCustomer = ticketSentToCustomer;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }


    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public String getTypeOfTicket() {
        return typeOfTicket;
    }

    public void setTypeOfTicket(String typeOfTicket) {
        this.typeOfTicket = typeOfTicket;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getEvent_ID() {
        return event_ID;
    }

    public void setEvent_ID(int event_ID) {
        this.event_ID = event_ID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }


}
