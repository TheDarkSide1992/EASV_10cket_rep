package gui.model;

import be.*;
import bll.EventManager;
import bll.TicketManager;
import bll.TicketGenerator;
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
    private TicketManager ticketManager;
    private ObservableList<Event> submittedForDeletion;
    private EventManager eventManager;
    private UserManager userManager;
    private TicketGenerator ticketGenerator;

    public Model() throws Exception {
        ticketManager = new TicketManager();
        eventManager = new EventManager();
        userManager = new UserManager();
        ticketGenerator = new TicketGenerator();
        activeEvents = FXCollections.observableArrayList();
        allEvents = FXCollections.observableArrayList();
        submittedForDeletion = FXCollections.observableArrayList();

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
    public ObservableList<Event> getSubmittedForDeletion() throws Exception {
        submittedForDeletion = FXCollections.observableArrayList();
        submittedForDeletion.addAll(eventManager.getSubmittedForDeletion());
        return submittedForDeletion;
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
    public void submitForDeletion(Event eventToBeDeleted) throws Exception {
        eventToBeDeleted.setEventID(eventManager.submitForDeletion(eventToBeDeleted));
        submittedForDeletion.add(eventToBeDeleted);
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

    public List<Ticket> getTickets(int eventID) throws SQLException {
        ticketObservableList = FXCollections.observableArrayList();
        List<Ticket> allTickets = ticketManager.getTickets(eventID);
        ticketObservableList.addAll(allTickets);
        return ticketObservableList;
    }


    public void makeTicket(Event event, Ticket ticket) throws Exception{
        //TODO USED FOR TESTING
        //event = getActiveEvents().get(2);
        //ticket = new Ticket(0, "string", 1);
        ticketGenerator.makeTicket(event, ticket);
    }

    public void saveTickets(List<Ticket> ticketsForSale, int eventID) throws SQLException {
        ticketManager.saveTickets(ticketsForSale, eventID);
    }

    public void sendRequest(String request, int eventID) {
        userManager.sendRequest(request, eventID);
    }
}
