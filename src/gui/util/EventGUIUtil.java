package gui.util;

import be.Event;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.xml.stream.events.StartDocument;


public class EventGUIUtil {

    DropShadow shadow = new DropShadow(0, 4, 4, Color.color(0, 0, 0, 0.25));
    DropShadow shadow2 = new DropShadow(0, 2, 2, Color.color(0, 0, 0, 0.25));

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

    public void setEventMonthAndYearPlacement(Label month) {
        month.setEffect(shadow);
        month.setLayoutX(110);
        month.setLayoutY(70);
    }

    public void setEventTitlePlacement(Label... title) {
        for (Label l : title) {
            l.setEffect(shadow);
            l.setAlignment(Pos.CENTER);
            l.setMinHeight(60);
        }
    }

    public void setEventStartTimePlacement(Label... startTime) {
        for (Label l : startTime) {
            l.setEffect(shadow);
            l.setAlignment(Pos.CENTER);
            l.setMinHeight(60);
        }
    }

    public void setEventLocationPlacement(Label... location) {
        for (Label l : location) {
            l.setEffect(shadow);
            l.setAlignment(Pos.CENTER);
            l.setMinHeight(60);
        }
    }

    public void setEventDescriptionPlacement(Label eventDescription) {
        eventDescription.setEffect(shadow2);
        eventDescription.setWrapText(true);
        eventDescription.setMaxWidth(300);
        eventDescription.setPrefHeight(150);
    }

    public void setExpandedCustomerPaneChildren(BorderPane expandedPane, Label eventTitleExpanded, Label eventStartTimeExpanded, Label eventLocationExpanded, Label eventDescription, ImageView imageEventExpanded, ImageView imageBuyTicketExpanded, Label eventOwner, Label eventCollaborator, Label lblOwner, Label lblCollaborator) {
        BorderPane right = new BorderPane();
        imageBuyTicketExpanded.setFitWidth(192);
        imageBuyTicketExpanded.setFitHeight(75);
        right.setCenter(imageBuyTicketExpanded);
        expandedPane.setRight(right);
        expandedPane.setPrefWidth(1200);
        expandedPane.setLeft(imageEventExpanded);
        BorderPane left = new BorderPane();
        left.setCenter(imageEventExpanded);
        expandedPane.setLeft(left);
        BorderPane titlePane = new BorderPane();
        BorderPane startTimePane = new BorderPane();
        BorderPane locationPane = new BorderPane();
        BorderPane eventDescriptionPane = new BorderPane();
        titlePane.setCenter(eventTitleExpanded);
        startTimePane.setCenter(eventStartTimeExpanded);
        locationPane.setCenter(eventLocationExpanded);
        eventDescriptionPane.setCenter(eventDescription);
        Pane bufferPane = new Pane();
        bufferPane.setPrefHeight(30);
        VBox eventDescriptionPaneRight = new VBox(bufferPane, lblOwner,eventOwner,lblCollaborator,eventCollaborator);
        eventDescriptionPane.setRight(eventDescriptionPaneRight);
        eventDescriptionPane.setPadding(new Insets(10,0, 10,0));
        VBox center = new VBox(titlePane,startTimePane,locationPane,eventDescriptionPane);
        expandedPane.setCenter(center);


    }

    public void setExpandedAdminPaneChildren(BorderPane expandedPane, Label eventTitleExpanded, Label eventStartTimeExpanded, Label eventLocationExpanded, Label eventDescription, ImageView imageEventExpanded, ImageView imageDeleteExpanded, ImageView imageCxlExpanded, Label eventOwner, Label eventCollaborator, Label lblOwner, Label lblCollaborator) {
        BorderPane right = new BorderPane();
        right.setLeft(imageDeleteExpanded);
        right.setRight(imageCxlExpanded);
        expandedPane.setRight(right);
        expandedPane.setPrefWidth(1200);
        expandedPane.setLeft(imageEventExpanded);
        BorderPane left = new BorderPane();
        left.setCenter(imageEventExpanded);
        expandedPane.setLeft(left);
        BorderPane titlePane = new BorderPane();
        BorderPane startTimePane = new BorderPane();
        BorderPane locationPane = new BorderPane();
        BorderPane eventDescriptionPane = new BorderPane();
        titlePane.setCenter(eventTitleExpanded);
        startTimePane.setCenter(eventStartTimeExpanded);
        locationPane.setCenter(eventLocationExpanded);
        eventDescriptionPane.setCenter(eventDescription);
        Pane bufferPane = new Pane();
        bufferPane.setPrefHeight(30);
        VBox eventDescriptionPaneRight = new VBox(bufferPane, lblOwner,eventOwner,lblCollaborator,eventCollaborator);
        eventDescriptionPane.setRight(eventDescriptionPaneRight);
        eventDescriptionPane.setPadding(new Insets(10,0, 10,0));
        VBox center = new VBox(titlePane,startTimePane,locationPane,eventDescriptionPane);
        expandedPane.setCenter(center);


    }

