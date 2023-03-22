package bll;

import be.Event;
import dal.interfaces.IEventDAO;

public class EventManager {

    IEventDAO EventDAO;
    public EventManager() {
    }

    public void createEvent(Event event) throws Exception {
        EventDAO.createEvent(event);
    }
}
