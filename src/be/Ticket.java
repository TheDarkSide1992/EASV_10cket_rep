package be;

import javafx.scene.image.Image;

public class Ticket {

    private int ticketID;
    private int ticketPrice;

    private String ticketContains;

    private Image ticketBarCode;

    private Image ticketQRCode;

    private int amountOfTickets;

    public Ticket(int ticketID, String ticketContains, int ticketPrice) {
        this.ticketID = ticketID;
        this.ticketContains = ticketContains;
        this.ticketPrice =  ticketPrice;
    }

    public Ticket(String ticketContains, int ticketPrice, int amountOfTickets) {
        this.ticketContains = ticketContains;
        this.ticketPrice =  ticketPrice;
        this.amountOfTickets = amountOfTickets;

    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Image getTicketBarCode() {
        return ticketBarCode;
    }

    public void setTicketBarCode(Image ticketBarCode) {
        this.ticketBarCode = ticketBarCode;
    }

    public Image getTicketQRCode() {
        return ticketQRCode;
    }

    public void setTicketQRCode(Image ticketQRCode) {
        this.ticketQRCode = ticketQRCode;
    }

    public String getTicketContains() {
        return ticketContains;
    }

    public void setTicketContains(String ticketContains) {
        this.ticketContains = ticketContains;
    }
    public int getAmountOfTickets() {
        return amountOfTickets;
    }

    public void setAmountOfTickets(int amountOfTickets) {
        this.amountOfTickets = amountOfTickets;
    }

}
