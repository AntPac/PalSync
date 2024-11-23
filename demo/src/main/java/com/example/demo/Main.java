package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
a) Main
b) Creation Date: September 30, 2024
Modification Dates:
October 1, 2024
October 21, 2024
c) Programmer: Sky Dalangin
d) Purpose: Is the entry point of this program.
e) Role: Initializes the program and the main application
f) Data Structures: None
g) Algorithms: None

*/

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("PalSync");
        primaryStage.setScene(new Scene(root , 600,400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }
}



