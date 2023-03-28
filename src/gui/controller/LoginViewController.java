package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    @FXML private Button btnLogIn, btnCancel;
    @FXML private TextField txtUserName, txtPassword;

    private ControllerAssistant controllerAssistant;
    private static String userREAL = null;
    public static IndexController indexController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerAssistant = ControllerAssistant.getInstance();
    }
    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("!!ERROR!!");
        alert.setHeaderText("Something went wrong, \n ERROR:      " + t.getMessage());
        alert.showAndWait();

        t.printStackTrace();
    }
    private void displayAlert(String alert){
        Alert alertMessage = new Alert(Alert.AlertType.INFORMATION);
        alertMessage.setTitle("Alert");
        alertMessage.setHeaderText(alert);
        alertMessage.showAndWait();
    }

    public void handleLogIn(ActionEvent actionEvent) {
        try {
            indexController = new IndexController();
            if (txtUserName.getText().contains("a")) {
                userREAL = "Administrator";
                controllerAssistant.loadTop("TopViewAllUsers.fxml");

            } else if (txtUserName.getText().contains("e")) {
                userREAL = "Event Coordinator";
                controllerAssistant.loadTop("TopViewAllUsers.fxml");

            } else {
                userREAL = null;
                controllerAssistant.loadTop("TopViewAllUsers.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty()) displayAlert("Missing Username or Password.");

        if (txtPassword.getText().length() < 8) displayAlert("password is Incorrect");

        if (!validPassword(txtPassword.getText())) displayAlert("password contain illegal characters");

        String pasword = String.valueOf(txtPassword.getText().getBytes());
        System.out.println(pasword);

    }

    public boolean validPassword(String password){
        String specialChars = "!,.:;<>\\/()#%=+?'*";
        if (password.length() >= 8){
            for (int i = 0; i < password.length() - 1; i++) {
                for (int j = 0; j < specialChars.length() - 1; j++){
                    if(password.charAt(i) == specialChars.charAt(j)){
                        return true;
                    }
                }
            }
        }else if (password.length() < 8){
            return false;
        }

        return false;
    }

    public void handleCancel(ActionEvent actionEvent) {
        controllerAssistant.openNewWindow("EventOverView.fxml");
    }

    public String getUserREAL() {
        return userREAL;
    }
}
