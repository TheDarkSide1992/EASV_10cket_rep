package gui.controller;

import be.Event;
import be.Request;
import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BuyTicketViewController implements Initializable {

    @FXML
    private TableView tblViewTypeOfTickets;
    @FXML
    private TableColumn colTypeOfTicket, colPrice;
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
    private List<Ticket> ticketsForThisEvent;

    private static ObservableList<Ticket> ticketsForSale;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();
            ticketsForThisEvent = model.getTickets(event.getEventID());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not get tickets from database", ButtonType.CANCEL);
            alert.showAndWait();
        }
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
        numOfTickets.getSelectionModel().select(0);
        btnSendRequest.setDisable(true);
        txtCustomerName.textProperty().addListener(observable -> isEmpty());
        txtCustomerEmail.textProperty().addListener(observable -> isEmpty());
        txtCustomerTlf.textProperty().addListener(observable -> isEmpty());
        numOfTickets.selectionModelProperty().addListener(observable -> isEmpty());
        tblViewTypeOfTickets.selectionModelProperty().addListener(observable -> isEmpty());
        tblViewTypeOfTickets.setPlaceholder(new Label("No Tickets available for this event"));
        updateTableView();
    }

    public void handleSendRequest(ActionEvent actionEvent) {
        String customerName = txtCustomerName.getText();
        int tickets = Integer.parseInt((String) numOfTickets.getSelectionModel().getSelectedItem());
        String type = colTypeOfTicket.getCellObservableValue(tblViewTypeOfTickets.getSelectionModel().getSelectedItem()).getValue().toString();
        String customerPhone = txtCustomerTlf.getText();
        String customerEmail = txtCustomerEmail.getText();

        Request request = new Request(customerName, tickets, type, customerPhone, customerEmail, event.getEventID());

        try {
            model.sendRequest(request);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING, "Could not process your request", ButtonType.CANCEL);
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Tickets have been requested", ButtonType.OK);
        alert.showAndWait();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    public void setBuyTicketEvent(Event event) {
        this.event = event;
    }

    private void isEmpty() { //TODO need to fix this so it doesn't enable Send Request before all 4 criteria are filled in
        btnSendRequest.setDisable(true);
        if (txtCustomerName.getText().isEmpty()) return;
        if (txtCustomerEmail.getText().isEmpty()) return;
        if (txtCustomerTlf.getText().isEmpty()) return;
        if (tblViewTypeOfTickets.getSelectionModel().isEmpty() || tblViewTypeOfTickets.getSelectionModel().getSelectedItem() == null)
            return;
        btnSendRequest.setDisable(false);
    }

    private void updateTableView() {
        ticketsForSale = FXCollections.observableArrayList();
        if (ticketsForThisEvent.size() > 0) {
            Ticket first = null;
            int totalTickets = 0;
            for (int i = 0; i < ticketsForThisEvent.size() - 1; i = i + (first.getAmountOfTickets() + 1)) {
                first = ticketsForThisEvent.get(i);
                ticketsForSale.add(first);
                totalTickets = totalTickets + first.getAmountOfTickets();
            }
        }
        colTypeOfTicket.setCellValueFactory(new PropertyValueFactory<>("ticketContains"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

        tblViewTypeOfTickets.getColumns().addAll();
        tblViewTypeOfTickets.setItems(ticketsForSale);

    }
}

