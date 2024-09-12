package com.example.demo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class  Login extends  Application {
public void start(Stage Stage){
    Label WelcomeText = new Label("Welcome to PalSync");
    WelcomeText.setStyle("-fx-font-size: 20pt; ");
    AnchorPane.setTopAnchor(WelcomeText, 50.0);

    Button LB = new Button("Login");
    Button SB = new Button("Sign Up");


    VBox vbox = new VBox(20);
    vbox.setAlignment(Pos.CENTER);

    Image icon = new Image (getClass().getResource("/images/hug.png").toExternalForm());
    Stage.getIcons().add(icon);


    vbox.getChildren().addAll(WelcomeText,LB,SB);


    Scene scene = new Scene(vbox, 800, 600);

    LB.setOnAction(event -> SignUp(vbox));
    SB.setOnAction(event -> SignUp(vbox));


    Stage.setScene(scene);
    Stage.setFullScreen(true);
    Stage.show();
}


    public void SignUp( VBox box){
    box.getChildren().clear();
        Label Sname = new Label("Enter a username");
        TextField Username = new TextField();
        Label Spass = new Label("Enter a password");
        TextField Password = new TextField("Enter Password");
        Button Submit = new Button("submit");
        box.getChildren().addAll(Sname,Username,Spass,Password,Submit);


    }

    public static void main(String[] args) {
        launch();
    }

}
