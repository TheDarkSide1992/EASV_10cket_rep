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
    public List<Event> getAllEvents() throws Exception {
        ArrayList<Event> allActiveEvents = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM Events WHERE isActive = 1;";
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
        return 0;
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
