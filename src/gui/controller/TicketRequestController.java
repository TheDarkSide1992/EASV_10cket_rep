package gui.controller;

import be.Event;
import be.Request;
import be.Ticket;
import gui.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        colPaymentReceived.setCellValueFactory(new PropertyValueFactory<>("paymentReceived"));
        colSentToCustomer.setCellValueFactory(new PropertyValueFactory<>("ticketSentToCustomer"));


        tblViewTicketRequests.setItems(requestsForTblView);
    }

    public void handlePaymentReceived(ActionEvent actionEvent) {
        if (tblViewTicketRequests.getSelectionModel().getSelectedItem() != null) {
            Request selectedItem = (Request) tblViewTicketRequests.getSelectionModel().getSelectedItem();
            selectedItem.setPaymentReceived(true);
            try {
                model.paymentProcessed(selectedItem);
                requests = model.getRequests();
                requestsForTblView = FXCollections.observableArrayList();
                requestsForTblView.addAll(requests);
                tblViewTicketRequests.setItems(requestsForTblView);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Could not mark this/these ticket as paid", ButtonType.CANCEL);
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }

    public void handleSentToCustomer(ActionEvent actionEvent) {
        if (tblViewTicketRequests.getSelectionModel().getSelectedItem() != null) {
            Request selectedItem = (Request) tblViewTicketRequests.getSelectionModel().getSelectedItem();
            selectedItem.setTicketSentToCustomer(true);
            try {
                model.ticketSentToCustomer(selectedItem);
                requests = model.getRequests();
                requestsForTblView = FXCollections.observableArrayList();
                requestsForTblView.addAll(requests);
                tblViewTicketRequests.setItems(requestsForTblView);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Could not mark this/these ticket as sent", ButtonType.CANCEL);
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }

    public void handleGenerateTickets(ActionEvent actionEvent) {
        if (tblViewTicketRequests.getSelectionModel().getSelectedItem() != null) {
            Request request = (Request) tblViewTicketRequests.getSelectionModel().getSelectedItem();
            String eventTitle = request.getEventTitle();
            LocalDate date = request.getEventDate();
            Event event = new Event(eventTitle, date);
            String ticketType = request.getTypeOfTicket();
            int ticketID = request.getTicketID();
            int ticketPrice = request.getTicketPrice();
            Ticket ticket = new Ticket(ticketID, ticketType, ticketPrice);
            try {
                model.makeTicket(event, ticket);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Could not generate a ticket", ButtonType.CANCEL);
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
}


