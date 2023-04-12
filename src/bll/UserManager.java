package bll;

import be.Administrator;
import be.EventCoordinator;
import be.User;
import dal.AdminDAO;
import dal.EventCoordinatorDAO;
import dal.GeneralUserDAO;
import dal.interfaces.IAdministratorDAO;
import dal.interfaces.IEventCoordinator;
import dal.interfaces.IGeneralUser;

import java.util.ArrayList;

public class UserManager {
    private IGeneralUser generalUser;
    private IEventCoordinator eventCoordinator;
    private IAdministratorDAO administrator;

    public UserManager() throws Exception {
        generalUser = new GeneralUserDAO();
        eventCoordinator = new EventCoordinatorDAO();
        administrator = new AdminDAO();
    }

    public ArrayList<Administrator> getAllAdmins() throws Exception{
        return administrator.getAllAdministrators();
    }

    public User getIfLongedInUSer(String userName, String pasWord) throws Exception{
        return generalUser.IsLogInLegit(userName, pasWord);
    }

    public ArrayList<EventCoordinator> getAllCoordinators() throws Exception{
        return eventCoordinator.getAllEventCoordinators();
    }

    public int createUser(User user) throws Exception{
        return generalUser.createUser(user);
    }
}
