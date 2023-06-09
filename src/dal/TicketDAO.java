package dal;

import be.Request;
import be.Ticket;
import dal.interfaces.ITicketDAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

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
            String sql = "DELETE FROM Ticket WHERE Ticket_Event_ID = " + eventID + ";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Could not modify tickets in Database");
        }
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO Ticket (Ticket_Event_ID, Ticket_Contains, Ticket_Price, Ticket_AmountOfThisTicketType, Ticket_Is_Sold, Ticket_Is_Requested) VALUES(?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < ticketsForSale.size(); i++) {
                for (int j = 0; j < ticketsForSale.get(i).getAmountOfTickets(); j++) {
                    Ticket t = ticketsForSale.get(i);
                    ps.setInt(1, eventID);
                    ps.setString(2, t.getTicketContains());
                    ps.setInt(3, t.getTicketPrice());
                    ps.setInt(4, t.getAmountOfTickets());
                    ps.setBoolean(5, false);
                    ps.setBoolean(6, false);
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Could not save tickets to database");
        }
    }

    @Override
    public void allocateTicketToRequest(Request request) throws SQLException {
        List<Integer> ticketID = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT TOP " + request.getTickets() + " * FROM Ticket WHERE Ticket_Event_ID =" + request.getEvent_ID() + " AND Ticket_Contains = '" + request.getTypeOfTicket() + "' AND Ticket_Is_Sold = 0 AND Ticket_Is_Requested = 0;";
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                ticketID.add(rs.getInt("Ticket_Content_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }

        sendRequest(request, ticketID);
    }

    @Override
    public void sendRequest(Request request, List<Integer> ticketID) throws SQLException {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO Ticket_Request(Event_ID, Customer_Name, Customer_Email, Customer_Phone,"
                    + " Number_Of_Tickets, Payment_Received, Tickets_Sent_To_Customer, Type_Of_Ticket, Ticket_ID) VALUES(?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Integer i : ticketID) {
                ps.setInt(1, request.getEvent_ID());
                ps.setString(2, request.getCustomerName());
                ps.setString(3, request.getCustomerEmail());
                ps.setString(4, request.getCustomerPhone());
                ps.setInt(5, request.getTickets());
                ps.setBoolean(6, false);
                ps.setBoolean(7, false);
                ps.setString(8, request.getTypeOfTicket());
                ps.setInt(9, i);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (
                SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Request> getRequests() throws SQLException {
        List<Request> requests = new ArrayList<>();
        try (Connection conn = db.getConnection()) {

            String sql = """
                    SELECT DISTINCT Request_ID, Event_Title, Event_Date, Customer_Name,
                    Customer_Email, Customer_Phone, Number_Of_Tickets, Type_Of_Ticket,
                    Ticket_Price, Payment_Received, Tickets_Sent_To_Customer, Ticket_Content_ID
                    FROM Ticket_Request
                    JOIN Event_ ON Ticket_Request.Event_ID = Event_.Event_ID
                    JOIN Ticket ON Ticket.Ticket_Content_ID = Ticket_Request.Ticket_ID
                    """;
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                int requestID = rs.getInt("Request_ID");
                String eventTitle = rs.getString("Event_Title");
                LocalDate eventDate = rs.getDate("Event_Date").toLocalDate();
                String customerName = rs.getString("Customer_Name");
                String customerEmail = rs.getString("Customer_Email");
                String customerPhone = rs.getString("Customer_Phone");
                int numOfTickets = rs.getInt("Number_Of_Tickets");
                String typeOfTicket = rs.getString("Type_Of_Ticket");
                int ticketPrice = rs.getInt("Ticket_Price");
                boolean paymentReceived = rs.getBoolean("Payment_Received");
                boolean ticketsSentToCustomer = rs.getBoolean("Tickets_Sent_To_Customer");
                int ticketID = rs.getInt("Ticket_Content_ID");
                Request r = new Request(requestID, eventTitle, eventDate, customerName, customerEmail, customerPhone, numOfTickets, typeOfTicket, ticketPrice, paymentReceived, ticketsSentToCustomer, ticketID);
                requests.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
        System.out.println(requests.size());
        return requests;
    }

    @Override
    public void ticketSentToCustomer(Request selectedItem) throws SQLException {
        try (Connection conn = db.getConnection()) {
            String sql = "UPDATE Ticket_Request SET Tickets_Sent_To_Customer = 1 WHERE Request_ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, selectedItem.getRequestID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException();
        }

    }

    @Override
    public void paymentProcessed(Request selectedItem) throws SQLException {
        try (Connection conn = db.getConnection()) {
            String sql = "UPDATE Ticket_Request SET Payment_Received = 1 WHERE Request_ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, selectedItem.getRequestID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException();
        }

    }

    @Override
    public Ticket getTicket(String ticketContains) throws SQLException {
        Ticket ticket = null;
        try (Connection conn = db.getConnection()) {
            String sql = "Select * FROM Ticket WHERE Ticket_Contains = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ticketContains);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ticketID = rs.getInt("Ticket_Content_ID");
                int ticketPrice = rs.getInt("Ticket_Price");

                ticket = new Ticket(ticketID, ticketContains, ticketPrice);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();

        }
        return ticket;
    }

}

