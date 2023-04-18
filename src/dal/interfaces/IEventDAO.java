package dal.interfaces;

import be.Event;

import java.sql.SQLException;
import java.util.List;

public interface IEventDAO {
    List<Event> getAllEvents() throws SQLException;
    List<Event> getSubmittedForDeletion() throws SQLException;

    int createEvent (Event event) throws Exception;

    void deleteEvent(int id) throws SQLException;

    void cancelEvent(int id) throws SQLException;
    int requestToDeleteEventCoordinator(Event eventToBeDeleted) throws Exception;

}
