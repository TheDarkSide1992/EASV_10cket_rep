package gui.model;

import be.*;
import bll.EventManager;
import bll.TicketManager;
import bll.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private ObservableList<Event> activeEvents;
    private ObservableList<Event> allEvents;

    private ObservableList ticketObservableList;
    EventManager eventManager;
    UserManager userManager;
    TicketManager ticketManager;

    public Model() throws Exception {
        ticketManager = new TicketManager();
        eventManager = new EventManager();
        userManager = new UserManager();
        activeEvents = FXCollections.observableArrayList();
        allEvents = FXCollections.observableArrayList();

    }
    public ObservableList<Event> getAllEvents() throws Exception {
        allEvents = FXCollections.observableArrayList();
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

    public void createUser(User user) throws Exception{
        user.setUserID(userManager.createUser(user));

    }

    public void setUserPassword(User user, String password) throws Exception{
        userManager.handlePassword(user, password);
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

    public ObservableList getTickets(int eventID) throws SQLException {
        ticketObservableList = FXCollections.observableArrayList();
        List<Ticket> allTickets = ticketManager.getTickets(eventID);
        int amountOfTickets = 0;
        //TODO continue from here
        return ticketObservableList;
    }
}

