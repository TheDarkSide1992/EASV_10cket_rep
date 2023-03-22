package gui.model;

import be.Event;
import bll.EventManager;

public class Model {
    EventManager eventManager = new EventManager();

    public Model() {

    }

    public void createEvent(Event event) throws Exception {
        eventManager.createEvent(event);
    }
}
