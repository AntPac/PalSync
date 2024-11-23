package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

// This class provides functionality for the two buttons
// on the login.fxml
/*
a) Controller
b) Creation Date: September 30, 2024
Modification Dates:
October 1, 2024
October 21, 2024
c) Programmer: Sky Dalangin
d) Purpose: This file is the controller for the login fxml
e) Role: Initializes the program and loads the login UI. Handles the actions to switch the tab to sign up and submit action for login
f) Data Structures: None
g) Algorithms: None
 */

public class Controller implements Initializable {

    @FXML
    private Button button_login;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @FXML
    private Button button_sign_up;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event, tf_username.getText(), tf_password.getText());
            }
        });


        button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sign-up.fxml", "Sign up!", null);

            }
        });




    }
}
