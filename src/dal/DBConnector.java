package dal;

import be.Administrator;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

//Singleton Class
public class DBConnector {
    private static DBConnector dbConnector = null;

    private static final String PROP_FILE = "data/dbCredentials/config.properties";
    private SQLServerDataSource dataSource; //variable for creating a connection to the DB


    protected DBConnector() throws IOException {
        Properties databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream(PROP_FILE));

        String server = databaseProperties.getProperty("Server");
        String database = databaseProperties.getProperty("Database");
        String user = databaseProperties.getProperty("User");
        String password = databaseProperties.getProperty("Password");

        dataSource = new SQLServerDataSource();
        dataSource.setServerName(server);
        dataSource.setDatabaseName(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setTrustServerCertificate(true);
    }

    public static DBConnector getInstance() {
        try {
            if (dbConnector == null) {
                dbConnector = new DBConnector();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbConnector;
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws Exception {

        DBConnector databaseConnector = new DBConnector().getInstance();

        try (Connection connection = databaseConnector.getConnection()) {
            System.out.println("Is it open? " + !connection.isClosed());

            AdminDAO adminDAO = new AdminDAO();
            List<Administrator> admins = adminDAO.getAllAdministrators();


        } //Connection gets closed here

        //To set a test image on ALL users outcome the line below and run the test Image Method.
        //Recommend removal before release
        //testImageCode(databaseConnector);

    }

    private static void testImageCode(DBConnector db) throws Exception {
        byte[] data;

        Path cxlURL = Path.of("data/Images/Cancel.png");

        try {
            data = Files.readAllBytes(cxlURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = db.getConnection()) {
            String sql = "UPDATE User_ SET User_Img = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBytes(1, data);

            ps.execute();

        }

        System.out.println("uploaded image");
    }
}


