package bll;

import be.Event;
import dal.interfaces.IEventDAO;

import java.util.List;

public class EventManager {

    IEventDAO EventDAO;
    public EventManager() {
    }

    public List<Event> getAllEvents() throws Exception {
        return EventDAO.getAllEvents();
    }

    public void createEvent(Event event) throws Exception {
        EventDAO.createEvent(event);
    }


}
