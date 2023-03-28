package dal.interfaces;

import be.User;

import java.util.ArrayList;

public interface IGeneralUser {
    ArrayList<User> getAllUsers() throws Exception;
    int createUser() throws Exception;
    User IsLogInLegit(String username, String password) throws Exception;

}
