package gui.model;

import be.*;
import bll.EventManager;
import bll.TicketGenerator;
import bll.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    private ObservableList<Event> activeEvents;
    private ObservableList<Event> allEvents;
    private EventManager eventManager;
    private UserManager userManager;
    private TicketGenerator ticketGenerator;

    public Model() throws Exception {

        eventManager = new EventManager();
        userManager = new UserManager();
        ticketGenerator = new TicketGenerator();
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

    public void makeTicket(Event event, Ticket ticket) throws Exception{
        event = getActiveEvents().get(2);
        ticket = new Ticket();
        ticketGenerator.makeTicket(event, ticket);
    }
}

