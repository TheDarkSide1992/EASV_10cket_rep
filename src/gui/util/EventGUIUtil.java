package gui.util;

import be.Event;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EventGUIUtil {

    DropShadow shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));

    public static EventGUIUtil EGUInstance = null;

    private EventGUIUtil() {
    }

    public static synchronized EventGUIUtil getInstance() {
        if (EGUInstance == null) EGUInstance = new EventGUIUtil();
        return EGUInstance;
    }

    public void setEventDayPlacement(Label eventDay) {
        eventDay.setEffect(shadow);
        eventDay.setLayoutX(30);
        eventDay.setLayoutY(50);
        eventDay.minHeight(100);
        eventDay.minWidth(100);
        eventDay.setAlignment(Pos.CENTER);
    }

    public void setEventMonthPlacement(Label month) {
        month.setEffect(shadow);
        month.setLayoutX(110);
        month.setLayoutY(70);
    }

    public void setEventYearPlacement(Label year) {
        year.setEffect(shadow);
        year.setLayoutX(110);
        year.setLayoutY(95);
    }

    public void setEventTitlePlacement(Label... title) {
        for (Label l : title) {
            l.setEffect(shadow);
            l.setAlignment(Pos.CENTER);
            l.setMinWidth(1200);
            l.setMinHeight(60);
        }
    }

    public void setEventStartTimePlacement(Label... startTime) {
        for (Label l : startTime) {
            l.setEffect(shadow);
            l.setAlignment(Pos.CENTER);
            l.setLayoutY(45);
            l.setMinWidth(1200);
            l.setMinHeight(60);
        }
    }

    public void setEventLocationPlacement(Label... location) {
        for (Label l : location) {
            l.setEffect(shadow);
            l.setLayoutY(90);
            l.setAlignment(Pos.CENTER);
            l.setMinWidth(1200);
            l.setMinHeight(60);
        }
    }

    public void setEventDescriptionPlacement(Label eventDescription) {
        eventDescription.setLayoutY(200);
        eventDescription.setLayoutX(20);
        eventDescription.setWrapText(true);
        eventDescription.setMaxWidth(500);
        eventDescription.setMaxHeight(300);
    }

    public void setExpandedPaneChildren(Pane expandedPane, Node... nodes) {
        expandedPane.setMinHeight(400);
        for (Node n : nodes) {
            expandedPane.getChildren().add(n);
        }
    }

    public void setCollapsedPaneChildren(Pane collapsedPane, Node... nodes) {
        collapsedPane.prefHeight(200);
        collapsedPane.prefWidth(1200);
        for (Node n : nodes) {
            collapsedPane.getChildren().add(n);
        }
    }

    public void setImageCxlPlacement(ImageView imageCxl) {
        imageCxl.setScaleX(0.8);
        imageCxl.setScaleY(0.8);
        imageCxl.setX(1100);
        imageCxl.setY(10);
        imageCxl.setEffect(shadow);
    }

    public void setImageEditPlacement(ImageView imageEdit) {
        imageEdit.setScaleX(0.68);
        imageEdit.setScaleY(0.68);
        imageEdit.setX(1030);
        imageEdit.setY(10);
        imageEdit.setEffect(shadow);

    }

    public void setImageEventPlacement(ImageView imageEvent) {
        imageEvent.setX(5);
        imageEvent.setY(10);
        imageEvent.maxHeight(140);
        imageEvent.maxWidth(210);
    }

    public void setImageCxlExpandedPlacement(ImageView imageCxlExpanded) {
        imageCxlExpanded.setScaleX(0.8);
        imageCxlExpanded.setScaleY(0.8);
        imageCxlExpanded.setX(1100);
        imageCxlExpanded.setY(10);
        imageCxlExpanded.setEffect(shadow);
    }

    public void setImageEventExpandedPlacement(ImageView imageEventExpanded) {
        imageEventExpanded.setX(5);
        imageEventExpanded.setY(10);
        imageEventExpanded.maxHeight(140);
        imageEventExpanded.maxWidth(210);
    }

    public void setImageEditExpandedPlacement(ImageView imageEditExpanded) {
        imageEditExpanded.setScaleX(0.68);
        imageEditExpanded.setScaleY(0.68);
        imageEditExpanded.setX(1030);
        imageEditExpanded.setY(10);
        imageEditExpanded.setEffect(shadow);
    }

    public void setTextInLabels(Event events, Label month, Label year, Label title, Label eventTitleExpanded, Label startTime, Label location, Label eventStartTimeExpanded, Label eventLocationExpanded, Label eventDescription, Label eventDay) {
        String day1;
        eventDay.setText(String.valueOf(events.getEventDate().getDayOfMonth()));
        if (events.getEventDate().getDayOfMonth() <= 9) {
            day1 = "0" + eventDay.getText();
            eventDay.setText(day1);
        }
        month.setText(String.valueOf(events.getEventDate().getMonth()).substring(0, 3));
        year.setText(String.valueOf(events.getEventDate().getYear()));
        title.setText(events.getEventTitle());
        eventTitleExpanded.setText(events.getEventTitle());
        startTime.setText(events.getEventStartTime().toString().substring(0, 5));
        location.setText(events.getEventLocation());
        eventStartTimeExpanded.setText(events.getEventStartTime().toString().substring(0, 5));
        eventLocationExpanded.setText(events.getEventLocation());
        eventDescription.setText(events.getEventDescription());
    }

    public void setExpPanelPlacementAndChildren(ExpansionPanel expPanel, Pane collapsedPane, Pane expandedPane) {
        expPanel.setLayoutX(200);
        expPanel.setLayoutY(25);
        expPanel.setCollapsedContent(collapsedPane);
        expPanel.setExpandedContent(expandedPane);
    }

    public void setOuterPanePlacementAndChildren(Pane outerPane, Label eventDay, Label month, Label year, ExpansionPanel expPanel) {
        outerPane.setMinHeight(200);
        outerPane.getChildren().add(eventDay);
        outerPane.getChildren().add(month);
        outerPane.getChildren().add(year);
        outerPane.getChildren().add(expPanel);
    }

    public void setStyleSheetsAndClass(Pane outerPane, Label title, Label startTime, Label location, Pane collapsedPane, Label eventTitleExpanded, Label eventStartTimeExpanded, Label eventLocationExpanded, Pane expandedPane, ExpansionPanel expPanel, Label eventDay, Label month, Label year, Label eventDescription) {
        outerPane.getStylesheets().add(getClass().getResource("/gui/view/Main.css").toExternalForm());
        outerPane.getStyleClass().add("paneOuterPane");
        title.getStyleClass().add("lblEventTitle");
        startTime.getStyleClass().add("lblStartTime");
        location.getStyleClass().add("lblLocation");
        collapsedPane.getStyleClass().add("paneCollapsedPane");
        eventTitleExpanded.getStyleClass().add("lblEventTitle");
        eventStartTimeExpanded.getStyleClass().add("lblStartTime");
        eventLocationExpanded.getStyleClass().add("lblLocation");
        expandedPane.getStyleClass().add("paneExpandedPane");
        expPanel.getStyleClass().add("expansionPanel");
        eventDay.getStyleClass().add("lblEventDay");
        month.getStyleClass().add("lblMonth");
        year.getStyleClass().add("lblYear");
        eventDescription.getStyleClass().add("lblEventDescription");
    }
}
