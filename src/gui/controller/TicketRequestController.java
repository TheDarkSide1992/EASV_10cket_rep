package gui.controller;

import be.Request;
import gui.model.Model;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TicketRequestController implements Initializable {

    @FXML
    private TableView tblViewTicketRequests;
    @FXML
    private Button btnPaymentReceived, btnSentToCustomer, btnGenerateTickets;
    @FXML
    private TableColumn colRequestID, colEventName, colDate, colCustomerName, colCustomerEmail, colCustomerPhone, colRequestedTickets, colTypeOfTickets, colTicketsLeft, colPaymentReceived, colSentToCustomer, colTicketPrice, colTicketID;

    private Model model;

    private List<Request> requests;

    private ObservableList<Request> requestsForTblView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        requests = new ArrayList<>();
        try {
            model = new Model();
            requests = model.getRequests();
            updateTblView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTblView() {
        requestsForTblView = FXCollections.observableArrayList();
        requestsForTblView.addAll(requests);
        colRequestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        colRequestedTickets.setCellValueFactory(new PropertyValueFactory<>("tickets"));
        colTypeOfTickets.setCellValueFactory(new PropertyValueFactory<>("typeOfTicket"));
        colTicketPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        colTicketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));


        tblViewTicketRequests.setItems(requestsForTblView);
    }

    public void handlePaymentReceived(ActionEvent actionEvent) {
        Request selectedItem = (Request) tblViewTicketRequests.getSelectionModel().getSelectedItem();
        selectedItem.setPaymentReceived(true);
    }

    public void handleSentToCustomer(ActionEvent actionEvent) {
    }

    public void handleGenerateTickets(ActionEvent actionEvent) {
    }
}
