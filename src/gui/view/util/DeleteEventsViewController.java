package gui.view.util;

import be.Event;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
import gui.model.Model;
import javafx.collections.FXCollections;
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

public class DeleteEventsViewController implements Initializable {
    @FXML
    private VBox vBoxEventView;
    private Model model;

    private static ObservableList<Event> activeEvents;

    public static boolean submitForDeletion = false;

    @FXML
    private ImageView imageCxl, imageEdit, imageEvent;

    private String cxlURL = "data/Images/Cancel.png";

    private String editURL = "data/Images/Edit.png";

    private String eventURL = "data/Images/Event.png";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();
            activeEvents = FXCollections.observableArrayList();
            //Gets absolutely ALL events
            activeEvents.addAll(model.getActiveEvents());
            displayActiveEvents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayActiveEvents() {
        try {
            for (Event events : activeEvents) {
                if (!events.isEventIsActive()) {

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

                    Pane outerPane = new Pane();
                    ExpansionPanel expPanel = new ExpansionPanel();
                    Pane innerPane = new Pane();

                    day.setText(String.valueOf(events.getEventDate().getDayOfMonth()));
                    if (events.getEventDate().getDayOfMonth() <= 9) {
                        day1 = "0" + day.getText();
                        day.setText(day1);
                    }


                    month.setText(String.valueOf(events.getEventDate().getMonth()).substring(0, 3));
                    year.setText(String.valueOf(events.getEventDate().getYear()));


                    title.setText(events.getEventTitle());

                    startTime.setText(events.getEventStartTime().toString().substring(0, 5));
                    location.setText(events.getEventLocation());


                    outerPane.getStylesheets().add(getClass().getResource("/gui/view/Main.css").toExternalForm());
                    outerPane.getStyleClass().add("outerPane");
                    title.getStyleClass().add("lblEventTitle");
                    startTime.getStyleClass().add("lblStartTime");
                    location.getStyleClass().add("lblLocation");
                    innerPane.getStyleClass().add("innerPane");
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
                    innerPane.prefHeight(200);
                    innerPane.prefWidth(1200);
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


                    imageCxl.setX(1100);
                    imageCxl.setY(10);
                    imageEdit.setX(1030);
                    imageEdit.setY(10);
                    imageEvent.setX(5);
                    imageEvent.setY(10);
                    imageCxl.setEffect(shadow);
                    imageEdit.setEffect(shadow);


                    innerPane.getChildren().add(imageEvent);
                    innerPane.getChildren().add(title);
                    innerPane.getChildren().add(startTime);
                    innerPane.getChildren().add(location);

                    innerPane.getChildren().add(imageCxl);
                    innerPane.getChildren().add(imageEdit);


                    expPanel.setCollapsedContent(innerPane);
                    Pane expandedPane = new Pane();
                    //Add to the expanded panel
                    expandedPane.setMinHeight(200);
                    expPanel.setExpandedContent(expandedPane);


                    outerPane.setOpacity(0.5);
                    outerPane.setStyle("-fx-background-color: Gray");
                    expPanel.setStyle("-fx-background-color: Gray");

                    outerPane.getChildren().add(expPanel);

                    vBoxEventView.getChildren().add(outerPane);
                }
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

