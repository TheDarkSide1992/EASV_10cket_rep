package bll;

import be.Administrator;
import be.Event;
import dal.AdminDAO;
import dal.EventDAO;
import dal.interfaces.IAdministratorDAO;
import dal.interfaces.IEventDAO;

import java.io.IOException;
import java.util.List;

public class EventManager {
    private IEventDAO eventDAO;
    private IAdministratorDAO administratorDAO;
    public EventManager() {
        try {
            this.eventDAO = new EventDAO();
            this.administratorDAO = new AdminDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> getAllEvents() throws Exception {
        return eventDAO.getAllEvents();
    }

    public void createEvent(Event event) throws Exception {
        eventDAO.createEvent(event);
    }


}
