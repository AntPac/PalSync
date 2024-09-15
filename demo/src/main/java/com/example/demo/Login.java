package com.example.demo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class  Login extends  Application {
    VBox vbox = new VBox();
    Image icon = new Image(getClass().getResource("/images/hug.png").toExternalForm());
    account[] Storage = new account[10];
    int numofacc = 0;
    public void start(Stage Stage) {
        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: khaki; ");

        Stage.getIcons().add(icon);


        Home();

        Scene scene = new Scene(vbox, 800, 600);
        Stage.setScene(scene);
        Stage.setFullScreen(true);
        Stage.show();

    }
    public void Home() {
        vbox.getChildren().clear();

        Label WelcomeText = new Label("Welcome to PalSync");
        WelcomeText.setStyle("-fx-font-size: 20pt; ");
        ImageView Image = new ImageView(icon);
        Button LB = new Button("Login");
        Button SB = new Button("Are You New Here?");

        LB.setOnAction(event -> login(vbox));
        SB.setOnAction(event -> NewAcc(vbox));

        vbox.getChildren().addAll(Image, WelcomeText,LB,SB);
    }

    public void login( VBox box){

        box.getChildren().clear();

        Label Pint = new Label("Please Enter Your Personal Pin");
        TextField Pin = new TextField();
        Pin.setPromptText("Ex. 1234"); // Placeholder for pin

        Pin.setStyle("-fx-prompt-text-fill: black;"); // Ensure prompt text is visible on initial launch.

        // Listener to remove prompt when the user starts typing, not just when cleaning
        Pin.textProperty().addListener((observable, oldValue, newValue) -> {
            // If new text is empty, set prompt text color back to visible
            if (newValue.isEmpty()) {
                Pin.setStyle("-fx-prompt-text-fill: black;"); // Prompt visible
            } else {
                Pin.setStyle("-fx-prompt-text-fill: transparent;"); // Hide prompt text
            }
        });

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");

        Submit.setOnAction(event -> {
            int pin = Integer.parseInt(Pin.getText());
            LoginMes(pin);
        });

        Back.setOnAction(event -> Home());

        box.getChildren().addAll(Pint,Pin,Submit,Back);



    }
    public void NewAcc(VBox box) {

        box.getChildren().clear();

        Label namet = new Label("Please Enter A Name");
        TextField name = new TextField();
        name.setPromptText("Ex. John Doe"); // Placeholder for name
        name.setStyle("-fx-prompt-text-fill: black;");

        Label Pint = new Label("Please Enter A 4 Digit Pin");
        TextField Pin = new TextField();
        Pin.setPromptText("Ex. 1234"); // Placeholder for pin

        Pin.setStyle("-fx-prompt-text-fill: black;"); // Ensure prompt text is visible on initial launch

        // Listener to remove prompt when the user starts typing not just when clicking
        Pin.textProperty().addListener((observable, oldValue, newValue) -> {
            // If new text is empty, set prompt text color back to visible
            if (newValue.isEmpty()) {
                Pin.setStyle("-fx-prompt-text-fill: black;"); // Prompt visible
            } else {
                Pin.setStyle("-fx-prompt-text-fill: transparent;"); // Hide prompt text
            }
        });

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");

        Submit.setOnAction(event -> {
            int pin = Integer.parseInt(Pin.getText());
            NewAccMes(pin, name.getText());
        });
        Back.setOnAction(event -> Home());

        box.getChildren().addAll(namet,name,Pint,Pin,Submit,Back);

    }

    public void NewAccMes(int pin, String name){
        account a = new account(pin,name);
        Storage[numofacc++]= a;

        vbox.getChildren().clear();

        Label Message = new Label("Welcome To PalSync " + name);
        Button Back = new Button("Return to Home Page");

        Back.setOnAction(event -> Home());
        vbox.getChildren().addAll(Message,Back);
    }

    public void LoginMes(int pin){
        vbox.getChildren().clear();
        boolean found = false;
        String name = "";

        for (int i=0;i< Storage.length;i++){
            if(Storage[i] != null && Storage[i].accnum==pin){
                found = true;
                name = Storage[i].name;
                break;
            }
        }



        Button Back = new Button("Return to Home Page");
        Back.setOnAction(event -> Home());

        if (found){
            Label Acc = new Label("Welcome Back " + name);
            vbox.getChildren().addAll(Acc,Back);
        } else {
            Label noAcc = new Label("Account Doesn't Exist");
            vbox.getChildren().addAll(noAcc,Back);
        }
    }


    public static void main(String[] args) {
        launch();
    }
    static class account{
        int accnum;
        String name;
        public account(int accnum, String name) {
            this.accnum = accnum;
            this.name = name;
        }
    }
}

