package gui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class TopViewController implements Initializable {

    @FXML
    private Label lblSignIn;

    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnUpcomingEvents, btnAllEvents, btnCalendar, btnNewEvent, btnContact;
    private ControllerAssistant controllerAssistant;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLogo();
        setStyles();
        signInLabelStyling();
        controllerAssistant = ControllerAssistant.getInstance();
    }

    /**
     * Styling of labels with shadows
     * Gets style class from css file
     */
    private void signInLabelStyling() {
        DropShadow shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));
        lblSignIn.setEffect(shadow);
        lblSignIn.getStyleClass().add("lblSignIn");
    }
    /**
     * Styling of labels with shadows
     */
    private void setStyles() {
        DropShadow shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));
        lblSignIn.setEffect(shadow);
    }

    /**
     * Inserts the logo in the imgLogo Imageview
     */
    private void setLogo() {

        try {
            InputStream img = new FileInputStream("data/Images/10cketshort.png");
            Image image = new Image(img);
            imgLogo.setImage(image);
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Could not load logo, following error occurred:\n"+ e, ButtonType.CANCEL);
            alert.showAndWait();
        }

    }

    /**
     * Opens the CreateEventView when button is clicked
     * @param actionEvent
     */
    public void handleNewEvent(ActionEvent actionEvent) {
        controllerAssistant.openNewWindow("CreateEventView.fxml");
    }

    /**
     * Opens the UpcomingEventView when button is clicked
     * @param actionEvent
     */
    public void handleUpcomingEvents(ActionEvent actionEvent) {
        controllerAssistant.openNewWindow("UpcomingEventsView.fxml");
    }

    /**
     * Opens the LoginView when button is clicked
     * @param mouseEvent
     */
    public void handleSignIn(MouseEvent mouseEvent) {
        controllerAssistant.openNewWindow("LoginView.fxml");
    }

    /**
     * Opens the ContactView when button is clicked
     * @param actionEvent
     */
    public void handleContact(ActionEvent actionEvent) {
        controllerAssistant.openNewWindow("ContactView.fxml");
    }

    /**
     * Opens the AllEventView when button is clicked
     * @param actionEvent
     */
    public void handleAllEvents(ActionEvent actionEvent) {
        controllerAssistant.openNewWindow("AllEventView.fxml");
    }
}


