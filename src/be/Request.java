package be;

public class Request {

    private String customerName;
    private int tickets;
    private String typeOfTicket;
    private String customerPhone;
    private String customerEmail;
    private int event_ID;

    public Request(String customerName, int tickets, String typeOfTicket, String customerPhone, String customerEmail, int event_ID) {
        this.customerName = customerName;
        this.tickets = tickets;
        this.typeOfTicket = typeOfTicket;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.event_ID = event_ID;
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
}
