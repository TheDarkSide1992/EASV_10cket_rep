package dal.interfaces;

import be.Event;
import be.EventCoordinator;

import java.util.List;

public interface IEventCoordinator {
    List<EventCoordinator> getAllEventCoordinators() throws Exception;
    int createEvent(Event eventToCreate) throws Exception;
    boolean requestToDeleteEvent(Event eventToBeDeleted) throws Exception;
    void deleteEventCoordinator(int id)throws Exception ;
}
