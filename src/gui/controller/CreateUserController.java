package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    @FXML private ComboBox comboBoxUserType;
    @FXML private Button btnSaveUser;
    @FXML private TextField txtFieldTlf;
    @FXML private TextField txtEventOwner;
    @FXML private Button btnChooseImage;
    @FXML private ImageView imgUserProfilePicture;
    @FXML private Pane imagePane;
    @FXML private TextField txtFieldUSerUserName;
    @FXML private TextField txtFieldUserFirstName;
    @FXML private Label lblCreateUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("!!ERROR!!");
        alert.setHeaderText("Something went wrong, \n ERROR:      " + t.getMessage());
        alert.showAndWait();

        t.printStackTrace();
    }
    public void handleChooseImage(ActionEvent actionEvent) {
    }

    public void handleSaveEvent(ActionEvent actionEvent) {
    }
}
