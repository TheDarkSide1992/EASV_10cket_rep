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
            eventDAO = new EventDAO();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> getAllEvents() throws Exception {
        return EventDAO.getAllEvents();
    }

    public void createEvent(Event event) throws Exception {
        EventDAO.createEvent(event);
    }


}
