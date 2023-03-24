package gui.controller;

import be.Event;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class EventOverViewController implements Initializable {
    @FXML
    private VBox vBoxCustomerView;
    private Model model;
    
    public static int prefs = 0;

    @FXML
    private ImageView imageCxl,imageEdit,imageEvent;

    private String cxlURL = "data/Images/Cancel.png";

    private String editURL = "data/Images/Edit.png";

    private String eventURL = "data/Images/Event.png";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();
            displayActiveEvents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(prefs);
    }
    private void displayActiveEvents() {
        try {

            ObservableList<Event> activeEvents = model.getActiveEvents();
            for (Event events: activeEvents)
            {
                String day1 = "";
                Label day = new Label();
                Label month = new Label();
                Label year = new Label();
                imageEvent = new ImageView();
                imageCxl = new ImageView();
                imageEdit = new ImageView();//TODO change to an image of a cancel sign and place the right place
                Label title = new Label();
                Label startTime = new Label();
                Label location = new Label();

                Pane outerPane = new Pane();
                ExpansionPanel expPanel = new ExpansionPanel();
                Pane innerPane = new Pane();

                day.setText(String.valueOf(events.getEventDate().getDayOfMonth()));
                if(events.getEventDate().getDayOfMonth() <= 9) {
                    day1 = "0" + day.getText();
                    day.setText(day1);
                }


                month.setText(String.valueOf(events.getEventDate().getMonth()).substring(0,3));
                year.setText(String.valueOf(events.getEventDate().getYear()));



                title.setText(events.getEventTitle());

                startTime.setText(events.getEventStartTime().toString().substring(0,5));
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

                DropShadow shadow = new DropShadow(0,4,4, Color.color(0,0,0,0.25));

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



                imageCxl.setOnMouseClicked(event -> cancelEvent(title.getText(),startTime.getText(),day.getText(),month.getText(),year.getText()));
                //imageEdit.setOnMouseClicked(event -> cancelEvent());
                imageCxl.setImage(loadImages(cxlURL));
                imageEdit.setImage(loadImages(editURL));
                imageEvent.setImage(loadImages(cxlURL));
                imageCxl.setScaleX(0.8);
                imageCxl.setScaleY(0.8);
                imageEdit.setScaleX(0.8);
                imageEdit.setScaleY(0.8);


                imageCxl.setX(1000);
                imageCxl.setY(10);
                imageEdit.setX(900);
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
            Alert alert = new Alert(Alert.AlertType.ERROR,"Could not load an image, following error occurred:\n"+ e, ButtonType.CANCEL);
            alert.showAndWait();
        }
        return image;

    }

    private void cancelEvent(String eventName,String startTime, String day, String month, String year){

        Alert alert = createAlertWithOptOut(Alert.AlertType.CONFIRMATION, "Cancel Event?", "Cancel Event?",
                "Are you sure you want to cancel:\n" +"'"+eventName+"'\n"+"'"+day+"-"+month+"-"+year+" "+startTime+"'", "Submit for deletion",
                param -> prefs = 1, ButtonType.YES, ButtonType.NO);
        if (alert.showAndWait().filter(t -> t == ButtonType.YES).isPresent()) {
            System.out.println("Works");;
            System.out.println(prefs); //TODO replace EVENTNAME, DATE & STARTTIME with actual data and make event inactive
        }
    }

    public static Alert createAlertWithOptOut(Alert.AlertType type, String title, String headerText,
                                              String message, String deletionMessage, Consumer<Boolean> deletionAction,
                                              ButtonType... buttonTypes) {
        Alert alert = new Alert(type);
        // Need to force the alert to layout in order to grab the graphic,
        // as we are replacing the dialog pane with a custom pane
        alert.getDialogPane().applyCss();
        Node graphic = alert.getDialogPane().getGraphic();
        // Create a new dialog pane that has a checkbox instead of the hide/show details button
        // Use the supplied callback for the action of the checkbox
        alert.setDialogPane(new DialogPane() {
            @Override
            protected Node createDetailsButton() {
                CheckBox delete = new CheckBox();
                delete.setText(deletionMessage);
                delete.setOnAction(e -> deletionAction.accept(delete.isSelected()));
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
  
    
}
