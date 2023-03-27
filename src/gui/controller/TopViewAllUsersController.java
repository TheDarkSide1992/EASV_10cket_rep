package gui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TopViewAllUsersController implements Initializable {
    @FXML private HBox btnHolderHBox;
    @FXML private Label lblSignIn;
    @FXML
    private ImageView imgLogo;

    private String url = "data/Images/10cketshort.png";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String user = "Event Coordinator";
        ArrayList<String> btnsToCreate = setAllButtons(user);
        addButtons(btnsToCreate);
        //setLogo();
        setStyles();
        signInLabelStyling();

    }

    private void addButtons(ArrayList<String> btnsToCreate) {
        for (String name: btnsToCreate) {
            Button btn = new Button(name);
            //Style the btns
            btn.getStyleClass().add("btnTopButtons");
            //btn.setMinSize(160,50);
            Font font = Font.font("Courier New", FontWeight.BOLD, 14);
            btn.setFont(font);
            btnHolderHBox.getChildren().add(btn);

            //Spacing between each element
            btnHolderHBox.setSpacing(10);
            //Position in BOX
            btnHolderHBox.setAlignment(Pos.BOTTOM_CENTER);

            //ObservableList<Node> hBox = btnHolderHBox.getChildren();
            //hBox.add(btn);
            //btnHolderHBox.getChildren().add(3,btn);
        }
    }

    private ArrayList<String> setAllButtons(String user) {
        ArrayList<String> btns = new ArrayList<>();
        if (user == "Event Coordinator") {
            btns.add("Upcoming Events");
            btns.add("All Events");
            btns.add("Calender");
            btns.add("Contact");
            btns.add("Prices");
            btns.add("Create Event");
            btns.add("Manage Tickets");
        }
        else if (user == "Administrator"){
            btns.add("Upcoming Events");
            btns.add("All Events");
            btns.add("Calender");
            btns.add("Contact");
            btns.add("Prices");
            btns.add("Create Event Coordinator");
        }
        else {
            btns.add("Upcoming Events");
            btns.add("All Events");
            btns.add("Calender");
            btns.add("Contact");
            btns.add("Prices");
        }
        return btns;
    }

    private void signInLabelStyling() {
        DropShadow shadow = new DropShadow(0,4,4, Color.color(0,0,0,0.25));
        lblSignIn.setEffect(shadow);
        lblSignIn.getStyleClass().add("lblSignIn");
        //btnHolderHBox.getChildren().add(lblSignIn);
    }

    private void setStyles() {
        DropShadow shadow = new DropShadow(0,4,4, Color.color(0,0,0,0.25));
        lblSignIn.setEffect(shadow);
    }

    private void setLogo() {
        Image logo = new Image(url);
        imgLogo.setImage(logo);

    }

    public void handleNewEvent(ActionEvent actionEvent) {
        ControllerAssistant controllerAssistant = ControllerAssistant.getInstance();
        controllerAssistant.openNewWindow("CreateEventView.fxml");
    }

    public void handleUpcomingEvents(ActionEvent actionEvent) {
        ControllerAssistant controllerAssistant = ControllerAssistant.getInstance();
        controllerAssistant.openNewWindow("EventOverView.fxml");
    }

    public void handleSignIn(MouseEvent mouseEvent) {
    }

}
