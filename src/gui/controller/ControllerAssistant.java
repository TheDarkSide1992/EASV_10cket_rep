package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ControllerAssistant {
    @FXML
    private static BorderPane borderPane;
    private static ControllerAssistant controllerAssistant;

    private ControllerAssistant() {
    }

    public static ControllerAssistant getInstance(){
        if (controllerAssistant == null) controllerAssistant = new ControllerAssistant();

        return controllerAssistant;
    }

    public void setBorderPane(BorderPane borderPane){
        this.borderPane = borderPane;
    }

    public void loadCenter(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/" + file));
        BorderPane newScene = loader.load();

        borderPane.setCenter(newScene);
    }

    public void loadTop(String file) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/" + file));
        BorderPane newScene = loader.load();

        borderPane.setTop(newScene);
    }

    public void loadBottom(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/" + file));
        BorderPane newScene = loader.load();

        borderPane.setBottom(newScene);
    }

    public void loadLeft(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/" + file));
        BorderPane newScene = loader.load();

        borderPane.setLeft(newScene);
    }

    public void loadRight(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/" + file));
        BorderPane newScene = loader.load();

        borderPane.setRight(newScene);
    }
}
