package gui.model;

import be.Event;
import be.User;
import bll.EventManager;
import bll.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Model {
    private ObservableList<Event> activeEvents;
    EventManager eventManager;
    UserManager userManager;

    public Model() throws Exception {

        eventManager = new EventManager();
        userManager = new UserManager();
        activeEvents = FXCollections.observableArrayList();

    }

    public void getAllEvents() throws Exception {
        eventManager.getAllEvents();
    }

    public void createEvent(Event event) throws Exception {
        event.setEventID(eventManager.createEvent(event));
        activeEvents.add(event);
    }


    public ObservableList<Event> getActiveEvents() throws Exception {
        activeEvents.addAll(eventManager.getAllEvents());
        return activeEvents;
    }

    public void deleteEvent(int id) throws SQLException {
        eventManager.deleteEvent(id);

    }

    public void cancelEvent(int eventID) throws SQLException {
        eventManager.cancelEvent(eventID);
    }

    public User checkLogIn(String userName, String password) throws Exception{
        return userManager.getIfLongedInUSer(userName,password);
    }
}

