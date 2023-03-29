package gui.controller;

import be.User;
import gui.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    @FXML private Button btnLogIn, btnCancel;
    @FXML private TextField txtUserName, txtPassword;

    private ControllerAssistant controllerAssistant;
    private Model model;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            controllerAssistant = ControllerAssistant.getInstance();
            model = new Model();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
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
    private void displayAlert(String alert){
        Alert alertMessage = new Alert(Alert.AlertType.INFORMATION);
        alertMessage.setTitle("Alert");
        alertMessage.setHeaderText(alert);
        alertMessage.showAndWait();
    }

    public void handleLogIn(ActionEvent actionEvent) {
        if (txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty()) displayAlert("Missing Username or Password.");

        if (txtPassword.getText().length() < 8) displayAlert("password is Incorrect");

        if (!validPassword(txtPassword.getText())) displayAlert("password contain illegal characters");

        String password = txtPassword.getText().trim();
        System.out.println(password);

        String userName = txtUserName.getText().trim();

        try {
            user = model.checkLogIn(userName, password);
            controllerAssistant.setLoggedInUser(user);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Successfully login to " + user.getUserFirstName());
            alert.showAndWait();

        } catch (Exception e){
            displayError(e);
            e.printStackTrace();
        }


    }

    public boolean validPassword(String password){
        String specialChars = "!,.:;<>\\/()#%=+?'*";
        if (password.length() >= 8){
            for (int i = 0; i < password.length() - 1; i++) {
                for (int j = 0; j < specialChars.length() - 1; j++){
                    if(password.charAt(i) == specialChars.charAt(j)){
                        return false;
                    }
                }
            }
        }else if (password.length() < 8){
            return false;
        }

        return true;
    }

    public void handleCancel(ActionEvent actionEvent) {
        controllerAssistant.openNewWindow("EventOverView.fxml");
    }
}
