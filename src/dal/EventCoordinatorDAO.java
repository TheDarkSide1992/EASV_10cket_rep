package dal;

import be.Administrator;
import be.Event;
import be.EventCoordinator;
import dal.DBConnector;
import dal.interfaces.IEventCoordinator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventCoordinatorDAO implements IEventCoordinator {
    private DBConnector dbConnector;

    public EventCoordinatorDAO() {
        dbConnector = DBConnector.getInstance();
    }

    @Override
    public ArrayList<EventCoordinator> getAllEventCoordinators() throws Exception {
        ArrayList<EventCoordinator> eventCordList = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {
            //Extract all administrators
            String sql = "SELECT * FROM User_ WHERE User_Type = " +
                    "(sELECT User_Type_ID FROM User_Type WHERE USER_TYPE_TYPE = 'Event Coordinator')";
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
                byte[] data = rs.getBytes("User_Img");

                EventCoordinator eventCoordinator = new EventCoordinator(id,userName,userFirstName,userEmil,tlfNumber,userType);

                if(data != null){
                    eventCoordinator.setImageBytes(data);
                    eventCoordinator.convertByteToImage();
                }

                eventCordList.add(eventCoordinator);
            }
        }

        return eventCordList;
    }

    @Override
    public int createEventCoordinator(Event eventToCreate) throws Exception {
        return 0;
    }



    @Override
    public void deleteEventCoordinator(int id) throws Exception {

    }
}
