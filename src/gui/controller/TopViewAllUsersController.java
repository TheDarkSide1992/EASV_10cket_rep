package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TopViewAllUsersController implements Initializable {
    @FXML
    private HBox btnHolderHBox;
    @FXML
    private ImageView imgLogo;

    private ControllerAssistant controllerAssistant;

    private String url = "data/Images/10cketshort.png";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //String userREAL = "Event Coordinator";
        //String userREAL = "Administrator";
        String user = null;
        if (new LoginViewController().getUserREAL() != null) {
            //user = new LoginViewController().indexController.getUser();
            user = new LoginViewController().getUserREAL();
        }
        Button[] buttons = assignButtonsToUsers(user);
        addButtons(buttons);
        setLogo();
        if (user == null) {
            signInLabelStyling();
        } else {
            logoutLabel();
        }
        controllerAssistant = ControllerAssistant.getInstance();
    }

    private Button[] assignButtonsToUsers(String user) {
        //Define ALL buttons possible having in the topView
        Button upcomingEvents = new Button("Upcoming Events");
        Button allEvents = new Button("All Events");
        Button calender = new Button("Calender");
        Button contact = new Button("Contact");
        Button prices = new Button("Prices");
        Button createEvent = new Button("Create Event");
        Button manageTickets = new Button("Manage Tickets");
        Button makeCoordinator = new Button("Create Event Coordinator");

        //Give the buttons action listeners
        upcomingEvents.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> upcomingEvents());
        allEvents.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> allEvents());
        calender.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> calender());
        contact.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> contact());
        prices.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> prices());
        createEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> createEvent());
        manageTickets.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> manageTickets());
        makeCoordinator.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> createCoordinators());


        //AssignButtons to all different users
        Button[] eventCoordinator = {upcomingEvents, allEvents, calender, contact, prices, createEvent, manageTickets};
        Button[] administrator = {upcomingEvents, allEvents, calender, contact, prices, makeCoordinator};
        Button[] costumer = {upcomingEvents, allEvents, calender, contact, prices};
        if (user == null) {
            return costumer;
        } else if (user.equals("Event Coordinator")) {
            return eventCoordinator;
        } else if (user.equals("Administrator")) {
            return administrator;
        } else {
            return costumer;
        }
    }

    /**
     * Adds buttons to GUI
     *
     * @param btnsToCreate
     */
    private void addButtons(Button[] btnsToCreate) {
        for (Button btn : btnsToCreate) {
            //Style the buttons
            btn.getStyleClass().add("btnTopButtons");
            Font font = Font.font("Courier New", FontWeight.BOLD, 14);
            btn.setFont(font);
            btnHolderHBox.getChildren().add(btn);
            //Position in BOX
            btnHolderHBox.setAlignment(Pos.BOTTOM_CENTER);

        }
    }

    private void signInLabelStyling() {
        DropShadow shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));
        Label signInLbl = new Label();
        signInLbl.setEffect(shadow);
        signInLbl.setText("Sig Up");
        signInLbl.setAlignment(Pos.CENTER_RIGHT);

        //Add a listener to label
        signInLbl.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            handleSignIn(event);
        });
        signInLbl.getStyleClass().add("lblSignIn");
        btnHolderHBox.getChildren().add(signInLbl);
    }

    void logoutLabel() {
        DropShadow shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));
        Label logout = new Label();
        logout.setEffect(shadow);
        logout.setText("Log Out");
        logout.setAlignment(Pos.CENTER_RIGHT);

        //Add a listener to label
        logout.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            btnHolderHBox.getChildren().remove(logout);
            Node node = btnHolderHBox.getChildren().get(0);
            btnHolderHBox.getChildren().clear();
            btnHolderHBox.getChildren().add(node);
            Button[] buttons = assignButtonsToUsers(null);
            addButtons(buttons);
            signInLabelStyling();
            upcomingEvents();
        });
        logout.getStyleClass().add("lblSignIn");
        btnHolderHBox.getChildren().add(logout);
    }

    private void setLogo() {
        try {
            InputStream stream = null;
            stream = new FileInputStream(url);
            Image logo = new Image(stream);
            imgLogo.setImage(logo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void handleSignIn(MouseEvent mouseEvent) {
        controllerAssistant.openNewWindow("LoginView.fxml");
    }

    public void upcomingEvents() {
        controllerAssistant.openNewWindow("EventOverView.fxml");
    }

    private void allEvents() {
        controllerAssistant.openNewWindow("AllEventView.fxml");
    }

    private void calender() {
        controllerAssistant.openNewWindow("CalendarView.fxml");
    }

    private void contact() {
        controllerAssistant.openNewWindow("ContactView.fxml");
    }

    private void prices() {
        controllerAssistant.openNewWindow("PriceView.fxml");
    }

    private void createEvent() {
        controllerAssistant.openNewWindow("CreateEventView.fxml");
    }

    private void manageTickets() {
        controllerAssistant.openNewWindow("");
    }

    private void createCoordinators() {
        controllerAssistant.openNewWindow("");
    }

}
