package bll;

import be.Administrator;
import be.EventCoordinator;
import be.Request;
import be.User;
import bll.util.BCrypt;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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

    /**
     * gets the salt from db connected to given username and uses that to hash the given password, to compare them afterwards to see if the two hashes ius the same
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public User getIfLongedInUSer(String userName, String password) throws Exception{
        String salt = generalUser.getUserSalt(userName);

        String hashed = BCrypt.hashpw(password, salt );

        return generalUser.IsLogInLegit(userName, hashed);
    }

    public ArrayList<EventCoordinator> getAllCoordinators() throws Exception{
        return eventCoordinator.getAllEventCoordinators();
    }

    /**
     * Creates the user if the username is uniq
     * @param user
     * @return
     * @throws Exception
     */
    public int createUser(User user) throws Exception{

        if (generalUser.doesUserAlreadyExist(user.getUserName()) != 0) throw new Exception("the current UserName is taken");

        return generalUser.createUser(user);
    }

    /**
     * generates a random salt and hashes the password wth salt, and saves both hash and salt in the DB afterwords
     * @param user
     * @param password
     * @throws Exception
     */
    public void handlePassword(User user, String password) throws Exception {
        String salt = BCrypt.gensalt(16);

        String hashed = BCrypt.hashpw(password, salt );

        generalUser.setPassword(user, hashed, salt);


    }


}
