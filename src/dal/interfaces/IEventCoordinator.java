package dal.interfaces;

import be.Event;
import be.EventCoordinator;

import java.util.ArrayList;
import java.util.List;

public interface IEventCoordinator {
    ArrayList<EventCoordinator> getAllEventCoordinators() throws Exception;
    int createEventCoordinator(Event eventToCreate) throws Exception;
    void deleteEventCoordinator(int id)throws Exception ;
}
