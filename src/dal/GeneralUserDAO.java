package dal;

import be.Administrator;
import be.EventCoordinator;
import be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.interfaces.IGeneralUser;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class GeneralUserDAO implements IGeneralUser {

    private DBConnector db;

    public GeneralUserDAO() throws IOException {
        db = new DBConnector();
    }

    @Override
    public ArrayList<User> getAllUsers() throws Exception {
        return null;
    }

    @Override
    public int doesUserAlreadyExist(String userName) throws Exception {
        int id = 0;
        try (Connection conn = db.getConnection()) {

            String sql = "SELECT User_User_ID FROM User_Passwords WHERE (User_User_Name = ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);


            stmt.executeQuery();

            //Execute the update to the DB
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                id = rs.getInt("User_User_ID");
            }

        }

        return id;
    }


    @Override
    public int createUser(User user) throws Exception {
        int id = 0;
        try (Connection conn = db.getConnection()) {
            //String sql = "INSERT INTO Event_ (Event_Title, Event_Location, Event_Event_Coordinator_ID, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Ticket_Sold, Event_Is_Active) Values(?,?,?,?,?,?,?,?,?);";
            String sql = "INSERT INTO User_ VALUES(?, ?, (SELECT DISTINCT User_Type_ID FROM User_Type WHERE USER_TYPE_TYPE = ?), ?,?, ?)";


            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserFirstName());
            stmt.setString(3, user.getUserStringType());
            stmt.setString(4, user.getUserEmail());
            stmt.setString(5, user.getUserTLF());
            stmt.setBytes(6, user.getImageBytes());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create User\n" + ex);
        }
        return id;
    }

    @Override
    public void setPassword(User user, String password, String salt) throws Exception {
        try (Connection conn = db.getConnection()) {
            //String sql = "INSERT INTO Event_ (Event_Title, Event_Location, Event_Event_Coordinator_ID, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Ticket_Sold, Event_Is_Active) Values(?,?,?,?,?,?,?,?,?);";
            String sql = "INSERT INTO User_Passwords VALUES((SELECT DISTINCT User_ID FROM User_  WHERE User_Name = ?),?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, password);
            stmt.setString(4, salt);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not set password Event\n" + ex);
        }
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
            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.executeQuery();

            //Execute the update to the DB
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                int id = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userFirstName = rs.getString("User_First_Name");
                int userType = rs.getInt("User_Type");
                String userEmil = rs.getString("User_Email");
                String tlfNumber = rs.getString("User_tlf");

                if (userType == 1)
                    user = new Administrator(id, userName, userFirstName, userEmil, tlfNumber, userType);

                if (userType == 2)
                    user = new EventCoordinator(id, userName, userFirstName, userEmil, tlfNumber, userType);
            }
        }

        return user;
    }

    @Override
    public String getUserSalt(String userName) throws Exception {
        String salt = "";
        try (Connection conn = db.getConnection()) {

            String sql = "SELECT Users_Salt FROM User_Passwords WHERE (User_User_Name = ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);


            stmt.executeQuery();

            //Execute the update to the DB
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                salt = rs.getString("Users_Salt");
            }

        }

        return salt;
    }

    @Override
    public void sendRequest(String request, int eventID) throws SQLServerException {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO Ticket_Request(Event_ID, Request) VALUES(?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,eventID);
            ps.setString(2,request);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

