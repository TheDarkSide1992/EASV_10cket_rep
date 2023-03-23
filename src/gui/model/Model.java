package gui.model;

import be.Event;
import bll.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    private ObservableList<Event> activeEvents;
    EventManager eventManager;

    public Model() throws Exception {

        eventManager = new EventManager();
        activeEvents = FXCollections.observableArrayList();

    }

    public void getAllEvens() throws Exception {
        eventManager.getAllEvents();
    }

    public void createEvent(Event event) throws Exception {
        event.setEventID(eventManager.createEvent(event));
        //activeEvents.add(event);
    }

    public void deleteEvent(Event event) throws Exception {
        eventManager.deleteEvent(event);
    }

    public ObservableList<Event> getActiveEvents() { return activeEvents;}
}
