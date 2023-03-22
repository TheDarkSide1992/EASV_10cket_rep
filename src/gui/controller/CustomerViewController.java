package gui.controller;

import gui.model.Model;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    Model model = new Model();

    public CustomerViewController() throws Exception {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model.getActiveEvents();
    }
}
