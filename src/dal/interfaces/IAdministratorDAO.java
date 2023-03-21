package dal.interfaces;

import be.Administrator;
import be.Event;
import be.EventCoordinator;

import java.util.List;

public interface IAdministratorDAO {
    List<Administrator> getAllAdministrators();

    int createAdministrator(EventCoordinator eventCoordinator);

    void deleteAdministrator(Event eventToDelete);
}
