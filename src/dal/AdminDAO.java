package dal;

import be.Administrator;
import be.Event;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.interfaces.IAdministratorDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AdminDAO implements IAdministratorDAO {

    private static final String PROP_FILE = "data/dbCredentials/tableNames.settings.examlpe";

    private DBConnector dbConnector;
    private String adminTable;

    public AdminDAO() {
        try {
            dbConnector = DBConnector.getInstance();

            Properties databaseProperties = new Properties();
            databaseProperties.load(new FileInputStream(PROP_FILE));
            adminTable = databaseProperties.getProperty("AdminTable");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Administrator> getAllAdministrators() throws Exception{
        ArrayList<Administrator> adminList = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {
            //Extract all administrators
            String sql = "SELECT * FROM User_ WHERE User_Type = " +
                    "(sELECT User_Type_ID FROM User_Type WHERE USER_TYPE_TYPE = 'Administrator')";
            Statement stmt = conn.createStatement();

            //Execute the update to the DB
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userFirstName = rs.getString("User_First_Name");
                int userType = rs.getInt("User_Type");
                String userEmil = rs.getString("User_Email");
                String tlfNumber = rs.getString("User_tlf");

                adminList.add(new Administrator(id,userName,userFirstName,userEmil,tlfNumber,userType));
            }
        }

        return adminList;
    }

    @Override
    public int createAdministrator(EventCoordinator eventCoordinator) {
        return 0;
    }

    @Override
    public void deleteAdministrator(Event eventToDelete) {

    }

}
