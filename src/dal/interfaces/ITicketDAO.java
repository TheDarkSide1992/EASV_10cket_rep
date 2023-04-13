package dal.interfaces;

import be.Event;
import be.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketDAO {
    List<Ticket> getTickets(int eventID) throws SQLException;
}