    public void setExpandedEventCoPaneChildren(BorderPane expandedPane, Label eventTitleExpanded, Label eventStartTimeExpanded, Label eventLocationExpanded, Label eventDescription, ImageView imageEventExpanded, ImageView imageEditExpanded, ImageView imageCxlExpanded, Label eventOwner, Label eventCollaborator, Label lblOwner, Label lblCollaborator) {
        BorderPane right = new BorderPane();
        right.setLeft(imageEditExpanded);
        right.setRight(imageCxlExpanded);
        expandedPane.setRight(right);
        expandedPane.setPrefWidth(1200);
        expandedPane.setLeft(imageEventExpanded);
        BorderPane left = new BorderPane();
        left.setCenter(imageEventExpanded);
        expandedPane.setLeft(left);
        BorderPane titlePane = new BorderPane();
        BorderPane startTimePane = new BorderPane();
        BorderPane locationPane = new BorderPane();
        BorderPane eventDescriptionPane = new BorderPane();
        titlePane.setCenter(eventTitleExpanded);
        startTimePane.setCenter(eventStartTimeExpanded);
        locationPane.setCenter(eventLocationExpanded);
        eventDescriptionPane.setCenter(eventDescription);
        Pane bufferPane = new Pane();
        bufferPane.setPrefHeight(30);
        VBox eventDescriptionPaneRight = new VBox(bufferPane, lblOwner,eventOwner,lblCollaborator,eventCollaborator);
        eventDescriptionPane.setRight(eventDescriptionPaneRight);
        eventDescriptionPane.setPadding(new Insets(10,0, 10,0));
        VBox center = new VBox(titlePane,startTimePane,locationPane,eventDescriptionPane);
        expandedPane.setCenter(center);


    }


    public void setCollapsedCustomerPaneChildren(BorderPane collapsedPane, ImageView imageEvent, Label title, Label startTime, Label location, ImageView imageBuyTicket) {
        BorderPane right = new BorderPane();
        imageBuyTicket.setFitWidth(192);
        imageBuyTicket.setFitHeight(75);
        right.setCenter(imageBuyTicket);
        collapsedPane.setRight(right);
        collapsedPane.setPrefWidth(1200);
        BorderPane left = new BorderPane();
        left.setCenter(imageEvent);
        collapsedPane.setLeft(left);
        BorderPane titlePane = new BorderPane();
        BorderPane startTimePane = new BorderPane();
        BorderPane locationPane = new BorderPane();
        titlePane.setCenter(title);
        startTimePane.setCenter(startTime);
        locationPane.setCenter(location);
        VBox center = new VBox(titlePane,startTimePane,locationPane);
        collapsedPane.setCenter(center);
    }

    public void setCollapsedAdminPaneChildren(BorderPane collapsedPane, ImageView imageEvent, Label title, Label startTime, Label location, ImageView imageDelete, ImageView imageCxl) {
        BorderPane right = new BorderPane();
        right.setLeft(imageDelete);
        right.setRight(imageCxl);
        collapsedPane.setRight(right);
        collapsedPane.setPrefWidth(1200);
        BorderPane left = new BorderPane();
        left.setCenter(imageEvent);
        collapsedPane.setLeft(left);
        BorderPane titlePane = new BorderPane();
        BorderPane startTimePane = new BorderPane();
        BorderPane locationPane = new BorderPane();
        titlePane.setCenter(title);
        startTimePane.setCenter(startTime);
        locationPane.setCenter(location);
        VBox center = new VBox(titlePane,startTimePane,locationPane);
        collapsedPane.setCenter(center);
    }

