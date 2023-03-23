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
import java.time.LocalDate;
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

        txtTitleOfEvent.textProperty().addListener(observable -> isEmpty());
        txtEventDescription.textProperty().addListener(observable -> isEmpty());
        txtEventStartTime.textProperty().addListener(observable -> isEmpty());
        txtEventDescription.textProperty().addListener(observable -> isEmpty());
        txtEventOwner.textProperty().addListener(observable -> isEmpty());
        txtEventCollaborator.textProperty().addListener(observable -> isEmpty());
    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("!!ERROR!!");
        alert.setHeaderText("Something went wrong, \n ERROR:      " + t.getMessage());
        alert.showAndWait();

        t.printStackTrace();
    }

    private void isEmpty(){
        if (txtTitleOfEvent.getText().isEmpty())  return;

        if (datePicker.getValue() == null) return;

        if (txtEventStartTime.getText() == null) return;

        if (txtEventDescription.getText() == null || txtEventDescription.getText().isEmpty()) return;

        btnSaveEvent.setDisable(false);
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

    private void displayAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("!!Invaid!!");
        alert.setHeaderText("Something went wrong, \n" + message);
        alert.showAndWait();
    }
    public void handleSaveEvent(ActionEvent event) {
        if (checkData()) saveEvent();
    }

    private boolean checkData(){
        if(txtTitleOfEvent.getText() == null || txtTitleOfEvent.getText().isEmpty()){
            displayAlert("Missing tittle");
            return false;
        } else if (datePicker.getValue() == null || datePicker.getValue().isBefore(LocalDate.now())){
            displayAlert("Date is either missing or not valid");
            return false;
        } else if (!txtEventStartTime.getText().contains(":") || txtEventStartTime.getText().contains(" ") || Time.valueOf(txtEventStartTime.getText()+ ":00") == null) {
            displayAlert("Start time is missing or not valid");
            return false;
        } else if (txtLocation.getText() == null || txtLocation.getText().isEmpty()) {
            displayAlert("Missing a Location");
            return false;
        } else if (txtEventDescription.getText() == null || txtEventDescription.getText().isEmpty()){
            displayAlert("Missing a Description");
            return false;
        }
        btnSaveEvent.setDisable(false);
        return true;

    }

    private void saveEvent(){
        Event event1 = new Event(
                txtTitleOfEvent.getText(),
                datePicker.getValue(),
                Time.valueOf(txtEventStartTime.getText()+ ":00"),
                txtLocation.getText(),
                txtEventDescription.getText(),
                cbIsActive.isSelected());
        if (imgEventImage != null){
            //TODO SAVE AN IMAGE ON EVENT
        }
        try {
            model.createEvent(event1);
        } catch (Exception e) {
            displayError(e);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Event Successfully created");
        alert.showAndWait();
    }

}
