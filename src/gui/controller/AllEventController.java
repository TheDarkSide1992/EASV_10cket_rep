package gui.controller;

import be.Event;
import be.User;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
import gui.model.Model;
import gui.util.EventGUIUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static javafx.scene.paint.Color.MEDIUMBLUE;
import static javafx.scene.paint.Color.rgb;

public class AllEventController implements Initializable {
    @FXML
    public ScrollPane allEventScrollPane;
    @FXML
    public VBox vBoxAllEventView;
    private Model model;
    private static ObservableList<Event> allEvents;
    private User user;
    private ControllerAssistant controllerAssistant;

    public static boolean submitForDeletion = false;

    @FXML
    private ImageView imageCxl, imageEdit, imageEvent, imageEventExpanded, imageCxlExpanded, imageEditExpanded, imageBuyTicket, imageBuyTicketExpanded, imageDelete, imageDeleteExpanded;

    private String cxlURL = "data/Images/Cancel.png";
    private String editURL = "data/Images/Edit.png";

    private String eventURL = "data/Images/Event.png";

    private String deleteURL = "data/Images/Trash Can.png";
    private String buyTicket = "data/Images/Buy Ticket.png";

    public AllEventController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            controllerAssistant = ControllerAssistant.getInstance();
            user = controllerAssistant.getLoggedInUser();
            model = new Model();
            allEvents = model.getAllEvents();
            displayAllEvents();
            allEventScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays all events on the main page to the user
     */
    private void displayAllEvents() {
        try {
            for (Event events : allEvents) {
                EventGUIUtil egu = EventGUIUtil.getInstance();
                Label eventDay = new Label();
                String day1;
                eventDay.setText(String.valueOf(events.getEventDate().getDayOfMonth()));
                if (events.getEventDate().getDayOfMonth() <= 9) {
                    day1 = "0" + eventDay.getText();
                    eventDay.setText(day1);
                }
                Label monthAndYear = new Label(String.valueOf(events.getEventDate().getMonth()).substring(0, 3) + "\n" + events.getEventDate().getYear());
                Label title = new Label(events.getEventTitle());
                Label startTime = new Label(events.getEventStartTime().toString().substring(0, 5));
                Label location = new Label(events.getEventLocation());
                location.setOnMouseClicked(event -> handleLocation(events));
                location.setOnMouseEntered(event -> hoverLocation(location));
                location.setOnMouseExited(event -> unHoverLocation(location));
                Label eventTitleExpanded = new Label(events.getEventTitle());
                Label eventStartTimeExpanded = new Label(events.getEventStartTime().toString().substring(0, 5));
                Label eventLocationExpanded = new Label(events.getEventLocation());
                eventLocationExpanded.setOnMouseClicked(event -> handleLocation(events));
                eventLocationExpanded.setOnMouseEntered(event -> hoverLocation(eventLocationExpanded));
                eventLocationExpanded.setOnMouseExited(event -> unHoverLocation(eventLocationExpanded));
                Label eventDescription = new Label(events.getEventDescription());
                Label lblOwner = new Label("Event Coordinator:");
                Label lblCollaborator = new Label("Event Collaborator:");
                Label eventOwner = new Label(events.getEventCoordinator());
                Label eventCollaborator = new Label(events.getEventCollaborator());

                imageEvent = new ImageView();
                imageCxl = new ImageView();
                imageEdit = new ImageView();
                imageBuyTicket = new ImageView();
                imageDelete = new ImageView();
                imageEventExpanded = new ImageView();
                imageCxlExpanded = new ImageView();
                imageEditExpanded = new ImageView();
                imageBuyTicketExpanded = new ImageView();
                imageDeleteExpanded = new ImageView();

                ExpansionPanel expPanel = new ExpansionPanel();
                FlowPane outerPane = new FlowPane();
                BorderPane collapsedPane = new BorderPane();
                BorderPane expandedPane = new BorderPane();


                egu.setStyleSheetsAndClass(outerPane, title, startTime, location, collapsedPane, eventTitleExpanded, eventStartTimeExpanded, eventLocationExpanded, expandedPane, expPanel, eventDay, monthAndYear, eventDescription, lblOwner, lblCollaborator);
                egu.setEventDayPlacement(eventDay);
                egu.setEventMonthAndYearPlacement(monthAndYear);
                egu.setEventTitlePlacement(eventTitleExpanded, title);
                egu.setEventStartTimePlacement(eventStartTimeExpanded, startTime);
                egu.setEventLocationPlacement(location, eventLocationExpanded);
                egu.setEventDescriptionPlacement(eventDescription);
                egu.setOwnerAndCollaboratorStyling(lblOwner, lblCollaborator, eventOwner, eventCollaborator);

                imageCxl.setOnMouseClicked(event -> cancelEvent(events));
                imageCxl.setImage(loadImages(cxlURL));
                egu.setImageCxlPlacement(imageCxl);

                imageEdit.setOnMouseClicked(event -> deleteEvent(events));
                imageEdit.setImage(loadImages(editURL));
                egu.setImageEditPlacement(imageEdit);

                if (events.getEventImage() == null || events.getImageByte().length < 10) {
                    imageEvent.setImage(loadImages(cxlURL));
                } else imageEvent.setImage(events.getEventImage());

                imageEditExpanded.setImage(loadImages(editURL));
                egu.setImageEditExpandedPlacement(imageEditExpanded);

                imageDelete.setImage(loadImages(deleteURL));
                imageDeleteExpanded.setImage(loadImages(deleteURL));
                egu.setImageDeletePlacement(imageDelete);
                egu.setImageDeleteExpandedPlacement(imageDeleteExpanded);


                if (events.getEventImage() == null || events.getImageByte().length < 10) {
                    imageEventExpanded.setImage(loadImages(cxlURL));
                } else imageEventExpanded.setImage(events.getEventImage());

                egu.setImageEventExpandedPlacement(imageEventExpanded);

                imageCxlExpanded.setImage(loadImages(cxlURL));
                egu.setImageCxlExpandedPlacement(imageCxlExpanded);

                if (user != null && user.getUserTypeId() == 2) {
                    imageCxl.setOnMouseClicked(event -> cancelEvent(events));
                    imageEdit.setOnMouseClicked(event -> editEvent(events));
                    imageCxlExpanded.setOnMouseClicked(event -> cancelEvent(events));
                    imageEditExpanded.setOnMouseClicked(event -> editEvent(events));
                    egu.setCollapsedEventCoPaneChildren(collapsedPane, imageEvent, title, startTime, location, imageEdit, imageCxl);
                    egu.setExpandedEventCoPaneChildren(expandedPane, eventTitleExpanded, eventStartTimeExpanded, eventLocationExpanded, eventDescription, imageEventExpanded, imageEditExpanded, imageCxlExpanded, eventOwner, eventCollaborator, lblOwner, lblCollaborator);
                } else if (user != null && user.getUserTypeId() == 1) {
                    imageCxl.setOnMouseClicked(event -> cancelEvent(events));
                    imageDelete.setOnMouseClicked(event -> deleteEvent(events));
                    imageCxlExpanded.setOnMouseClicked(event -> cancelEvent(events));
                    imageDeleteExpanded.setOnMouseClicked(event -> deleteEvent(events));
                    egu.setCollapsedAdminPaneChildren(collapsedPane, imageEvent, title, startTime, location, imageDelete, imageCxl);
                    egu.setExpandedAdminPaneChildren(expandedPane, eventTitleExpanded, eventStartTimeExpanded, eventLocationExpanded, eventDescription, imageEventExpanded, imageDeleteExpanded, imageCxlExpanded, eventOwner, eventCollaborator, lblOwner, lblCollaborator);
                } else {
                    egu.setBuyTicketPlacement(imageBuyTicket);
                    imageBuyTicket.setOnMouseClicked(event -> buyTickets(events));
                    egu.setBuyTicketPlacement(imageBuyTicketExpanded);
                    imageBuyTicketExpanded.setOnMouseClicked(event -> buyTickets(events));
                    egu.setCollapsedCustomerPaneChildren(collapsedPane, imageEvent, title, startTime, location, imageBuyTicket);
                    egu.setExpandedCustomerPaneChildren(expandedPane, eventTitleExpanded, eventStartTimeExpanded, eventLocationExpanded, eventDescription, imageEventExpanded, imageBuyTicketExpanded, eventOwner, eventCollaborator, lblOwner, lblCollaborator);
                }

                egu.setExpPanelPlacementAndChildren(expPanel, collapsedPane, expandedPane);
                egu.setOuterPanePlacementAndChildren(outerPane, eventDay, monthAndYear, expPanel);


                if (!events.isEventIsActive()) {
                    outerPane.setOpacity(1);
                    outerPane.setStyle("-fx-background-color: LightGray");
                    expPanel.setStyle("-fx-background-color: LightGray");
                }

                vBoxAllEventView.getChildren().add(outerPane);

            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
            alert.showAndWait();
        }
    }

    /**
     * When the Buy Ticket image is clicked, this opens up a new window so that the user can request to buy ticket
     * @param events
     */
    private void buyTickets(Event events) {
        BuyTicketViewController buyTicketViewController = new BuyTicketViewController();

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setController(buyTicketViewController);
            loader.setLocation(getClass().getResource("/gui/view/BuyTicketView.fxml"));
            buyTicketViewController.setBuyTicketEvent(events);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not open new window", ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

    /**
     * When hovering over the Location of the event, the Event changes color to indicate it can be clicked on.
     * @param location
     */
    private void hoverLocation(Label location) {
        location.setUnderline(true);
        location.setTextFill(MEDIUMBLUE);
    }

    /**
     * When the location label no longer is hovered over, it should return to its normal color and not be underlined anymore
     * @param location
     */
    private void unHoverLocation(Label location) {
        location.setUnderline(false);
        location.setTextFill(rgb(243, 218, 218));
    }

    /**
     * When the location label is clicked open up the users browser and search for the attached URL
     * @param events
     */
    private void handleLocation(Event events) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(events.getEventLocationURL());
            desktop.browse(oURL);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "URL not specified", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Helps with loading images into the imageview
     * @param url
     * @return
     */
    private Image loadImages(String url) {
        Image image = null;
        try {
            InputStream img = new FileInputStream(url);
            image = new Image(img);
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load an image, following error occurred:\n" + e, ButtonType.CANCEL);
            alert.showAndWait();
        }
        return image;

    }

    /**
     * When the Event Coordinator clicks the cancel button, he is promted with a dialog box asking if he is sure he wants to cancel the event
     * Furthermore he can submit it for deletion so that the admin can erase the event from the Database if needed.
     * @param event
     */
    private void cancelEvent(Event event) {
        Alert alert = createAlertWithDelete(Alert.AlertType.CONFIRMATION, "Cancel Event?", null, "Are you sure you want to cancel:\n" + "'" + event.getEventTitle() + "'\n" + "'" + event.getEventDate().getDayOfMonth() + "-" + event.getEventDate().getMonth() + "-" + event.getEventDate().getYear() + " " + event.getEventStartTime() + "'", "Submit for deletion", param -> submitForDeletion = true, ButtonType.YES, ButtonType.NO);
        alert.getDialogPane().getStylesheets().add("/gui/view/Main.css");
        alert.getDialogPane().getStyleClass().add("alertPane");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            try {
                model.cancelEvent(event.getEventID());
            } catch (SQLException e) {
                Alert error = new Alert(Alert.AlertType.ERROR, "Could not cancel Event" + "\n" + e, ButtonType.CANCEL);
                error.showAndWait();
            }
            if (submitForDeletion = true) {
                try {
                    model.submitForDeletion(event);
                } catch (Exception ex) {
                    Alert error1 = new Alert(Alert.AlertType.ERROR, "Event could not be submitted for deletion" + ex);
                }

            }
        }
    }

    /**
     * Creates a customized Alert which is styled and adds a checkbox for submit deletion.
     * @param type
     * @param title
     * @param headerText
     * @param message
     * @param deletionMessage
     * @param deletionAction
     * @param buttonTypes
     * @return
     */
    public static Alert createAlertWithDelete(Alert.AlertType type, String title, String headerText, String message, String deletionMessage, Consumer<Boolean> deletionAction, ButtonType... buttonTypes) {
        Alert alert = new Alert(type);
        // Need to force the alert to layout in order to grab the graphic,
        // as we are replacing the dialog pane with a custom pane
        alert.getDialogPane().getStylesheets().add("/gui/view/Main.css");
        alert.getDialogPane().getStyleClass().add("alertPane");
        Node graphic = alert.getDialogPane().getGraphic();
        // Create a new dialog pane that has a checkbox instead of the hide/show details button
        // Use the supplied callback for the action of the checkbox
        alert.setDialogPane(new DialogPane() {
            @Override
            protected Node createDetailsButton() {
                CheckBox delete = new CheckBox();
                delete.setText(deletionMessage);
                delete.setOnAction(e -> deletionAction.accept(delete.isSelected()));
                delete.getStylesheets().add("/gui/view/Main.css");
                delete.getStyleClass().add("alertPane");
                return delete;
            }
        });
        alert.getDialogPane().getButtonTypes().addAll(buttonTypes);
        alert.getDialogPane().setContentText(message);
        // Fool the dialog into thinking there is some expandable content
        // a Group won't take up any space if it has no children
        alert.getDialogPane().setExpandableContent(new Group());
        alert.getDialogPane().setExpanded(true);
        // Reset the dialog graphic using the default style
        alert.getDialogPane().setGraphic(graphic);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        return alert;
    }

    /**
     * When the trashcan is clicked by the admin this triggers an Alert which asks if he is sure he wants to delete the event from the Database
     * @param event
     */
    private void deleteEvent(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete:\n" + "'" + event.getEventTitle() + "'\n" + "'" + event.getEventDate().getDayOfMonth() + "-" + event.getEventDate().getMonth() + "-" + event.getEventDate().getYear() + " " + event.getEventStartTime() + "'" + "\n WARNING: DELETING EVENTS WILL MAKE THEM INVISIBLE FOR CUSTOMERS", ButtonType.YES, ButtonType.NO);
        alert.getDialogPane().getStylesheets().add("/gui/view/Main.css");
        alert.getDialogPane().getStyleClass().add("alertPane");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            try {
                model.deleteEvent(event.getEventID());
                controllerAssistant.loadCenter("AllEventView.fxml");
            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR, "Could not delete Event from Database" + "\n" + e, ButtonType.CANCEL);
                error.showAndWait();
            }
        }
    }

    /**
     * Missing implementation
     * @param event
     */
    private void editEvent(Event event) {

    }

}