    public void setCollapsedEventCoPaneChildren(BorderPane collapsedPane, ImageView imageEvent, Label title, Label startTime, Label location, ImageView imageEdit, ImageView imageCxl) {
        BorderPane right = new BorderPane();
        right.setRight(imageCxl);
        right.setLeft(imageEdit);
        collapsedPane.setRight(right);
        collapsedPane.setPrefWidth(1200);
        BorderPane left = new BorderPane();
        left.setCenter(imageEvent);
        collapsedPane.setLeft(left);
        BorderPane titlePane = new BorderPane();
        BorderPane startTimePane = new BorderPane();
        BorderPane locationPane = new BorderPane();
        titlePane.setCenter(title);
        startTimePane.setCenter(startTime);
        locationPane.setCenter(location);
        VBox center = new VBox(titlePane,startTimePane,locationPane);
        collapsedPane.setCenter(center);
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
        imageEventExpanded.maxHeight(280);
        imageEventExpanded.maxWidth(420);
    }

    public void setImageEditExpandedPlacement(ImageView imageEditExpanded) {
        imageEditExpanded.setScaleX(0.68);
        imageEditExpanded.setScaleY(0.68);
        imageEditExpanded.setX(1030);
        imageEditExpanded.setY(10);
        imageEditExpanded.setEffect(shadow);
    }


    public void setExpPanelPlacementAndChildren(ExpansionPanel expPanel, BorderPane collapsedPane, BorderPane expandedPane) {
        expPanel.setLayoutX(200);
        expPanel.setLayoutY(25);
        expPanel.setCollapsedContent(collapsedPane);
        expPanel.setExpandedContent(expandedPane);
    }

    public void setOuterPanePlacementAndChildren(FlowPane outerPane, Label eventDay, Label monthAndYear, ExpansionPanel expPanel) {
        outerPane.setPadding(new Insets(30,0,0,30));
        monthAndYear.setPadding(new Insets(0,15,0,0));
        outerPane.getChildren().add(eventDay);
        outerPane.getChildren().add(monthAndYear);
        outerPane.getChildren().add(expPanel);

    }

    public void setStyleSheetsAndClass(FlowPane outerPane, Label title, Label startTime, Label location, BorderPane collapsedPane, Label eventTitleExpanded, Label eventStartTimeExpanded, Label eventLocationExpanded, BorderPane expandedPane, ExpansionPanel expPanel, Label eventDay, Label monthAndYear, Label eventDescription, Label lblOwner, Label lblCollaborator) {
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
        monthAndYear.getStyleClass().add("lblMonthAndYear");
        eventDescription.getStyleClass().add("lblEventDescription");
    }

    public void setBuyTicketPlacement(ImageView buyTicket) {
    }

    public void setOwnerAndCollaboratorStyling(Label lblOwner, Label lblCollaborator, Label eventOwner, Label eventCollaborator) {
        lblOwner.getStyleClass().add("lblOwnerAndCollaborator");
        lblCollaborator.getStyleClass().add("lblOwnerAndCollaborator");
        lblCollaborator.setEffect(shadow2);
        lblOwner.setEffect(shadow2);
        eventOwner.setEffect(shadow2);
        eventCollaborator.setEffect(shadow2);
        eventOwner.getStyleClass().add("eventOwnerAndCollaborator");
        eventCollaborator.getStyleClass().add("eventOwnerAndCollaborator");
    }

    public void setImageDeletePlacement(ImageView imageDelete) {
        imageDelete.setScaleX(0.6);
        imageDelete.setScaleY(0.6);
        imageDelete.setX(1100);
        imageDelete.setY(10);
        imageDelete.setEffect(shadow);
    }

    public void setImageDeleteExpandedPlacement(ImageView imageDeleteExpanded) {
        imageDeleteExpanded.setScaleX(0.6);
        imageDeleteExpanded.setScaleY(0.6);
        imageDeleteExpanded.setX(1100);
        imageDeleteExpanded.setY(10);
        imageDeleteExpanded.setEffect(shadow);
    }
}
