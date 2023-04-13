package dal;

import be.Event;
import be.Ticket;
import dal.interfaces.ITicketDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements ITicketDAO {

    private DBConnector db;

    public TicketDAO() throws IOException {
        db = new DBConnector();
    }
    @Override
    public List<Ticket> getTickets(int eventID) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM Ticket WHERE Ticket_Event_ID = " + eventID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int ticketID = rs.getInt("Ticket_Content_ID");
                String ticketContains = rs.getString("Ticket_Contains");
                int ticketPrice = rs.getInt("Ticket_Price");


                Ticket ticket = new Ticket(ticketID, ticketContains, ticketPrice);

                tickets.add(ticket);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Could not get events from database");
    }
    return tickets;
    }

}

