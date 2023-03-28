package bll;

import be.User;
import dal.GeneralUserDAO;
import dal.interfaces.IGeneralUser;

public class UserManager {
    private IGeneralUser generalUser;

    public UserManager() throws Exception {
        generalUser = new GeneralUserDAO();
    }

    public User getIfLongedInUSer(String userName, String pasWord) throws Exception{
        return generalUser.IsLogInLegit(userName, pasWord);
    }
}
