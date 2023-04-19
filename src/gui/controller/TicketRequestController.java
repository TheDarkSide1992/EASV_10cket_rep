package gui.controller;

import gui.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketRequestController implements Initializable {

    @FXML
    private TableView tblViewTicketRequests;
    @FXML
    private Button btnPaymentReceived, btnSentToCustomer, btnGenerateTickets;
    @FXML
    private TableColumn colEventName, colDate, colCustomerName, colCustomerEmail, colCustomerPhone, colRequestedTickets, colTypeOfTickets, colTicketsLeft, colTicketPrice, colPaymentReceived, colSentToCustomer, colTicketID;

    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model(); //TODO continue from here
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void handlePaymentReceived(ActionEvent actionEvent) {
    }

    public void handleSentToCustomer(ActionEvent actionEvent) {
    }

    public void handleGenerateTickets(ActionEvent actionEvent) {
    }
}
