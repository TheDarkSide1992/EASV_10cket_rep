package dal.interfaces;

import be.Request;
import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITicketDAO {
    List<Ticket> getTickets(int eventID) throws SQLException;

    void saveTickets(List<Ticket> ticketsForSale, int eventID) throws SQLException;

    void sendRequest(Request request, List<Integer> ticketID) throws SQLException;

    List<Request> getRequests() throws SQLException;

    void ticketSentToCustomer(Request selectedItem) throws SQLException;

    void paymentProcessed(Request selectedItem) throws SQLException;

    Ticket getTicket(String ticketContains) throws SQLException;

    void allocateTicketToRequest(Request request) throws SQLException;

    //List<Request> optimizeRequests() throws SQLException;
}
