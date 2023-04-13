package gui.controller;

import be.Administrator;
import be.EventCoordinator;
import be.User;
import gui.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    @FXML private ComboBox comboBoxUserType;
    @FXML private Button btnSaveUser, btnChooseImage;
    @FXML private TextField txtFieldTlf, txtEmail, txtFieldUSerUserName, txtFieldUserFirstName, txtFieldPassword;
    @FXML private ImageView imgUserProfilePicture;
    @FXML private Pane imagePane;
    @FXML private Label lblCreateUser;
    private Model model;
    private byte[] data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();

        } catch (Exception e) {
            e.printStackTrace();
            displayError(e);
        }

        comboBoxUserType.getItems().add("Administrator");
        comboBoxUserType.getItems().add("Event Coordinator");

        btnSaveUser.setDisable(true);

        txtEmail.textProperty().addListener(observable -> isEmpty());
        txtFieldTlf.textProperty().addListener(observable -> isEmpty());
        txtFieldUSerUserName.textProperty().addListener(observable -> isEmpty());
        txtFieldUserFirstName.textProperty().addListener(observable -> isEmpty());
        comboBoxUserType.getSelectionModel().selectedItemProperty().addListener(observable -> isEmpty());
        imgUserProfilePicture.imageProperty().addListener(observable -> imageChosen());
        txtFieldPassword.textProperty().addListener(observable -> isEmpty());

    }
    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("!!ERROR!!");
        alert.setHeaderText("Something went wrong, \n ERROR:      " + t.getMessage());
        alert.showAndWait();

        System.out.println("error cured in CreateUserController.java");

        t.printStackTrace();
    }

    private void displayAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("!!Invaid!!");
        alert.setHeaderText("Something went wrong, \n" + message);
        alert.showAndWait();
    }

    public void handleChooseImage(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            Stage stage = (Stage) btnChooseImage.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null && selectedFile.getName().endsWith(".png") || selectedFile != null && selectedFile.getName().endsWith(".jpg")
                    || selectedFile != null && selectedFile.getName().endsWith(".gif")) {
                Image image = new Image(selectedFile.toURI().toString());
                imgUserProfilePicture.setImage(image);

                try {
                    data = Files.readAllBytes(selectedFile.getAbsoluteFile().toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            displayError(e);
        }
    }
    private void imageChosen() {
        if (!imgUserProfilePicture.imageProperty().isBound()) {
            imagePane.setBorder(null);
        }
    }

    private void isEmpty(){
        if (txtFieldUserFirstName.getText().isEmpty())  return;

        if (txtEmail.getText().isEmpty()) return;

        if (txtFieldUSerUserName.getText().isEmpty()) return;

        if (txtFieldTlf.getText().isEmpty()) return;

        if (txtFieldPassword.getText().isEmpty()) return;

        if (comboBoxUserType.getValue() == null) return;

        btnSaveUser.setDisable(false);
    }

    public void handleSaveUser(ActionEvent actionEvent) {
        if (checkData() || validPassword(txtFieldPassword.getText())) saveUser();

        txtFieldPassword.setText("");
    }

    private boolean checkData(){
        if(txtFieldUserFirstName.getText() == null || txtFieldUserFirstName.getText().isEmpty()){
            displayAlert("Missing firstName");
            btnSaveUser.setDisable(true);
            return false;
        } else if (txtFieldUSerUserName.getText() == null || txtFieldUSerUserName.getText().isEmpty()){
            displayAlert("Missing a Username");
            btnSaveUser.setDisable(true);
            return false;
        } else if (!txtEmail.getText().contains("@") || !txtEmail.getText().contains(".") || txtEmail.getText().contains(" ")) {
            displayAlert("Invalid Email");
            btnSaveUser.setDisable(true);
            return false;
        }
        btnSaveUser.setDisable(false);
        return true;

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

    private void saveUser(){
        User user;
        if (comboBoxUserType.getValue().equals("Administrator")){
            user = new Administrator(0,
                    txtFieldUSerUserName.getText(),
                    txtFieldUserFirstName.getText(),
                    txtEmail.getText(),
                    txtFieldTlf.getText(),
                    1);
        } else {
            user = new EventCoordinator(0,
                    txtFieldUSerUserName.getText(),
                    txtFieldUserFirstName.getText(),
                    txtEmail.getText(),
                    txtFieldTlf.getText(),
                    2);
        }
        if(imgUserProfilePicture != null){
            user.setProfilePicture(imgUserProfilePicture.getImage());
            user.setImageBytes(data);
        }

        try {
            model.createUser(user);
            model.setUserPassword(user, txtFieldPassword.getText());
        } catch (Exception e) {
            //TODO if fail try to delete user
            e.printStackTrace();
            displayError(e);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("User Successfully created");
        alert.showAndWait();

    }
}
