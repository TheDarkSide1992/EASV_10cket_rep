package gui.controller;

import be.Administrator;
import be.Event;
import be.EventCoordinator;
import gui.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContactController implements Initializable {

    @FXML private HBox hBoxAdd, hBoxCord, hBoxAddUser;
    @FXML private VBox vbox;
    private Model model;

    private ArrayList<Administrator> allAdmins;
    private ArrayList<EventCoordinator> allCoordinators;
    private ControllerAssistant controllerAssistant;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allAdmins = new ArrayList<>();
        allCoordinators = new ArrayList<>();
        try {
            controllerAssistant = ControllerAssistant.getInstance();

            model = new Model();

            allAdmins.addAll(model.getAllAdmins());
            allCoordinators.addAll(model.getAllCoordinators());

            //System.out.println("Admin " + allAdmins);
            //System.out.println("Cord " +allCoordinators);

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

        hBoxAdd = new HBox();
        hBoxAdd.setPadding(new Insets(20,0,0,50));
        vbox.getChildren().add(hBoxAdd);

        setContactInfoAdmin();

        FlowPane outerPane2 = new FlowPane();

        Label roleEvent = new Label("Event Coordinator");
        roleEvent.getStyleClass().add("lblEventTitle");
        roleEvent.setLayoutX(100);
        roleEvent.setPadding(new Insets(60,0,0,50));
        outerPane2.getChildren().add(roleEvent);

        vbox.getChildren().add(outerPane2);

        hBoxCord = new HBox();
        hBoxCord.setPadding(new Insets(20,0,0,50));
        vbox.getChildren().add(hBoxCord);

        setContactInfoCord();

        FlowPane outerPane3 = new FlowPane();




        if(controllerAssistant.getLoggedInUser() != null &&controllerAssistant.getLoggedInUser().getUserStringType().equals("Administrator")) {
            Button btnAdd = new Button("Add User");
            btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addUser());

            hBoxAddUser = new HBox();
            hBoxAddUser.setPadding(new Insets(20, 0, 0, 50));

            btnAdd.setStyle(".btnTopButtons"); //TODO Style btn

            hBoxAddUser.getChildren().add(btnAdd);
            outerPane3.getChildren().add(hBoxAddUser);

            vbox.getChildren().add(hBoxAddUser);
        }


        if(true) {
            Button btnAdd = new Button("print pdf"); //TODO btn where used for pdf testing. need removal once there is a better implementation
            btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                try {
                    model.makeTicket(null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    displayError(e);
                }
            });

            hBoxAddUser = new HBox();
            hBoxAddUser.setPadding(new Insets(20, 0, 0, 50));

            btnAdd.setStyle(".btnTopButtons");

            hBoxAddUser.getChildren().add(btnAdd);
            outerPane3.getChildren().add(hBoxAddUser);

            vbox.getChildren().add(hBoxAddUser);
        }


    }



    private void setContactInfoAdmin() {
        for (Administrator admin: allAdmins){
            ImageView profilePicture = new ImageView();
            Label name = new Label(admin.getUserFirstName());
            Label email = new Label(admin.getUserEmail());
            Label phoneNumber = new Label(admin.getUserTLF());

            if (admin.getProfilePicture() != null) {
                profilePicture.setImage(admin.getProfilePicture());
            }

            FlowPane outerPane = new FlowPane();

            outerPane.getStylesheets().add(getClass().getResource("/gui/view/Main.css").toExternalForm());

            name.getStyleClass().add("lblMonth");
            email.getStyleClass().add("lblMonth");
            phoneNumber.getStyleClass().add("lblMonth");

            name.setLayoutX(100);
            email.setLayoutX(100);
            phoneNumber.setLayoutX(100);
            profilePicture.setLayoutX(100);

            name.setPadding(new Insets(15,0,0,60));
            email.setPadding(new Insets(15,0,0,60));
            phoneNumber.setPadding(new Insets(15,0,0,60));

            outerPane.getChildren().add(name);
            outerPane.getChildren().add(email);
            outerPane.getChildren().add(phoneNumber);
            outerPane.getChildren().add(profilePicture);

            hBoxAdd.getChildren().add(outerPane);

        }
    }

    private void setContactInfoCord() {
        for (EventCoordinator cord: allCoordinators){
            ImageView profilePicture = new ImageView();
            Label role = new Label("Event Coordinator");
            Label name = new Label(cord.getUserFirstName());
            Label email = new Label(cord.getUserEmail());
            Label phoneNumber = new Label(cord.getUserTLF());

            if (cord.getProfilePicture() != null) {
                profilePicture.setImage(cord.getProfilePicture());
            }

            FlowPane outerPane = new FlowPane();

            outerPane.getStylesheets().add(getClass().getResource("/gui/view/Main.css").toExternalForm());

            name.getStyleClass().add("lblMonth");
            email.getStyleClass().add("lblMonth");
            phoneNumber.getStyleClass().add("lblMonth");

            name.setLayoutX(100);
            email.setLayoutX(100);
            phoneNumber.setLayoutX(100);
            profilePicture.setLayoutX(100);


            name.setPadding(new Insets(15,0,0,60));
            email.setPadding(new Insets(15,0,0,60));
            phoneNumber.setPadding(new Insets(15,0,0,60));

            outerPane.getChildren().add(name);
            outerPane.getChildren().add(email);
            outerPane.getChildren().add(phoneNumber);
            outerPane.getChildren().add(profilePicture);

            hBoxCord.getChildren().add(outerPane);

        }
    }

    private void addUser(){
        try {
            controllerAssistant.loadCenter("CreateUser.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            displayError(e);
        }
    }
}
