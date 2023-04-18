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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageTicketsController implements Initializable {
    @FXML
    private Button btnAdd, btnSaveTickets, btnRemoveTickets;
    @FXML
    private ChoiceBox comboChooseEvent, comboTypeOfTicket;
    @FXML
    private TextField txtTitleOfEvent;
    @FXML
    private TextField txtDateOfEvent;


    @FXML
    private TextField txtTotalAmountOfTickets;
    @FXML
    private TextField txtTicketsSold;
    @FXML
    private TextField txtLocationOfEvent;
    @FXML
    private TextField txtAddExtras;
    @FXML
    private TextField txtNumberOfTickets;
    @FXML
    private TextField txtNewPriceOfTicket;
    @FXML
    private TextField txtPriceOfTickets;
    @FXML
    private TableView tblviewTypesOfTickets;
    @FXML
    private TableColumn colContains, colPrice, colAmount;
    private Model model;

    private static ObservableList<Event> allEvents;

    private static ObservableList<Event> coordinatorsEvents;

    private static ObservableList<Ticket> ticketsForSale;

    private static Ticket defaultEntryTickets;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        defaultEntryTickets = new Ticket("Entry", 50, 150);
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
        comboTypeOfTicket.valueProperty().addListener((observable, oldValue, newValue) -> displayInfoOfTicket(newValue));

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
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not get tickets from Database", ButtonType.OK);
            alert.showAndWait();
        }
        if (ticketsForSale.size() == 0 || txtNumberOfTickets.getText().isEmpty()) {
            comboTypeOfTicket.getItems().clear();
            tblviewTypesOfTickets.getItems().clear();
            txtPriceOfTickets.setText("50");
            txtTotalAmountOfTickets.setText("150");


            colContains.setCellValueFactory(new PropertyValueFactory<>("ticketContains"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amountOfTickets"));

            tblviewTypesOfTickets.getColumns().addAll();
            ticketsForSale.add(defaultEntryTickets);
            tblviewTypesOfTickets.setItems(ticketsForSale);
            comboTypeOfTicket.getItems().add(defaultEntryTickets.getTicketContains());
            comboTypeOfTicket.getItems().add("New Ticket");
            comboTypeOfTicket.getSelectionModel().select(0);
            txtNumberOfTickets.setText("150");
            txtNewPriceOfTicket.setText("50");

        } else {
            colContains.setCellValueFactory(new PropertyValueFactory<>("ticketContains"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amountOfTickets"));

            tblviewTypesOfTickets.getColumns().addAll();
            tblviewTypesOfTickets.setItems(ticketsForSale);
            updateComboTicketTypes();
        }

    }

    private void updateComboTicketTypes() {
        ArrayList<String> comboBoxTicketTypes = new ArrayList<>();
        for (int i = 1; i < ticketsForSale.size() - 1; i++) {
            Ticket ticket1 = ticketsForSale.get(i);
            comboBoxTicketTypes.add(ticket1.getTicketContains());
            if (ticketsForSale.lastIndexOf(ticket1) < ticketsForSale.size()) {
                i = ticketsForSale.lastIndexOf(ticket1) + 1;
            } else break;
        }
        comboTypeOfTicket.getItems().clear();
        comboTypeOfTicket.getItems().addAll(comboBoxTicketTypes);
    }

    private void displayInfoOfTicket(Object newValue) {
        if (newValue == "New Ticket") {
            clearTicketInfo();
        }
        for (int i = 0; i < ticketsForSale.size(); i++) {
            if (ticketsForSale.get(i).getTicketContains().equals(newValue)) {
                txtNumberOfTickets.setText(String.valueOf(ticketsForSale.get(i).getAmountOfTickets()));
                txtNewPriceOfTicket.setText(String.valueOf(ticketsForSale.get(i).getTicketPrice()));
                txtAddExtras.setText(ticketsForSale.get(i).getTicketContains());
            }
            break;
        }

    }

    private void clearTicketInfo() {
        txtAddExtras.clear();
        txtNewPriceOfTicket.clear();
        txtNumberOfTickets.clear();
    }

    public void handleAdd(ActionEvent actionEvent) {
        boolean b = false;
        int k = 0;
        for (int i = 0; i < ticketsForSale.size(); i++) {
            k = i;
            b = txtAddExtras.getText().equals(ticketsForSale.get(i).getTicketContains());
            if (b) break;
        }
        if (comboTypeOfTicket.getSelectionModel().getSelectedItem().equals("New Ticket")) {
            if (b) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ticket type: \"" + txtAddExtras.getText() + "\" already exists", ButtonType.CANCEL);
                alert.showAndWait();
            } else {
                Ticket t1 = new Ticket(txtAddExtras.getText(), Integer.parseInt(txtNewPriceOfTicket.getText()), Integer.parseInt(txtNumberOfTickets.getText()));
                ticketsForSale.add(t1);
                tblviewTypesOfTickets.setItems(ticketsForSale);
                comboTypeOfTicket.getItems().add(t1.getTicketContains());

            }
        } else {
            Ticket t = new Ticket(txtAddExtras.getText(), Integer.parseInt(txtNewPriceOfTicket.getText()), Integer.parseInt(txtNumberOfTickets.getText()));
            ticketsForSale.remove(k);
            ticketsForSale.add(t);
            tblviewTypesOfTickets.setItems(ticketsForSale);
            comboTypeOfTicket.getItems().add(t.getTicketContains());

        }


    }

    public void handleSaveTickets(ActionEvent actionEvent) {
        List<Event> events = new ArrayList<>();
        events.addAll(coordinatorsEvents);
        Event event = null;
        for (int i = 0; i < events.size()-1; i++) {
            if(events.get(i).getEventTitle().equals(comboChooseEvent.getSelectionModel().getSelectedItem()))
            event = events.get(i);
        }
        try {
            model.saveTickets(ticketsForSale, event.getEventID());
        } catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not save tickets to Database", ButtonType.CANCEL);
            alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "All tickets saved");
        alert.show();
    }

    public void handleRemoveTickets(ActionEvent event) {
        Ticket ticket = (Ticket) tblviewTypesOfTickets.getFocusModel().getFocusedItem();
        tblviewTypesOfTickets.getItems().remove(ticket);
        comboTypeOfTicket.getItems().remove(ticket.getTicketContains());
    }
}