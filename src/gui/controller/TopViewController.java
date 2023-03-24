package gui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TopViewController implements Initializable {

    @FXML
    private Label lblSignIn;
    private String url = "data/Images/10cketshort.png";

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnUpcomingEvents,btnAllEvents,btnCalendar,btnNewEvent,btnContact;
    private ControllerAssistant controllerAssistant;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setLogo();
        setStyles();
        signInLabelStyling();
        controllerAssistant = ControllerAssistant.getInstance();



    }

    private void signInLabelStyling() {
        DropShadow shadow = new DropShadow(0,4,4, Color.color(0,0,0,0.25));
        lblSignIn.setEffect(shadow);
        lblSignIn.getStyleClass().add("lblSignIn");
    }

    private void setStyles() {
        DropShadow shadow = new DropShadow(0,4,4, Color.color(0,0,0,0.25));
        lblSignIn.setEffect(shadow);
    }

    private void setLogo() {
        Image logo = new Image(url);
        imgLogo.setImage(logo);

    }

    public void handleNewEvent(ActionEvent actionEvent) {
        controllerAssistant.openNewWindow("CreateEventView.fxml");
    }

    public void handleUpcomingEvents(ActionEvent actionEvent) {
        controllerAssistant.openNewWindow("EventOverView.fxml");
    }

    public void handleSignIn(MouseEvent mouseEvent) {
        controllerAssistant.openNewWindow("LoginView.fxml");
    }

    public void handleCalendar(ActionEvent actionEvent) { controllerAssistant.openNewWindow("CalendarView.fxml"); }

    public void handleContact(ActionEvent actionEvent) { controllerAssistant.openNewWindow("ContactView.fxml"); }

    public void handleAllEvents(ActionEvent actionEvent) { controllerAssistant.openNewWindow("AllEventView.fxml"); }
}
