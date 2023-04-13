package gui.controller;

import be.Event;
import be.Ticket;
import gui.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageTicketsController implements Initializable {
    @FXML
    private Button btnAdd, btnSaveTickets;
    @FXML
    private ChoiceBox comboChooseEvent, comboTypeOfTicket;
    @FXML
    private TextField txtTitleOfEvent, txtDateOfEvent, txtTotalAmountOfTickets, txtTicketsSold, txtLocationOfEvent, txtAddExtras, txtNumberOfTickets, txtNewPriceOfTicket;
    @FXML
    private TableView tblviewTypesOfTickets;
    @FXML
    private TableColumn colType, colContains, colPrice, colAmount;
    private Model model;

    private static ObservableList<Event> allEvents;

    private static ObservableList<Event> coordinatorsEvents;

    private static ObservableList<Ticket> ticketsForSale;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coordinatorsEvents = FXCollections.observableArrayList();
        try {
            model = new Model();
            allEvents = model.getActiveEvents();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Something went horribly wrong:\n" + e, ButtonType.CANCEL);
            alert.showAndWait();
        }

        for (Event event : allEvents) {
            if (event.getEventCoordinator().equals("Def_EV_Cord")) { //TODO fix this so it is for each unique coordinator
                coordinatorsEvents.add(event);
            }
        }
        ArrayList<String> comboBoxEventTitles = new ArrayList<>();

        for (Event event : coordinatorsEvents) {
            comboBoxEventTitles.add(event.getEventTitle());
        }
        comboChooseEvent.getItems().addAll(comboBoxEventTitles);

        comboChooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> displayAllInfo(newValue));

    }

    private void displayAllInfo(Object value) {
        for (Event event : coordinatorsEvents) {
            if (value.equals(event.getEventTitle())) {
                txtTitleOfEvent.setText(event.getEventTitle());
                txtDateOfEvent.setText(event.getEventDate().toString());
                txtLocationOfEvent.setText(event.getEventLocation());
                txtTotalAmountOfTickets.setText(String.valueOf(event.getEventTicketAmount()));
                updateTableView(event);
            }
        }

    }

    private void updateTableView(Event event) {
        try {
            ticketsForSale = model.getTickets(event.getEventID());
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Could not get tickets from Database", ButtonType.OK);
            alert.showAndWait();
        }

        if (ticketsForSale.isEmpty() && Integer.parseInt(txtNumberOfTickets.getText()) > 0) {

        }

    }

    public void handleAdd(ActionEvent actionEvent) {
    }

    public void handleSaveTickets(ActionEvent actionEvent) {
    }
}
