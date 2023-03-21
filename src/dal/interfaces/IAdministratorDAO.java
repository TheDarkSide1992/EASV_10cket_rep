package dal.interfaces;

import be.Administrator;
import be.Event;
import be.EventCoordinator;

import java.util.List;

public interface IAdministratorDAO {
    List<Administrator> getAllAdministrators() throws Exception;

    int createAdministrator(EventCoordinator eventCoordinator) throws Exception;

    void deleteAdministrator(Event eventToDelete) throws Exception;
}
