package dal.interfaces;

import be.Event;
import be.EventCoordinator;

import java.util.List;

public interface IEventCoordinatorDAO{
    public List<EventCoordinator> getAllEventCoordinators();
    public void createEvent(Event eventToCreate);
    public void requestToDeleteEvent(Event eventToBeDeleted);
    public void ticketSell();
}
