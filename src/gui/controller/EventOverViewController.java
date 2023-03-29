package gui.controller;

import be.Event;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
import gui.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class EventOverViewController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBoxCustomerView;
    private Model model;

    private static ObservableList<Event> activeEvents;

    public static boolean submitForDeletion = false;

    @FXML
    private ImageView imageCxl, imageEdit, imageEvent, imageEventExpanded,imageCxlExpanded, imageEditExpanded;

    private String cxlURL = "data/Images/Cancel.png";

    private String editURL = "data/Images/Edit.png";

    private String eventURL = "data/Images/Event.png";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();
            activeEvents = FXCollections.observableArrayList();
            activeEvents.addAll(model.getActiveEvents());
            displayActiveEvents();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayActiveEvents() {
        try {

            for (Event events : activeEvents) {
                int id = events.getEventID();
                String day1 = "";
                Label day = new Label();
                Label month = new Label();
                Label year = new Label();
                imageEvent = new ImageView();
                imageCxl = new ImageView();
                imageEdit = new ImageView();
                Label title = new Label();
                Label startTime = new Label();
                Label location = new Label();
                imageEventExpanded = new ImageView();
                imageCxlExpanded = new ImageView();
                imageEditExpanded = new ImageView();
                Label titleExpanded = new Label();
                Label startTimeExpanded = new Label();
                Label locationExpanded = new Label();


                Pane outerPane = new Pane();
                ExpansionPanel expPanel = new ExpansionPanel();
                Pane collapsedPane = new Pane();
                Pane expandedPane = new Pane();

                day.setText(String.valueOf(events.getEventDate().getDayOfMonth()));
                if (events.getEventDate().getDayOfMonth() <= 9) {
                    day1 = "0" + day.getText();
                    day.setText(day1);
                }


                month.setText(String.valueOf(events.getEventDate().getMonth()).substring(0, 3));
                year.setText(String.valueOf(events.getEventDate().getYear()));


                title.setText(events.getEventTitle());
                titleExpanded.setText(events.getEventTitle());

                startTime.setText(events.getEventStartTime().toString().substring(0, 5));
                location.setText(events.getEventLocation());

                startTimeExpanded.setText(events.getEventStartTime().toString().substring(0, 5));
                locationExpanded.setText(events.getEventLocation());


                outerPane.getStylesheets().add(getClass().getResource("/gui/view/Main.css").toExternalForm());
                outerPane.getStyleClass().add("outerPane");
                title.getStyleClass().add("lblEventTitle");
                startTime.getStyleClass().add("lblStartTime");
                location.getStyleClass().add("lblLocation");
                collapsedPane.getStyleClass().add("innerPane");
                titleExpanded.getStyleClass().add("lblEventTitle");
                startTimeExpanded.getStyleClass().add("lblStartTime");
                locationExpanded.getStyleClass().add("lblLocation");
                expandedPane.getStyleClass().add("innerPane");
                expPanel.getStyleClass().add("expansionPanel");
                day.getStyleClass().add("lblEventDay");
                month.getStyleClass().add("lblMonthAndYear");
                year.getStyleClass().add("lblMonthAndYear");

                DropShadow shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));

                day.setEffect(shadow);
                day.setLayoutX(30);
                day.setLayoutY(50);
                day.minHeight(100);
                day.minWidth(100);
                day.setAlignment(Pos.CENTER);
                month.setEffect(shadow);
                month.setLayoutX(110);
                month.setLayoutY(70);
                year.setEffect(shadow);
                year.setLayoutX(110);
                year.setLayoutY(95);
                expPanel.setLayoutX(200);
                expPanel.setLayoutY(25);
                outerPane.setMinHeight(200);
                collapsedPane.prefHeight(200);
                collapsedPane.prefWidth(1200);
                titleExpanded.setEffect(shadow);
                titleExpanded.setAlignment(Pos.CENTER);
                titleExpanded.setMinWidth(1200);
                titleExpanded.setMinHeight(60);
                startTimeExpanded.setEffect(shadow);
                startTimeExpanded.setAlignment(Pos.CENTER);
                startTimeExpanded.setLayoutY(45);
                startTimeExpanded.setMinWidth(1200);
                startTimeExpanded.setMinHeight(60);
                locationExpanded.setEffect(shadow);
                locationExpanded.setLayoutY(90);
                locationExpanded.setAlignment(Pos.CENTER);
                locationExpanded.setMinWidth(1200);
                locationExpanded.setMinHeight(60);
                title.setEffect(shadow);
                title.setAlignment(Pos.CENTER);
                title.setMinWidth(1200);
                title.setMinHeight(60);
                startTime.setEffect(shadow);
                startTime.setAlignment(Pos.CENTER);
                startTime.setLayoutY(45);
                startTime.setMinWidth(1200);
                startTime.setMinHeight(60);
                location.setEffect(shadow);
                location.setLayoutY(90);
                location.setAlignment(Pos.CENTER);
                location.setMinWidth(1200);
                location.setMinHeight(60);

                outerPane.getChildren().add(day);
                outerPane.getChildren().add(month);
                outerPane.getChildren().add(year);


                imageCxl.setOnMouseClicked(event -> cancelEvent(events));
                imageEdit.setOnMouseClicked(event -> deleteEvent(events));  //TODO change this image to a trashcan, and only visible if it is an Admin who logged in;
                imageCxl.setImage(loadImages(cxlURL));
                imageEdit.setImage(loadImages(editURL));
                imageEvent.setImage(loadImages(cxlURL));
                imageCxl.setScaleX(0.8);
                imageCxl.setScaleY(0.8);
                imageEdit.setScaleX(0.68);
                imageEdit.setScaleY(0.68);

                imageCxlExpanded.setImage(loadImages(cxlURL));
                imageEditExpanded.setImage(loadImages(editURL));
                imageEventExpanded.setImage(loadImages(cxlURL));
                imageCxlExpanded.setScaleX(0.8);
                imageCxlExpanded.setScaleY(0.8);
                imageEditExpanded.setScaleX(0.68);
                imageEditExpanded.setScaleY(0.68);


                imageCxl.setX(1100);
                imageCxl.setY(10);
                imageEdit.setX(1030);
                imageEdit.setY(10);
                imageEvent.setX(5);
                imageEvent.setY(10);
                imageCxl.setEffect(shadow);
                imageEdit.setEffect(shadow);

                imageCxlExpanded.setX(1100);
                imageCxlExpanded.setY(10);
                imageEditExpanded.setX(1030);
                imageEditExpanded.setY(10);
                imageEventExpanded.setX(5);
                imageEventExpanded.setY(10);
                imageCxlExpanded.setEffect(shadow);
                imageEditExpanded.setEffect(shadow);


                collapsedPane.getChildren().add(imageEventExpanded);
                collapsedPane.getChildren().add(titleExpanded);
                collapsedPane.getChildren().add(startTimeExpanded);
                collapsedPane.getChildren().add(locationExpanded);

                collapsedPane.getChildren().add(imageCxlExpanded);
                collapsedPane.getChildren().add(imageEditExpanded);

                expandedPane.setMinHeight(400);
                expandedPane.getChildren().add(imageEvent);
                expandedPane.getChildren().add(title);
                expandedPane.getChildren().add(startTime);
                expandedPane.getChildren().add(location);

                expandedPane.getChildren().add(imageCxl);
                expandedPane.getChildren().add(imageEdit);


                expPanel.setCollapsedContent(collapsedPane);
                expPanel.setExpandedContent(expandedPane);



                if (!events.isEventIsActive()) {
                    outerPane.setOpacity(0.5);
                    outerPane.setStyle("-fx-background-color: Gray");
                    expPanel.setStyle("-fx-background-color: Gray");
                }
                outerPane.getChildren().add(expPanel);

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
        Alert alert = createAlertWithDelete(Alert.AlertType.CONFIRMATION, "Cancel Event?", null,
                "Are you sure you want to cancel:\n" + "'" + event.getEventTitle() + "'\n" + "'" + event.getEventDate().getDayOfMonth() + "-" + event.getEventDate().getMonth() + "-" + event.getEventDate().getYear() + " " + event.getEventStartTime() + "'", "Submit for deletion",
                param -> submitForDeletion = true, ButtonType.YES, ButtonType.NO);

        alert.getDialogPane().getStylesheets().add("/gui/view/Main.css");
        alert.getDialogPane().getStyleClass().add("alertPane");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            try {
                model.cancelEvent(event.getEventID());
            } catch (SQLException e) {
                Alert error = new Alert(Alert.AlertType.ERROR, "Could not cancel Event" + "\n"+e, ButtonType.CANCEL);
                error.showAndWait();
            }
        }
    }

    public static Alert createAlertWithDelete(Alert.AlertType type, String title, String headerText,
                                              String message, String deletionMessage, Consumer<Boolean> deletionAction,
                                              ButtonType... buttonTypes) {
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
                Alert error = new Alert(Alert.AlertType.ERROR, "Could not delete Event from Database" + "\n"+e, ButtonType.CANCEL);
                error.showAndWait();
            }
        }
    }

}

