package dal.interfaces;

import be.User;

import java.util.ArrayList;

public interface IGeneralUser {
    ArrayList<User> getAllUsers() throws Exception;
    int doesUserAlreadyExist(String userName) throws Exception;
    int createUser(User user) throws Exception;
    void setPassword(User user, String password, String salt) throws Exception;
    User IsLogInLegit(String username, String password) throws Exception;
    String getUserSalt(String userName) throws Exception;
}
