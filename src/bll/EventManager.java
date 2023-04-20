package bll;

import be.Event;
import dal.EventDAO;


import dal.interfaces.IEventDAO;
import gui.util.DateComparator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventManager {

    private IEventDAO eventDAO;
    private List<Event> events = new ArrayList<>();
    private List<Event> submittedForDeletion = new ArrayList<>();
    public EventManager() {
        try {
            this.eventDAO = new EventDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> getAllEvents() throws Exception {
        events.addAll(eventDAO.getAllEvents());
        Collections.sort(events,new DateComparator());
        return events;
    }
    public List<Event> getSubmittedForDeletion() throws Exception {
        submittedForDeletion.addAll(eventDAO.getSubmittedForDeletion());
        Collections.sort(submittedForDeletion, new DateComparator());
        return submittedForDeletion;
    }


    public int createEvent(Event event) throws Exception {
        return eventDAO.createEvent(event);
    }
    public int submitForDeletion(Event eventToBeDeleted) throws Exception {
        return eventDAO.requestToDeleteEventCoordinator(eventToBeDeleted);
    }

    public void deleteEvent(int id) throws SQLException {
        eventDAO.deleteEvent(id);
    }


    public void cancelEvent(int eventID) throws SQLException {
        eventDAO.cancelEvent(eventID);
    }

    public List<Event> getActiveEvents() throws SQLException {
        List<Event> allEvents = eventDAO.getAllEvents();
        List<Event> activeEvents = new ArrayList<>();
        for (Event e: allEvents) {
            if(e.isEventIsActive() && e.getEventDate().isAfter(LocalDate.now())) {
                activeEvents.add(e);
            }
        }
        Collections.sort(activeEvents,new DateComparator());
        return activeEvents;
    }

    public Event getEvent(String eventName, LocalDate eventDate) throws SQLException {
        return eventDAO.getEvent(eventName, eventDate);
    }
}
