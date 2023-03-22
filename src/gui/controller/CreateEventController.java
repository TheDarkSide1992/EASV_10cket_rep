package gui.controller;

import be.Event;
import gui.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable {
    @FXML
    private TextField txtTitleOfEvent, txtLocation, txtEventOwner, txtEventCollaborator, txtEventStartTime;

    @FXML
    private TextArea txtEventDescription;

    @FXML
    private Button btnChooseImage, btnSaveEvent;

    @FXML
    private ImageView imgEventImage;

    @FXML
    private DatePicker datePicker;

    Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleSaveEvent(ActionEvent event) {
        Event event1 = new Event(
                        txtTitleOfEvent.getText(),
                        datePicker.getValue(),
                        Time.valueOf(txtEventStartTime.getText()),
                        txtLocation.getText(),
                        txtEventDescription.getText(),
                        true);
        try {
            model.createEvent(event1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
