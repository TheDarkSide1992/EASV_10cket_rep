package gui.model;

import be.Administrator;
import be.Event;
import be.EventCoordinator;
import be.User;
import bll.EventManager;
import bll.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Model {
    private ObservableList<Event> activeEvents;
    private ObservableList<Event> allEvents;
    EventManager eventManager;
    UserManager userManager;

    public Model() throws Exception {

        eventManager = new EventManager();
        userManager = new UserManager();
        activeEvents = FXCollections.observableArrayList();
        allEvents = FXCollections.observableArrayList();

    }
    public void sortByDate() {
        allEvents.sort((Comparator.comparing(Event::getEventDate)));
    }

    public ObservableList<Event> getAllEvents() throws Exception {
        sortByDate();
        allEvents.addAll(eventManager.getAllEvents());
        return allEvents;
    }

    public ObservableList<Event> getActiveEvents() throws Exception {
        activeEvents.addAll(eventManager.getActiveEvents());
        return activeEvents;
    }

    public void createEvent(Event event) throws Exception {
        event.setEventID(eventManager.createEvent(event));
        activeEvents.add(event);
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

    public ArrayList<Administrator> getAllAdmins() throws Exception {
        return userManager.getAllAdmins();
    }

    public ArrayList<EventCoordinator> getAllCoordinators() throws Exception {
        return userManager.getAllCoordinators();
    }
}

