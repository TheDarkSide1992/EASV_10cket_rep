package dal.interfaces;

import be.Event;
import be.EventCoordinator;

import java.util.List;

public interface IEventCoordinator {
    List<EventCoordinator> getAllEventCoordinators();
    int createEvent(Event eventToCreate);
    boolean requestToDeleteEvent(Event eventToBeDeleted);
    void deleteEventCoordinator(int id);
}
