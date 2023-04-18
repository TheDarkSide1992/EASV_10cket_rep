package dal;

import be.Ticket;
import dal.interfaces.ITicketDAO;

import java.io.IOException;
import java.sql.*;
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
            String sql = "SELECT * FROM Ticket WHERE Ticket_Event_ID = " + eventID + "ORDER BY Ticket_Contains";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String ticketContains = rs.getString("Ticket_Contains");
                int ticketPrice = rs.getInt("Ticket_Price");
                int ticketAmount = rs.getInt("Ticket_AmountOfThisTicketType");


                Ticket ticket = new Ticket(ticketContains, ticketPrice, ticketAmount);

                tickets.add(ticket);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Could not get events from database");
        }
        return tickets;
    }

    @Override
    public void saveTickets(List<Ticket> ticketsForSale, int eventID) throws SQLException {

        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO Ticket (Ticket_Event_ID, Ticket_Contains, Ticket_Price, Ticket_AmountOfThisTicketType) VALUES(?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < ticketsForSale.size(); i++) {
                for (int j = 0; j < ticketsForSale.get(i).getAmountOfTickets(); j++) {
                    Ticket t = ticketsForSale.get(i);
                    ps.setInt(1, eventID);
                    ps.setString(2, t.getTicketContains());
                    ps.setInt(3, t.getTicketPrice());
                    ps.setInt(4, t.getAmountOfTickets());
                    ps.addBatch();

                }
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Could not get events from database");
        }
    }

}

