package dal.interfaces;

import be.User;

import java.util.ArrayList;

public interface IGeneralUser {
    ArrayList<User> getAllUsers() throws Exception;
    int createUser(User user) throws Exception;
    void setPassword(User user, String password) throws Exception;
    User IsLogInLegit(String username, String password) throws Exception;

}
