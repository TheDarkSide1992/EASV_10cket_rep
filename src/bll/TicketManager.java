package bll;

import be.Event;
import be.Ticket;
import dal.TicketDAO;
import dal.interfaces.ITicketDAO;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketManager {

    ITicketDAO ticketDAO = new TicketDAO();

    public TicketManager() throws IOException {
    }

    public List<Ticket> getTickets(int eventID) throws SQLException {
        return ticketDAO.getTickets(eventID);
    }
}
