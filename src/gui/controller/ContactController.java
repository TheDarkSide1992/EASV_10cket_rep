package gui.controller;

import be.Administrator;
import be.EventCoordinator;
import gui.model.Model;
import gui.util.EventGUIUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContactController implements Initializable {

    @FXML private HBox hBoxAdd1, hBoxCord1;
    @FXML private VBox vbox;
    private Model model;

    private ArrayList<Administrator> allAdmins;
    private ArrayList<EventCoordinator> allCoordinators;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allAdmins = new ArrayList<>();
        allCoordinators = new ArrayList<>();
        try {
            model = new Model();

            allAdmins.addAll(model.getAllAdmins());
            allCoordinators.addAll(model.getAllCoordinators());

            System.out.println("Admin " + allAdmins);
            System.out.println("Cord " +allCoordinators);

            setContactInfo();

        } catch (Exception e) {
            e.printStackTrace();
            displayError(e);
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

    private void setContactInfo() {
        FlowPane outerPane = new FlowPane();

        Label roleadmin = new Label("Administrator");
        roleadmin.getStyleClass().add("lblEventTitle");
        roleadmin.setLayoutX(100);
        roleadmin.setPadding(new Insets(60,0,0,50));
        outerPane.getChildren().add(roleadmin);


        vbox.getChildren().add(outerPane);

        hBoxAdd1 = new HBox();
        hBoxAdd1.setPadding(new Insets(20,0,0,50));
        vbox.getChildren().add(hBoxAdd1);

        setContactInfoAdmin();

        FlowPane outerPane2 = new FlowPane();

        Label roleEvent = new Label("Event Coordinator");
        roleEvent.getStyleClass().add("lblEventTitle");
        roleEvent.setLayoutX(100);
        roleEvent.setPadding(new Insets(60,0,0,50));
        outerPane2.getChildren().add(roleEvent);

        vbox.getChildren().add(outerPane2);

        hBoxCord1 = new HBox();
        hBoxCord1.setPadding(new Insets(20,0,0,50));
        vbox.getChildren().add(hBoxCord1);

        setContactInfoCord();
    }

    private void setContactInfoAdmin() {
        for (Administrator admin: allAdmins){
            Label name = new Label(admin.getUserFirstName());
            Label email = new Label(admin.getUserEmail());
            Label phoneNumber = new Label(admin.getUserTLF());

            FlowPane outerPane = new FlowPane();

            outerPane.getStylesheets().add(getClass().getResource("/gui/view/Main.css").toExternalForm());

            name.getStyleClass().add("lblMonth");
            email.getStyleClass().add("lblMonth");
            phoneNumber.getStyleClass().add("lblMonth");

            name.setLayoutX(100);
            email.setLayoutX(100);
            phoneNumber.setLayoutX(100);

            name.setPadding(new Insets(15,0,0,60));
            email.setPadding(new Insets(15,0,0,60));
            phoneNumber.setPadding(new Insets(15,0,0,60));

            outerPane.getChildren().add(name);
            outerPane.getChildren().add(email);
            outerPane.getChildren().add(phoneNumber);

            hBoxAdd1.getChildren().add(outerPane);

        }
    }

    private void setContactInfoCord() {
        for (EventCoordinator cord: allCoordinators){
            Label role = new Label("Event Coordinator");
            Label name = new Label(cord.getUserFirstName());
            Label email = new Label(cord.getUserEmail());
            Label phoneNumber = new Label(cord.getUserTLF());

            FlowPane outerPane = new FlowPane();

            outerPane.getStylesheets().add(getClass().getResource("/gui/view/Main.css").toExternalForm());

            name.getStyleClass().add("lblMonth");
            email.getStyleClass().add("lblMonth");
            phoneNumber.getStyleClass().add("lblMonth");

            name.setLayoutX(100);
            email.setLayoutX(100);
            phoneNumber.setLayoutX(100);

            name.setPadding(new Insets(15,0,0,60));
            email.setPadding(new Insets(15,0,0,60));
            phoneNumber.setPadding(new Insets(15,0,0,60));

            outerPane.getChildren().add(name);
            outerPane.getChildren().add(email);
            outerPane.getChildren().add(phoneNumber);

            hBoxAdd1.getChildren().add(outerPane);

            hBoxCord1.getChildren().add(outerPane);

        }
    }
}
