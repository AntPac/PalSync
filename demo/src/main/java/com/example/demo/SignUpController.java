package com.example.demo;
/*
a) SignUpController
b) Creation Date: September 30 2024
c) Programmer’s Name: Sky Dalangin
d) Purpose: Handles user registration process.
e) Role: Ensures the user input is valid before storing account information into the SQL database. Two methods are used: initialize() prompts the user to input information to be sent to SQL and is also responsible for denying a user registration if information is not complete. handle() simply changes the scene.
f) Data Structures: None
g) Algorithms: None

 */
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_signup;

    @FXML
    private Button button_log_in;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){

                    DBUtils.signUpUser(event, tf_username.getText() , tf_password.getText());

                } else {
                    System.out.println("Please fill out all information!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information!");
                    alert.show();
                }

            }
        });


        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DBUtils.changeScene(event, "sample.fxml" , "Log in!" , null );
            }
        });


    }
}
