package dal;

import be.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class EventDAOTest {

    @Test
    List<Event> getAllEvents() {




    }

    @Test
    void createEvent(Event event) {
        //Arrange
        Time time = new Time(20, 0, 0);
        event = new Event("test", new LocalDate(20,10,10), time, "The Testment", "Test", true);
        

        //Act
        createEvent(event);


        //Assert
        Event expectedEvent = new Event("test", new LocalDate(20,10,10), time,"The testment", "Test", true);

    }

    @Test
    void deleteEvent() {
    }

    @Test
    void cancelEvent() {
    }
}