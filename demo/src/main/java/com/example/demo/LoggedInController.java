package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;


/*
    IMPORTANT NOTE: READ ME

    This controller is for the page that says "You have logged in".
    However, we would prefer it that after successful login, we prompt
    the user to the homePage itself. Implementation on connecting these
    two will be completed as soon as the loginPage implementation is complete.
    That will most likely be a separate branch from this one. Or not.

*/



public class LoggedInController implements Initializable {


    @FXML
    private Button button_logout;
    @FXML
    private Label label_welcome;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "sample.fxml", "Log in", null);
            }
        });
    }

    public void setUserInformation(String username){
        label_welcome.setText("Welcome " + username + "!");
    }



}


