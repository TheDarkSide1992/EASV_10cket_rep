package dal;

import be.Event;

import dal.interfaces.IEventDAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements IEventDAO {
    private DBConnector db;

    public EventDAO() throws IOException {
        db = new DBConnector();
    }


    @Override
    public List<Event> getAllEvents() throws SQLException {
        ArrayList<Event> allEvents = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM Event_ JOIN User_ ON Event_Event_Coordinator_ID = User_ID;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Event_ID");
                String title = rs.getString("Event_Title");
                LocalDate date = rs.getDate("Event_Date").toLocalDate();
                Time startTime = rs.getTime("Event_Start_Time");
                String location = rs.getString("Event_Location");
                String locationURL = rs.getString("Event_LocationURL");
                String description = rs.getString("Event_Description");
                String eventCollaborator = rs.getString("Event_Authors");
                String eventCoordinator = rs.getString("User_Name");
                byte[] data = rs.getBytes("Event_Img");

                boolean isActive = true;
                switch (rs.getInt("Event_Is_Active")) {
                    case 0:
                        isActive = false;
                }


                Event event = new Event(id, title, date, startTime, location, locationURL, description, isActive, eventCollaborator, eventCoordinator);

                if (data != null) {
                    event.setByteImage(data);
                    event.setImageWithByte(data);
                }

                allEvents.add(event);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Could not get events from database");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allEvents;
    }

    @Override
    public List<Event> getSubmittedForDeletion() throws SQLException {
        ArrayList<Event> submittedForDeletion = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM Event_ JOIN User_ ON Event_Event_Coordinator_ID = User_ID WHERE Event_ID IN (SELECT DISTINCT Submit_Delete_Event FROM Submitted_For_Deletion);";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Event_ID");
                String title = rs.getString("Event_Title");
                LocalDate date = rs.getDate("Event_Date").toLocalDate();
                Time startTime = rs.getTime("Event_Start_Time");
                String location = rs.getString("Event_Location");
                String locationURL = rs.getString("Event_LocationURL");
                String description = rs.getString("Event_Description");
                String eventCollaborator = rs.getString("Event_Authors");
                String eventCoordinator = rs.getString("User_Name");
                byte[] data = rs.getBytes("Event_Img");

                boolean isActive = false;
                switch (rs.getInt("Event_Is_Active")) {
                    case 0:
                }


                Event event = new Event(id, title, date, startTime, location, locationURL, description, isActive, eventCollaborator, eventCoordinator);

                if (data != null) {
                    event.setByteImage(data);
                    event.setImageWithByte(data);
                }

                submittedForDeletion.add(event);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Could not get events submitted for deletion from database");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return submittedForDeletion;
    }

    @Override
    public int createEvent(Event event) throws Exception {
        int id = 0;
        try (Connection conn = db.getConnection()) {
            //String sql = "INSERT INTO Event_ (Event_Title, Event_Location, Event_Event_Coordinator_ID, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Ticket_Sold, Event_Is_Active) Values(?,?,?,?,?,?,?,?,?);";
            String sql = "INSERT INTO Event_ (Event_Title, Event_Location, Event_LocationURL, Event_Event_Coordinator_ID, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Ticket_Sold, Event_Is_Active, Event_Img) Values(?,?,?,?,?,?,?,?,?,?,?);";


            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, event.getEventTitle());
            stmt.setString(2, event.getEventLocation());
            stmt.setString(3, event.getEventLocationURL());
            stmt.setInt(4, 1);
            stmt.setDate(5, Date.valueOf(event.getEventDate()));
            stmt.setTime(6, event.getEventStartTime());
            stmt.setString(7, event.getEventDescription());
            stmt.setInt(8, 0);
            stmt.setInt(9, 0);
            stmt.setBoolean(10, event.isEventIsActive());
            stmt.setBytes(11, event.getImageByte());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create Event" + ex);
        }
        return id;
    }

    @Override
    public void deleteEvent(int id) throws SQLException {

        String sql = "DELETE FROM Event_ WHERE Event_ID = " + id + ";";

        try (Connection conn = db.getConnection()) {

            //Statements are prepared SQL statements
            PreparedStatement ps = conn.prepareStatement(sql);

            //Execute the update which removes the link between song and playlist first, then remove the song from the DB
            ps.executeUpdate();
        }

    }

    @Override
    public void cancelEvent(int id) throws SQLException {

        String sql = "UPDATE Event_ SET Event_Is_Active = '0' WHERE Event_ID = " + id + ";";

        try (Connection conn = db.getConnection()) {

            //Statements are prepared SQL statements
            PreparedStatement ps = conn.prepareStatement(sql);

            //Execute the update which removes the link between song and playlist first, then remove the song from the DB
            ps.executeUpdate();

        }

    }

    @Override
    public int requestToDeleteEventCoordinator(Event eventToBeDeleted) throws Exception {
        int id = 0;
        try (Connection conn = db.getConnection()) {

            String sql = "INSERT INTO Submitted_For_Deletion (Submit_Delete_Event) Values(?);";


            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, eventToBeDeleted.getEventID());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not submit Event for deletion" + ex);
        }
        return id;
    }

    @Override
    public Event getEvent(String eventName, LocalDate eventDate) throws SQLException {
        Event event = null;
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * From Event_ WHERE Event_Title = ? AND Event_Date = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, eventName);
            ps.setDate(2, Date.valueOf(eventDate));
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String location = rs.getString("Event_Location");
                String locationURL = rs.getString("Event_LocationURL");
                String eventCollaborator = rs.getString("Event_Authors");;
                Time startTime = rs.getTime("Event_Start_Time");
                String description = rs.getString("Event_Description");
                int eventTicketTotal = rs.getInt("Event_Ticket_Total");
                int eventTicketsSold = rs.getInt("Event_Ticket_Sold");
                boolean isActive = true;
                byte[] eventImage = rs.getBytes("Event_Img");



                event = new Event(eventName, location, locationURL, eventCollaborator, eventDate, startTime, description, eventTicketTotal, eventTicketsSold, isActive, eventImage);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Could not submit Event for deletion" + ex);
        }
        return event;
    }
}
