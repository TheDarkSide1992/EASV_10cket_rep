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

    /**
     * Controller constructor, only used once in get instance
     */
    private ControllerAssistant() {

    }

    /**
     * Singleton, returns the instance of the Assistant
     * @return
     */
    public static ControllerAssistant getInstance(){
        if (controllerAssistant == null) controllerAssistant = new ControllerAssistant();

        return controllerAssistant;
    }

    /**
     * Takes a borderpane for and saves for general use.
     * should be implemented the first time the class is called.
     * @param borderPane
     */
    public void setBorderPane(BorderPane borderPane){
        this.borderPane = borderPane;
    }

    /**
     * Saves a User that can be accessed in all other classes for static use.
     * should be set to null after being logged out
     * @param loggedInUser
     */
    public void setLoggedInUser(User loggedInUser){this.setLoggedInUser = loggedInUser;}

    /**
     * Returns logged in user
     * @return
     */
    public User getLoggedInUser(){return this.setLoggedInUser;}

    /**
     * loads the given FXML window to center
     * Takes the name of window as a parameter
     * @param file
     * @throws IOException
     */
    public void loadCenter(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/" + file));
        ScrollPane newScene = loader.load();

        borderPane.setCenter(newScene);
    }

    /**
     * loads the given FXML window to Top
     * Takes the name of window as a parameter
     * @param file
     * @throws IOException
     */
    public void loadTop(String file) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/" + file));
        Pane newScene = loader.load();

        borderPane.setTop(newScene);
    }

    /**
     * Loads center and takes file a parameter
     * @param s
     */
    public void openNewWindow(String s)  {
        try {
            loadCenter(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
