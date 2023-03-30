package gui.controller;

import be.Event;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
import gui.util.EventGUIUtil;
import gui.model.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class UpcomingEventsController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBoxCustomerView;
    private Model model;

    private static ObservableList<Event> activeEvents;

    public static boolean submitForDeletion = false;

    @FXML
    private ImageView imageCxl, imageEdit, imageEvent, imageEventExpanded, imageCxlExpanded, imageEditExpanded;

    private String cxlURL = "data/Images/Cancel.png";

    private String editURL = "data/Images/Edit.png";

    private String eventURL = "data/Images/Event.png";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();
            activeEvents = model.getActiveEvents();
            displayActiveEvents();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayActiveEvents() {
        try {
            for (Event events : activeEvents) {
                EventGUIUtil egu = EventGUIUtil.getInstance();

                Label eventDay = new Label();
                Label monthAndYear = new Label();
                Label title = new Label();
                Label startTime = new Label();
                Label location = new Label();
                Label eventTitleExpanded = new Label();
                Label eventStartTimeExpanded = new Label();
                Label eventLocationExpanded = new Label();
                Label eventDescription = new Label();

                imageEvent = new ImageView();
                imageCxl = new ImageView();
                imageEdit = new ImageView();
                imageEventExpanded = new ImageView();
                imageCxlExpanded = new ImageView();
                imageEditExpanded = new ImageView();

                ExpansionPanel expPanel = new ExpansionPanel();
                FlowPane outerPane = new FlowPane();
                Pane collapsedPane = new Pane();
                Pane expandedPane = new Pane();

                egu.setStyleSheetsAndClass(outerPane, title, startTime, location, collapsedPane, eventTitleExpanded, eventStartTimeExpanded, eventLocationExpanded, expandedPane, expPanel, eventDay, monthAndYear, eventDescription);
                egu.setTextInLabels(events, monthAndYear, title, eventTitleExpanded, startTime, location, eventStartTimeExpanded, eventLocationExpanded, eventDescription, eventDay);
                egu.setEventDayPlacement(eventDay);
                egu.setEventMonthAndYearPlacement(monthAndYear);
                egu.setEventTitlePlacement(eventTitleExpanded, title);
                egu.setEventStartTimePlacement(eventStartTimeExpanded, startTime);
                egu.setEventLocationPlacement(location, eventLocationExpanded);
                egu.setEventDescriptionPlacement(eventDescription);

                imageCxl.setOnMouseClicked(event -> cancelEvent(events));
                imageCxl.setImage(loadImages(cxlURL));
                egu.setImageCxlPlacement(imageCxl);

                imageEdit.setOnMouseClicked(event -> deleteEvent(events));  //TODO change this image to a trashcan, and only visible if it is an Admin who logged in;
                imageEdit.setImage(loadImages(editURL));
                egu.setImageEditPlacement(imageEdit);


                imageEvent.setImage(events.getEventImage());
                egu.setImageEventPlacement(imageEvent);


                imageEditExpanded.setImage(loadImages(editURL));
                egu.setImageEditExpandedPlacement(imageEditExpanded);

                imageEventExpanded.setImage(loadImages(cxlURL));
                egu.setImageEventExpandedPlacement(imageEventExpanded);

                imageCxlExpanded.setImage(loadImages(cxlURL));
                egu.setImageCxlExpandedPlacement(imageCxlExpanded);

                egu.setCollapsedPaneChildren(collapsedPane, imageEvent, title, startTime, location, imageCxl, imageEdit);
                egu.setExpandedPaneChildren(expandedPane, eventTitleExpanded, eventStartTimeExpanded, eventLocationExpanded, eventDescription, imageEventExpanded, imageCxlExpanded, imageEditExpanded);
                egu.setExpPanelPlacementAndChildren(expPanel, collapsedPane, expandedPane);
                egu.setOuterPanePlacementAndChildren(outerPane, eventDay, monthAndYear, expPanel);

                vBoxCustomerView.getChildren().add(outerPane);
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
            alert.showAndWait();
        }
    }

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
        }
    }

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

    private void deleteEvent(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete:\n" + "'" + event.getEventTitle() + "'\n" + "'" + event.getEventDate().getDayOfMonth() + "-" + event.getEventDate().getMonth() + "-" + event.getEventDate().getYear() + " " + event.getEventStartTime() + "'" + "\n WARNING: DELETING EVENTS WILL MAKE THEM INVISIBLE FOR CUSTOMERS", ButtonType.YES, ButtonType.NO);
        alert.getDialogPane().getStylesheets().add("/gui/view/Main.css");
        alert.getDialogPane().getStyleClass().add("alertPane");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            try {
                model.deleteEvent(event.getEventID()); //TODO get the event overview to update correctly
            } catch (SQLException e) {
                Alert error = new Alert(Alert.AlertType.ERROR, "Could not delete Event from Database" + "\n" + e, ButtonType.CANCEL);
                error.showAndWait();
            }
        }
    }

}

