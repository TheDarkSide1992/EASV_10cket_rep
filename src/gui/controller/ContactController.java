package gui.controller;

import be.Administrator;
import be.EventCoordinator;
import gui.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContactController implements Initializable {

    private Model model;

    private ArrayList<Administrator> allAdmins;
    private ArrayList<EventCoordinator> allCoordinators;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allAdmins = new ArrayList<>();
        allCoordinators = new ArrayList<>();
        try {
            model = new Model();

            allAdmins.addAll(model.getAllAdmins());
            allCoordinators.addAll(model.getAllCoordinators());

            System.out.println("Admin " + allAdmins);
            System.out.println("Cord " +allCoordinators);

        } catch (Exception e) {
            e.printStackTrace();
            displayError(e);
        }
    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("!!ERROR!!");
        alert.setHeaderText("Something went wrong, \n ERROR:      " + t.getMessage());
        alert.showAndWait();

        t.printStackTrace();
    }
}
