package gui.controller;

import be.Event;
import gui.model.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class DeleteEventsViewController implements Initializable {
    @FXML
    private VBox vBoxEventView;
    private Model model;
    private static ObservableList<Event> eventsForDeletion;

    public static boolean submitForDeletion = false;

    @FXML
    private ImageView imageCxl, imageEdit, imageEvent;

    private String cxlURL = "data/Images/Cancel.png";
    private String deleteURL = "data/Images/Trash Can.png";
    private String editURL = "data/Images/Edit.png";

    private String eventURL = "data/Images/Event.png";

    private DropShadow shadow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();
            eventsForDeletion = model.getSubmittedForDeletion();
            shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));
            vBoxEventView.setFillWidth(true);
            displayActiveEvents();
            eventsForDeletion = model.getSubmittedForDeletion();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays all events that has been submitted for deletion
     */
    private void displayActiveEvents() {
        try {
            for (Event events : eventsForDeletion) {

                //Outer pane contains datePane, and the "blue" inner-pane, with info about the event
                BorderPane outerPane = new BorderPane();

                int id = events.getEventID();
                //Entities to add to outer pane
                outerPane = createDatePane(events, outerPane);
                BorderPane innerpane = createEventPane(events);

                innerpane = setImages(innerpane, events);
                //Add eventPane to center of this pane
                outerPane.setCenter(innerpane);
                //if event isn't active background color is changed
                if (!events.isEventIsActive()) {
                    outerPane.setOpacity(1);
                    outerPane.setStyle("-fx-background-color: #a886b0");


                    vBoxEventView.getChildren().add(outerPane);
                }
            }
            vBoxEventView.setSpacing(20);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
            alert.showAndWait();
        }
    }

    /**
     * Creates ImageViews for events that has been submitted for deletion
     * 
     * @param innerpane
     * @param events
     * @return
     */
    private BorderPane setImages(BorderPane innerpane, Event events) {

        imageEvent = new ImageView();
        imageCxl = new ImageView();
        imageEdit = new ImageView();

        //Image cancel event
        imageCxl.setOnMouseClicked(event -> cancelEvent(events));
        //Image delete event
        imageEdit.setOnMouseClicked(event -> deleteEvent(events));

        imageCxl.setImage(loadImages(cxlURL));
        imageEdit.setImage(loadImages(deleteURL));

        imageCxl.setEffect(shadow);
        imageEdit.setEffect(shadow);

        VBox vBox1 = new VBox(imageCxl);
        vBox1.setAlignment(Pos.CENTER);
        VBox vBox2 = new VBox(imageEdit);
        vBox2.setAlignment(Pos.CENTER);

        innerpane.setRight(vBox1);
        innerpane.setLeft(vBox2);
        return innerpane;
    }

    private BorderPane createEventPane(Event events) {
        BorderPane innerPane = new BorderPane();

        // Add to inner-pane
        Label title = new Label();
        Label startTime = new Label();
        Label location = new Label();

        Label eventDescription = new Label();

        VBox eventBox = new VBox();


        title.setText(events.getEventTitle());

        startTime.setText(events.getEventStartTime().toString().substring(0, 5));
        location.setText(events.getEventLocation());

        eventDescription.setText(events.getEventDescription());

        innerPane.getStylesheets().add(getClass().getResource("/gui/view/Main.css").toExternalForm());

        //Add styling to the different things
        title.getStyleClass().add("lblEventTitle");
        startTime.getStyleClass().add("lblStartTime");
        location.getStyleClass().add("lblLocation");
        innerPane.getStyleClass().add("innerPane");

        eventDescription.getStyleClass().add("lblEventDescription");


        title.setEffect(shadow);
        title.setAlignment(Pos.CENTER);
        eventBox.getChildren().add(title);


        startTime.setEffect(shadow);
        startTime.setAlignment(Pos.CENTER);
        eventBox.getChildren().add(startTime);


        location.setEffect(shadow);
        location.setAlignment(Pos.CENTER);
        eventBox.getChildren().add(location);

        eventBox.setFillWidth(true);
        eventBox.setAlignment(Pos.CENTER);

        eventDescription.setWrapText(true);
        eventBox.getChildren().add(eventDescription);

        innerPane.setCenter(eventBox);

        return innerPane;
    }

    private BorderPane createDatePane(Event events, BorderPane updatedPane) {

        String day1 = "";
        Label day = new Label();
        Label month = new Label();
        Label year = new Label();

        day.setText(String.valueOf(events.getEventDate().getDayOfMonth()));
        if (events.getEventDate().getDayOfMonth() <= 9) {
            day1 = "0" + day.getText();
            day.setText(day1);
        }


        month.setText(String.valueOf(events.getEventDate().getMonth()).substring(0, 3));
        year.setText(String.valueOf(events.getEventDate().getYear()));

        updatedPane.getStylesheets().add(getClass().getResource("/gui/view/Main.css").toExternalForm());

        //Styling the date
        day.getStyleClass().add("lblEventDay");
        month.getStyleClass().add("lblMonthAndYear");
        year.getStyleClass().add("lblMonthAndYear");


        //Entities to add to outer pane
        day.setEffect(shadow);
        month.setEffect(shadow);
        year.setEffect(shadow);

        HBox date = new HBox();
        date.getChildren().add(day);
        date.setAlignment(Pos.CENTER);
        date.setMinWidth(100);

        VBox monthYear = new VBox();
        monthYear.getChildren().add(month);
        monthYear.getChildren().add(year);
        monthYear.setAlignment(Pos.CENTER);

        date.getChildren().add(monthYear);

        updatedPane.setLeft(date);

        return updatedPane;
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
                Alert error = new Alert(Alert.AlertType.ERROR, "Could not cancel Event" + "\n" + e, ButtonType.CANCEL);
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
                Alert error = new Alert(Alert.AlertType.ERROR, "Could not delete Event from Database" + "\n" + e, ButtonType.CANCEL);
                error.showAndWait();
            }
        }
    }
}


