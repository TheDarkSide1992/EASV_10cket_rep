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
    public List<Administrator> getAllAdministrators() {
        try (Connection conn = dbConnector.getConnection()) {
            //Extract all administrators
            String sql = "SELECT * FROM Administrator;";
            Statement stmt = conn.createStatement();

            //Execute the update to the DB
            ResultSet rs = stmt.executeQuery(sql);

            int i = 1;
            while (rs.next()){
                String username = rs.getString("AdminUsername");
                String password = rs.getString("AdminPassword");
                System.out.println(i + "# Administrator: " + username + "\t password: " + password);
            }

        } catch (Exception e) {
        }

        return null;
    }

    @Override
    public int createAdministrator(EventCoordinator eventCoordinator) {
        return 0;
    }

    @Override
    public void deleteAdministrator(Event eventToDelete) {

    }

}
