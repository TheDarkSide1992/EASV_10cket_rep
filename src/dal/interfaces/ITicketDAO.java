package dal.interfaces;

import be.Request;
import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.List;

public interface ITicketDAO {
    List<Ticket> getTickets(int eventID) throws SQLException;

    void saveTickets(List<Ticket> ticketsForSale, int eventID) throws SQLException;

    void sendRequest(Request request) throws SQLServerException;

    List<Request> getRequests() throws SQLException;

    void ticketSentToCustomer(Request selectedItem) throws SQLException;

    void paymentProcessed(Request selectedItem) throws SQLException;
}
