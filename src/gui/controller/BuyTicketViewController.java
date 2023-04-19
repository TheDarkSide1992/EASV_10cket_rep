package gui.controller;

import be.Event;
import gui.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BuyTicketViewController implements Initializable {

    @FXML
    private ComboBox numOfTickets;
    @FXML
    private TextField txtCustomerName, txtCustomerEmail, txtCustomerTlf;
    @FXML
    private Label lblEventTitle, lblDateAndLocation;
    @FXML
    private Button btnSendRequest, btnCancel;
    @FXML
    private ImageView imageEvent;
    private Event event;
    private Model model;
    private ObservableList<String> numTickets;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            lblEventTitle.setText(event.getEventTitle());
            lblDateAndLocation.setText(event.getEventDate().toString() + " - " + event.getEventLocation());
            imageEvent.setImage(event.getEventImage());
            ArrayList<String> nums = new ArrayList<>();
            nums.add("1");
            nums.add("2");
            nums.add("3");
            nums.add("4");
            nums.add("5");
            nums.add("6");
            nums.add("7");
            nums.add("8");
            numTickets = FXCollections.observableArrayList();
            numTickets.addAll(nums);
            numOfTickets.setItems(numTickets);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void handleSendRequest(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Tickets have been requested", ButtonType.OK);
        alert.showAndWait();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    public void setBuyTicketEvent(Event event) {
        this.event = event;
    }
}
