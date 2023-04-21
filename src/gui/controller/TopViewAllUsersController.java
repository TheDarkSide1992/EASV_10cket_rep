package gui.controller;

import be.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class TopViewAllUsersController implements Initializable {
    @FXML
    private HBox btnHolderHBox;
    @FXML
    private ImageView imgLogo;

    private ControllerAssistant controllerAssistant;

    private DropShadow shadow = new DropShadow(0, -4, -4, Color.color(0, 0, 0, 1));

    private DropShadow noShadow = new DropShadow(0, 0, 0, Color.color(0, 0, 0, 1));

    private String url = "data/Images/10cketshort.png";
    private String userType = null;
    private User loggedInUser = null;

    private static Group group;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerAssistant = ControllerAssistant.getInstance();


        if (controllerAssistant.getLoggedInUser() != null) {
            loggedInUser = controllerAssistant.getLoggedInUser();
            userType = loggedInUser.getUserStringType();
        }
        Button[] buttons = assignButtonsToUsers(userType);
        addButtons(buttons);
        buttons[0].getStyleClass().add("btnTopButtonsClicked");
        setLogo();
        if (userType == null) {
            signInLabelStyling();
        } else {
            FlowPane flowPane = new FlowPane();
            flowPane.getChildren().add(logoutLabel());
            flowPane.getChildren().add(setUserName());

            flowPane.setAlignment(Pos.CENTER_RIGHT);
            btnHolderHBox.getChildren().add(flowPane);
        }

        //Opens a new view, if the admin is logged in
        //Check if logged in user is admin
        if (loggedInUser != null && loggedInUser.getUserTypeId()==1) {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/view/DeleteEventsView.fxml"), resources);
                Stage stage = new Stage();
                stage.setTitle("Not active events");
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Logged in as admin");
        }

    }

    private Button[] assignButtonsToUsers(String user) {
        //Define ALL buttons possible having in the topView
        Button upcomingEvents = new Button("Upcoming Events");
        Button allEvents = new Button("All Events");
        Button contact = new Button("Contact");
        Button createEvent = new Button("Create Event");
        Button manageTickets = new Button("Manage Tickets");
        Button makeCoordinator = new Button("Create New User");
        Button ticketRequests = new Button("Ticket Requests");

        group = new Group(upcomingEvents,allEvents,contact,createEvent,manageTickets,makeCoordinator,ticketRequests);

        //Give the buttons action listeners
        upcomingEvents.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> upcomingEvents());
        allEvents.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> allEvents());
        contact.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> contact());
        createEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> createEvent());
        manageTickets.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> manageTickets());
        makeCoordinator.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> createCoordinators());
        ticketRequests.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ticketRequests());

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    // Get the button that was clicked
                    Button clickedButton = (Button) e.getSource();

                    // Set the default style class for all buttons
                    upcomingEvents.getStyleClass().setAll("btnTopButtons");
                    allEvents.getStyleClass().setAll("btnTopButtons");
                    contact.getStyleClass().setAll("btnTopButtons");
                    createEvent.getStyleClass().setAll("btnTopButtons");
                    manageTickets.getStyleClass().setAll("btnTopButtons");
                    makeCoordinator.getStyleClass().setAll("btnTopButtons");
                    ticketRequests.getStyleClass().setAll("btnTopButtons");

                    // Set the new style class for the clicked button
                    clickedButton.getStyleClass().setAll("btnTopButtonsClicked");
                }
            }
        };

        upcomingEvents.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        allEvents.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        contact.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        createEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        manageTickets.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        makeCoordinator.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        ticketRequests.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

        //AssignButtons to all different users
        Button[] eventCoordinator = {upcomingEvents, allEvents, contact, createEvent, manageTickets, ticketRequests};
        Button[] administrator = {upcomingEvents, allEvents, contact, makeCoordinator};
        Button[] costumer = {upcomingEvents, allEvents, contact};
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

    private void clearStyle() {
        for (int i = 0; i < group.getChildren().size(); i++) {
            group.getChildren().get(i).getStyleClass().add("btnTopButtons");
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
    /**
     * Styling of labels with shadows
     * Gets style class from css file
     */
    private void signInLabelStyling() {
        DropShadow shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));
        Label signInLbl = new Label();
        signInLbl.setEffect(shadow);
        signInLbl.setText("Sign In");
        signInLbl.setAlignment(Pos.CENTER_RIGHT);

        //Add a listener to label
        signInLbl.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            handleSignIn(event);
        });
        signInLbl.getStyleClass().add("lblSignIn");
        btnHolderHBox.getChildren().add(signInLbl);
    }

    /**
     * Styling of labels with shadows
     * Gets style class from css file
     */
    private Label logoutLabel() {
        DropShadow shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));
        Label logout = new Label();
        logout.setEffect(shadow);
        logout.setText("Log Out");
        logout.setAlignment(Pos.CENTER_RIGHT);
        logout.setPadding(new Insets(10,100,0,0));

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
            controllerAssistant.setLoggedInUser(null);
        });
        logout.getStyleClass().add("lblSignIn");

        return logout;
    }
    /**
     * Inserts username in label
     * Gets style class from css file
     */
    private Label setUserName(){
        Label userName = new Label();
        FlowPane namePane = new FlowPane();
        userName.setText(controllerAssistant.getLoggedInUser().getUserFirstName() + "\n"
                + controllerAssistant.getLoggedInUser().getUserName());
        userName.setAlignment(Pos.BOTTOM_RIGHT);
        userName.setPadding(new Insets(0,150,0,0));
        userName.getStyleClass().add("lblMonthAndYear");
        return userName;
    }
    /**
     * Inserts the logo in the imgLogo Imageview
     */
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

    /**
     * Opens the LoginView when button is clicked
     * @param mouseEvent
     */
    public void handleSignIn(MouseEvent mouseEvent) {
        controllerAssistant.openNewWindow("LoginView.fxml");
    }
    /**
     * Opens the UpcomingEventView
     */
    public void upcomingEvents() {controllerAssistant.openNewWindow("UpcomingEventsView.fxml");}
    /**
     * Opens the AllEventView
     */
    private void allEvents() {
        controllerAssistant.openNewWindow("AllEventView.fxml");
    }
    /**
     * Opens the ContactView
     */
    private void contact() {
        controllerAssistant.openNewWindow("ContactView.fxml");
    }
    /**
     * Opens the CreateEventView
     */
    private void createEvent() {
        controllerAssistant.openNewWindow("CreateEventView.fxml");
    }
    /**
     * Opens the ManageTicketsView
     */
    private void manageTickets() {
        controllerAssistant.openNewWindow("ManageTicketsView.fxml");
    }
    /**
     * Opens the CreateUser
     */
    private void createCoordinators() {
        controllerAssistant.openNewWindow("CreateUser.fxml");
    }
    /**
     * Opens the TicketRequestView
     */
    private void ticketRequests() { controllerAssistant.openNewWindow("TicketRequestView");}

}
