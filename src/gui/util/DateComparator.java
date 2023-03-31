package gui.util;

import be.Event;

import java.util.Comparator;

public class DateComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        return o1.getEventDate().compareTo(o2.getEventDate());
    }
}
