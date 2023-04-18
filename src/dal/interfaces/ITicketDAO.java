package dal.interfaces;

import be.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketDAO {
    List<Ticket> getTickets(int eventID) throws SQLException;

    void saveTickets(List<Ticket> ticketsForSale, int eventID) throws SQLException;
}
