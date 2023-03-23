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
}
