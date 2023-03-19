package be;

import javafx.scene.image.Image;

public class Ticket {

    private int ticketID;
    private int ticketPrice;

    private Image ticketBarCode;

    private Image ticketQRCode;

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
}
