package bll;

import be.Event;
import dal.EventDAO;


import dal.interfaces.IEventDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventManager {

    private IEventDAO eventDAO;

    public EventManager() {
        try {
            this.eventDAO = new EventDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> getAllEvents() throws Exception {
        return eventDAO.getAllEvents();
    }


    public int createEvent(Event event) throws Exception {
        return eventDAO.createEvent(event);
    }

    public void deleteEvent(int id) throws SQLException {
        eventDAO.deleteEvent(id);
    }


    public void cancelEvent(int eventID) throws SQLException {
        eventDAO.cancelEvent(eventID);
    }
}
