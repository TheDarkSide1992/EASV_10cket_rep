package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerTopViewController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnUpcomingEvents,btnAllEvents,btnCalendar,btnNewEvent,btnContact;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //setLogo();

    }

    private void setLogo() {

    }

    public void handleNewEvent(ActionEvent actionEvent) {
        ControllerAssistant controllerAssistant = ControllerAssistant.getInstance();
        controllerAssistant.openNewWindow("CreateEventView.fxml");
    }

    public void handleUpcomingEvents(ActionEvent actionEvent) {
        ControllerAssistant controllerAssistant = ControllerAssistant.getInstance();
        controllerAssistant.openNewWindow("CustomerView.fxml");
    }
}
