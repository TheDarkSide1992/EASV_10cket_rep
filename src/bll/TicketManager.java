package bll;

import be.Request;
import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.TicketDAO;
import dal.interfaces.ITicketDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TicketManager {



    ITicketDAO ticketDAO = new TicketDAO();

    public TicketManager() throws IOException {
    }

    public List<Ticket> getTickets(int eventID) throws SQLException {
        return ticketDAO.getTickets(eventID);
        }

    public void saveTickets(List<Ticket> ticketsForSale, int eventID) throws SQLException {
        ticketDAO.saveTickets(ticketsForSale, eventID);
    }

    public void sendRequest(Request request) throws SQLServerException {
        ticketDAO.sendRequest(request);
    }

    public List<Request> getRequests() throws SQLException {
        return ticketDAO.getRequests();
    }

    public void ticketSentToCustomer(Request selectedItem) throws SQLException {
        ticketDAO.ticketSentToCustomer(selectedItem);
    }

    public void paymentProcessed(Request selectedItem) throws SQLException {
        ticketDAO.paymentProcessed(selectedItem);
    }
}

