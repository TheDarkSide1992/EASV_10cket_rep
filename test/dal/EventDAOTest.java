package dal;

import be.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Time;

class EventDAOTest {

    @Disabled
    @Test
    void getAllEvents() {




    }

    @Test
    void createEvent() {

        //Arrange
        Time time = new Time(20, 0, 0);
        java.sql.Date date = new java.sql.Date(2023,10,10);
        Event event = new Event("test", date.toLocalDate(), time, "The Testment", "Test", true);

        try {
            EventDAO ed = new EventDAO();


        //Act
        int id = ed.createEvent(event);


        //Assert
        Assertions.assertEquals(id, ed.getAllEvents().get(ed.getAllEvents().size()-1).getEventID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Disabled
    @Test
    void deleteEvent() {
    }
    @Disabled
    @Test
    void cancelEvent() {
    }
}