package dal;

import be.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDate;

class EventDAOTest {

    @Disabled
    @Test
    void getAllEvents() {

    }

    /**
     * This is not the best method to test but its the best method to test.
     * but do to most classes never having any getters this is the best we could come up with
     */
    @Test
    void createEvent() {

        //Arrange
        Event event = new Event(0,"test", LocalDate.of(2023, 10,10), Time.valueOf("20:00:00"), "The testment","The Testment", "Test", true, "Colab", "tester");

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