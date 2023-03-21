package dal.interfaces;

import be.Event;

import java.util.List;

public interface IEventDAO {
    List<Event> getAllEvents();
    int createEvent (Event event);
    boolean deleteEvent(int id);
    boolean cancelEvent(int id);

}
