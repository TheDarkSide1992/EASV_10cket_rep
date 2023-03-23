package gui.model;

import be.Event;
import bll.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    private ObservableList<Event> activeEvents;
    EventManager eventManager = new EventManager();

    public Model() throws Exception {
        activeEvents = FXCollections.observableArrayList();
        activeEvents.addAll(eventManager.getAllEvents());
    }


    public void createEvent(Event event) throws Exception {
        eventManager.createEvent(event);
    }

    public void deleteEvent(Event event) throws Exception {
        eventManager.deleteEvent(event);
    }

    public ObservableList<Event> getActiveEvents() { return activeEvents;}
}
