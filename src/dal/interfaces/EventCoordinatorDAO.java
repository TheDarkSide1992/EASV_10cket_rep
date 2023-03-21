package dal.interfaces;

import be.Administrator;
import be.Event;
import be.EventCoordinator;
import dal.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventCoordinatorDAO implements IEventCoordinator {
    private DBConnector dbConnector;

    public EventCoordinatorDAO() {
        dbConnector = DBConnector.getInstance();
    }

    @Override
    public List<EventCoordinator> getAllEventCoordinators() throws Exception {
        ArrayList<EventCoordinator> adminList = new ArrayList<>();

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

                adminList.add(new EventCoordinator(id,userName,userFirstName,userType,userEmil,tlfNumber));

            }
        }

        return adminList;
    }

    @Override
    public int createEvent(Event eventToCreate) throws Exception {
        return 0;
    }

    @Override
    public boolean requestToDeleteEvent(Event eventToBeDeleted) throws Exception {
        return false;
    }

    @Override
    public void deleteEventCoordinator(int id) throws Exception {

    }
}
