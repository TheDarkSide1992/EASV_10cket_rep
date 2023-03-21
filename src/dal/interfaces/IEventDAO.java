package dal.interfaces;

import be.Event;

import java.util.List;

public interface IEventDAO {
    List<Event> getAllEvents() throws Exception;
    int createEvent (Event event) throws Exception;
    boolean deleteEvent(int id) throws Exception;
    boolean cancelEvent(int id) throws Exception;

}
