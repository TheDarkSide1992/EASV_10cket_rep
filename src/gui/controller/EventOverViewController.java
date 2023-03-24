package gui.controller;

import be.Event;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
import gui.model.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class EventOverViewController implements Initializable {
    @FXML
    private VBox vBoxCustomerView;
    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new Model();
            displayActiveEvents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayActiveEvents() {
        try {

            ObservableList<Event> activeEvents = model.getActiveEvents();
            for (Event event: activeEvents)
            {
                String day1 = "";
                Label day = new Label();
                Label month = new Label();
                Label year = new Label();

                Label title = new Label();
                Label startTime = new Label();
                Label location = new Label();

                Pane outerPane = new Pane();
                ExpansionPanel expPanel = new ExpansionPanel();
                Pane innerPane = new Pane();

                day.setText(String.valueOf(event.getEventDate().getDayOfMonth()));
                if(event.getEventDate().getDayOfMonth() <= 9) {
                    day1 = "0" + day.getText();
                    day.setText(day1);
                }


                month.setText(String.valueOf(event.getEventDate().getMonth()).substring(0,3));
                year.setText(String.valueOf(event.getEventDate().getYear()));



                title.setText(event.getEventTitle());

                startTime.setText(event.getEventStartTime().toString().substring(0,5));
                location.setText(event.getEventLocation());


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

                innerPane.getChildren().add(title);
                innerPane.getChildren().add(startTime);
                innerPane.getChildren().add(location);


                expPanel.setCollapsedContent(innerPane);
                outerPane.getChildren().add(expPanel);

                vBoxCustomerView.getChildren().add(outerPane);

            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
            alert.showAndWait();
        }
    }
}
