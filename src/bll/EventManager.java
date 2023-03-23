package bll;

import be.Event;
import dal.EventDAO;
import dal.interfaces.IEventDAO;

import java.util.List;

public class EventManager {

    IEventDAO eventDAO;
    public EventManager() {
    }

    public List<Event> getAllEvents() throws Exception {
        eventDAO = new EventDAO();
        return eventDAO.getAllEvents();
    }

    public int createEvent(Event event) throws Exception {
        eventDAO = new EventDAO();
        return eventDAO.createEvent(event);
    }


}
