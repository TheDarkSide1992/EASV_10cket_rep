package gui.controller;

import be.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ControllerAssistant {
    @FXML
    private static BorderPane borderPane;
    private static ControllerAssistant controllerAssistant;
    private static User setLoggedInUser;

    private ControllerAssistant() {

    }

    public static ControllerAssistant getInstance(){
        if (controllerAssistant == null) controllerAssistant = new ControllerAssistant();

        return controllerAssistant;
    }

    public void setBorderPane(BorderPane borderPane){
        this.borderPane = borderPane;
    }
    public void setLoggedInUser(User loggedInUser){this.setLoggedInUser = loggedInUser;}
    public User getLoggedInUser(){return this.setLoggedInUser;}

    public void loadCenter(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/" + file));
        ScrollPane newScene = loader.load();

        borderPane.setCenter(newScene);
    }

    public void loadTop(String file) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/" + file));
        Pane newScene = loader.load();

        borderPane.setTop(newScene);
    }

    public void openNewWindow(String s)  {
        try {
            loadCenter(s);
        } catch (IOException e) {
            e.printStackTrace();
            //displayError(e);
        }
    }

}
