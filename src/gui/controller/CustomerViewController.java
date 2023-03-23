package gui.controller;

import gui.model.Model;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {
    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //model = new Model();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
