package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageTicketsController implements Initializable {
    @FXML
    private Button btnAdd, btnSaveTickets;
    @FXML
    private ChoiceBox comboChooseEvent, comboTypeOfTicket;
    @FXML private TextField txtTitleOfEvent, txtDateOfEvent, txtTotalAmountOfTickets, txtTicketsSold, txtLocationOfEvent, txtAddExtras, txtNumberOfTickets ,txtNewPriceOfTicket;
    @FXML private TableView tblviewTypesOfTickets;
    @FXML private TableColumn colType,colContains, colPrice, colAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleAdd(ActionEvent actionEvent) {
    }

    public void handleSaveTickets(ActionEvent actionEvent) {
    }
}
