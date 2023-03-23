package gui.controller;

import be.Event;
import gui.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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

    @FXML
    private CheckBox cbIsActive;

    Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();
        } catch (Exception e) {
            displayError(e);
        }

        btnSaveEvent.setDisable(true);
    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("!!ERROR!!");
        alert.setHeaderText("Something went wrong, \n ERROR:      " + t.getMessage());
        alert.showAndWait();

        t.printStackTrace();
    }

    public void handleChooseImage(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select song");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            Stage stage = (Stage) btnChooseImage.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null && selectedFile.getName().endsWith(".png") || selectedFile != null && selectedFile.getName().endsWith(".jpg")
                    || selectedFile != null && selectedFile.getName().endsWith(".gif")) {
                Image image = new Image(selectedFile.toURI().toString());
                imgEventImage.setImage(image);
            }
        } catch (Exception e) {
            displayError(e);
        }
    }

    public void handleSaveEvent(ActionEvent event) {


    }

    private void SaveEvent(){
        Event event1 = new Event(
                txtTitleOfEvent.getText(),
                datePicker.getValue(),
                Time.valueOf(txtEventStartTime.getText()+ ":00"),
                txtLocation.getText(),
                txtEventDescription.getText(),
                cbIsActive.isSelected());
        try {
            model.createEvent(event1);
        } catch (Exception e) {
            displayError(e);
        }
    }


}
