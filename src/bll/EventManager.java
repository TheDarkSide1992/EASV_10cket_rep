package bll;

import be.Event;
import dal.EventDAO;


import dal.interfaces.IEventDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<Event> getActiveEvents() throws SQLException {
        List<Event> allEvents = eventDAO.getAllEvents();
        List<Event> activeEvents = new ArrayList<>();
        for (Event e: allEvents) {
            if(e.isEventIsActive() && e.getEventDate().isAfter(LocalDate.now())) {
                activeEvents.add(e);
            }
        }
        return activeEvents;
    }
}
