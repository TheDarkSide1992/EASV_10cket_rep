package gui.controller;

import gui.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    public CheckBox cbIsActive;
    @FXML private BorderPane borderPane;

    private ControllerAssistant controllerAssistant;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerAssistant = ControllerAssistant.getInstance();
        controllerAssistant.setBorderPane(borderPane);
        try {
            //setCenter();
            //setTop();
            Model model = new Model();
            controllerAssistant.loadTop("TopViewAllUsers.fxml");
            controllerAssistant.loadCenter("EventOverView.fxml");
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    private void setTop() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/TopView.fxml"));
        Pane newScene = loader.load();

        borderPane.setTop(newScene);
    }

    private void setCenter() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/EventOverView.fxml"));
        Pane newScene = loader.load();

        borderPane.setCenter(newScene);
    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("!!ERROR!!");
        alert.setHeaderText("Something went wrong, \n ERROR:      " + t.getMessage());
        alert.showAndWait();
    }


}
