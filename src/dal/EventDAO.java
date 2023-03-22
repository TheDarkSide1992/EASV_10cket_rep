package dal;

import be.Event;
import dal.interfaces.IEventDAO;

import java.util.List;

public class EventDAO implements IEventDAO {
    @Override
    public List<Event> getAllEvents() throws Exception {
        return null;
    }

    @Override
    public int createEvent(Event event) throws Exception {
        return 0;
    }

    @Override
    public boolean deleteEvent(int id) throws Exception {
        return false;
    }

    @Override
    public boolean cancelEvent(int id) throws Exception {
        return false;
    }
}
