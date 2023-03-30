package dal;

import be.Administrator;
import be.EventCoordinator;
import be.User;
import dal.interfaces.IGeneralUser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GeneralUserDAO implements IGeneralUser {

    private DBConnector db;

    public GeneralUserDAO() throws IOException {
        db = new DBConnector();
    }

    @Override
    public ArrayList<User> getAllUsers()  throws Exception{
        return null;
    }

    @Override
    public int createUser() throws Exception{
        return 0;
    }

    @Override
    public User IsLogInLegit(String username, String password) throws Exception {
        User user = null;
        try (Connection conn = db.getConnection()) {
            //Extract all administrators

            //10Cket123456
            //Def_EV_Cord

            //THis didnt work dont know why
            /*String sql = "IF((SELECT User_User_ID FROM User_Passwords WHERE Users_Password = " +
                    "CAST(? AS VARBINARY(MAX))) = (SELECT User_ID FROM User_ Where [User_Name] = ?)) SELECT * FROM User_";*/

            String sql = "SELECT * FROM User_ WHERE User_ID = (SELECT User_User_ID FROM User_Passwords WHERE (User_User_Name = ?) AND (Users_Password = ?))";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2, password);

            stmt.executeQuery();

            //Execute the update to the DB
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){

                int id = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userFirstName = rs.getString("User_First_Name");
                int userType = rs.getInt("User_Type");
                String userEmil = rs.getString("User_Email");
                String tlfNumber = rs.getString("User_tlf");

                if (userType == 1)
                    user = new Administrator(id,userName,userFirstName,userEmil,tlfNumber,userType);

                if (userType == 2)
                    user = new EventCoordinator(id,userName,userFirstName,userEmil,tlfNumber,userType);
            }
        }

        return user;
    }
}
