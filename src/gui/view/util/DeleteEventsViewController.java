package gui.view.util;

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
    private FlowPane flowpane;
    @FXML
    private VBox vBoxEventView;
    private Model model;
    private static ObservableList<Event> allEvents;

    public static boolean submitForDeletion = false;
    private int minHeightOuterPanel = 200;
    private double fullWith = 1200;

    private int expandedPaneHeight = 600;
    @FXML
    private ImageView imageCxl, imageEdit, imageEvent, imageEventExpanded, imageCxlExpanded, imageEditExpanded;

    private String cxlURL = "data/Images/Cancel.png";
    private String editURL = "data/Images/Edit.png";

    private String eventURL = "data/Images/Event.png";

    private DropShadow shadow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();
            allEvents = model.getAllEvents();
            shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));
            vBoxEventView.setFillWidth(true);
            displayActiveEvents();
            //allEventScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayActiveEvents() {
        try {
            for (Event events : allEvents) {

                //Outer pane contains datePane, and the "blue" inner-pane, with info about the event
                BorderPane outerPane = new BorderPane();

                int id = events.getEventID();
                //Entities to add to outer pane
                Pane datePane = createDatePane(events);

                BorderPane innerpane = createEventPane(events);

                innerpane = setImages(innerpane, events);
                //Add pane to left side of pane
                outerPane.setLeft(datePane);
                //Add eventPane to center of this pane
                outerPane.setCenter(innerpane);


                outerPane.setMinHeight(minHeightOuterPanel);


                if (!events.isEventIsActive()) {
                    outerPane.setOpacity(1);
                    outerPane.setStyle("-fx-background-color: LightGray");

                    vBoxEventView.getChildren().add(outerPane);
                }
            }
            vBoxEventView.setSpacing(10);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
            alert.showAndWait();
        }
    }

    private BorderPane setImages(BorderPane innerpane, Event events) {

        //Image cancel event
        imageCxl.setOnMouseClicked(event -> cancelEvent(events));
        //Image edit event
        imageEdit.setOnMouseClicked(event -> deleteEvent(events));  //TODO change this image to a trashcan, and only visible if it is an Admin who logged in;

        imageCxl.setImage(loadImages(cxlURL));
        imageEdit.setImage(loadImages(editURL));

        imageCxl.setEffect(shadow);
        imageEdit.setEffect(shadow);

        innerpane.setRight(imageCxl);
        innerpane.setLeft(imageEdit);
        return innerpane;
    }

    private BorderPane createEventPane(Event events) {
        BorderPane innerPane = new BorderPane();

        // Add to innerpane
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
        Label eventDescription = new Label();

        title.setText(events.getEventTitle());
        titleExpanded.setText(events.getEventTitle());

        startTime.setText(events.getEventStartTime().toString().substring(0, 5));
        location.setText(events.getEventLocation());

        startTimeExpanded.setText(events.getEventStartTime().toString().substring(0, 5));
        locationExpanded.setText(events.getEventLocation());

        eventDescription.setText(events.getEventDescription());



        //Add styling to the different things
        title.getStyleClass().add("lblEventTitle");
        startTime.getStyleClass().add("lblStartTime");
        location.getStyleClass().add("lblLocation");
        innerPane.getStyleClass().add("innerPane");

        titleExpanded.getStyleClass().add("lblEventTitle");
        startTimeExpanded.getStyleClass().add("lblStartTime");
        locationExpanded.getStyleClass().add("lblLocation");
        eventDescription.getStyleClass().add("lblEventDescription");


        title.setEffect(shadow);
        title.setAlignment(Pos.CENTER);
        title.setMinWidth(fullWith);
        title.setMinHeight(expandedPaneHeight * 0.15);
        startTime.setEffect(shadow);
        startTime.setAlignment(Pos.CENTER);
        startTime.setLayoutY(expandedPaneHeight * 0.1125);
        startTime.setMinWidth(fullWith);
        startTime.setMinHeight(expandedPaneHeight * 0.15);
        location.setEffect(shadow);
        location.setLayoutY(expandedPaneHeight * 0.225);
        location.setAlignment(Pos.CENTER);
        location.setMinWidth(fullWith);
        location.setMinHeight(expandedPaneHeight * 0.15);

        eventDescription.setLayoutY(200);
        eventDescription.setLayoutX(20);
        eventDescription.setWrapText(true);
        eventDescription.setMaxWidth(500);
        eventDescription.setMaxHeight(300);

        titleExpanded.setEffect(shadow);
        titleExpanded.setAlignment(Pos.CENTER);
        titleExpanded.setMinWidth(fullWith);
        titleExpanded.setMinHeight(expandedPaneHeight * 0.15);
        startTimeExpanded.setEffect(shadow);
        startTimeExpanded.setAlignment(Pos.CENTER);
        startTimeExpanded.setLayoutY(expandedPaneHeight * 0.1125);
        startTimeExpanded.setMinWidth(fullWith);
        startTimeExpanded.setMinHeight(expandedPaneHeight * 0.15);
        locationExpanded.setEffect(shadow);
        locationExpanded.setLayoutY(expandedPaneHeight * 0.225);
        locationExpanded.setAlignment(Pos.CENTER);
        locationExpanded.setMinWidth(fullWith);
        locationExpanded.setMinHeight(expandedPaneHeight * 0.15);

        title.setEffect(shadow);
        title.setAlignment(Pos.CENTER);
        title.setMinWidth(fullWith);
        title.setMinHeight(expandedPaneHeight * 0.15);
        startTime.setEffect(shadow);
        startTime.setAlignment(Pos.CENTER);
        startTime.setLayoutY(expandedPaneHeight * 0.1125);
        startTime.setMinWidth(fullWith);
        startTime.setMinHeight(expandedPaneHeight * 0.15);
        location.setEffect(shadow);
        location.setLayoutY(expandedPaneHeight * 0.225);
        location.setAlignment(Pos.CENTER);
        location.setMinWidth(fullWith);
        location.setMinHeight(expandedPaneHeight * 0.15);

        eventDescription.setLayoutY(200);
        eventDescription.setLayoutX(20);
        eventDescription.setWrapText(true);
        eventDescription.setMaxWidth(500);
        eventDescription.setMaxHeight(300);

        innerPane.getChildren().add(title);

        return innerPane;
    }

    private Pane createDatePane(Event events) {

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

        //Styling the date
        day.getStyleClass().add("lblEventDay");
        month.getStyleClass().add("lblMonthAndYear");
        year.getStyleClass().add("lblMonthAndYear");


        //Entities to add to outer pane
        day.setEffect(shadow);
        day.setLayoutX(30);
        day.setLayoutY(minHeightOuterPanel * 0.25);
        day.minHeight(minHeightOuterPanel * 0.5);
        day.minWidth(100);
        day.setAlignment(Pos.CENTER);

        month.setEffect(shadow);
        month.setLayoutX(110);
        month.setLayoutY(minHeightOuterPanel * 0.285);

        year.setEffect(shadow);
        year.setLayoutX(110);
        year.setLayoutY(minHeightOuterPanel * 0.5);

        Pane datePane = new Pane();
        datePane.getChildren().add(day);
        datePane.getChildren().add(month);
        datePane.getChildren().add(year);

        return datePane;

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


