package dal.interfaces;

import be.Administrator;
import be.Event;
import be.EventCoordinator;

import java.util.List;

public interface IAdministratorDAO {
    public List<Administrator> getAllAdministrators();

    public void createEventCoordinator(EventCoordinator eventCoordinator);

    public void deleteEvent(Event eventToDelete);
}
