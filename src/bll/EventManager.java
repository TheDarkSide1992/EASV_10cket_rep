package bll;

import be.Event;
import dal.EventDAO;


import dal.interfaces.IEventDAO;

import java.io.IOException;
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
        eventDAO = new EventDAO();
        return eventDAO.getAllEvents();
    }


    public int createEvent(Event event) throws Exception {
        return eventDAO.createEvent(event);
    }


}
