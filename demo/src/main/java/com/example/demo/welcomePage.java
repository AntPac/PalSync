package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class welcomePage extends Application {

  // static Parent root;
    public void start(Stage Stage) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/FXML Files/welcomePagefxml.fxml")); // Replace with your actual FXML path
        Scene scene = new Scene(root, 600, 400);

        Stage.setTitle("PalSync");
        Stage.setScene(scene);
        //Stage.setFullScreen(true);
        Stage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}
