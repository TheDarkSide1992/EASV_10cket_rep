package gui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerTopViewController implements Initializable {

    public Label lblSignIn;
    private String url = "/data/Images/10cketshort";

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnUpcomingEvents,btnAllEvents,btnCalendar,btnNewEvent,btnContact;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //setLogo();
        signInLabelStyling();


    }

    private void signInLabelStyling() {
        DropShadow shadow = new DropShadow(0,4,4, Color.color(0,0,0,0.25));
        lblSignIn.setEffect(shadow);
        lblSignIn.getStyleClass().add("lblSignIn");
    }

    private void setLogo() {
        Image logo = new Image(url);
        imgLogo.setImage(logo);

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
