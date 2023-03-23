package dal;

import be.Event;
import dal.interfaces.IEventDAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements IEventDAO {

    public EventDAO() throws IOException {
        db = new DBConnector();
    }

    private DBConnector db;

    @Override
    public List<Event> getAllEvents() throws Exception {
        ArrayList<Event> allActiveEvents = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM Event WHERE Event_Is_Active = 1;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String title = rs.getString("Event_Title");
                String location = rs.getString("Event_Location");
                LocalDate date = rs.getDate("Event_Date").toLocalDate();
                Time startTime = rs.getTime("Event_Start_Time");
                String description = rs.getString("Event_Description");
                boolean isActive = true;

                Event event = new Event(title, date, startTime, location, description, isActive);
                allActiveEvents.add(event);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get events from database");
        }
        return allActiveEvents;
    }

    @Override
    public int createEvent(Event event) throws Exception {
        int id = 0;
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO Event (Event_Title, Event_Location, Event_Event_Coordinator_ID, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Ticket_Sold, Event_Is_Active) Values(?,?,?,?,?,?,?,?,?);";


            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, event.getEventTitle());
            stmt.setString(2, event.getEventLocation());
            stmt.setInt(3, 1);
            stmt.setDate(4, Date.valueOf(event.getEventDate()));
            stmt.setTime(5, event.getEventStartTime());
            stmt.setString(6, event.getEventDescription());
            stmt.setInt(7, 0);
            stmt.setInt(8, 0);
            stmt.setBoolean(9, event.isEventIsActive());

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
    public boolean deleteEvent(int id) throws Exception {
        return false;
    }

    @Override
    public boolean cancelEvent(int id) throws Exception {
        return false;
    }
}
