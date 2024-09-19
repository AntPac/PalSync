package com.example.demo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class  Login extends  Application {
    VBox vbox = new VBox();
    Image icon = new Image(getClass().getResource("/images/hug_transparent.png").toExternalForm());
    account[] Storage = new account[10];
    int numofacc = 0;
    public void start(Stage Stage) {
        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #FEF3E0;");

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
        PasswordField Pin = new PasswordField();
        Pin.setPromptText("Ex. 1234");
        Pin.setPrefSize(300, 50);
        Pin.setMaxSize(500, 200);
        Pin.setMinSize(100, 50);
        TextField unPin = new TextField();
        unPin.setPrefSize(300, 50);
        unPin.setMaxSize(500, 200);
        unPin.setMinSize(100, 50);

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
        CheckBox showPin = new CheckBox("Show PIN");




        unPin.textProperty().bindBidirectional(Pin.textProperty());

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Button forgotPin = new Button("ForgotPin");

        Submit.setOnAction(event -> {
            int pin = Integer.parseInt(Pin.getText());
            LoginMes(pin);
        });

        Back.setOnAction(event -> Home());



        forgotPin.setOnAction(event -> {
            forgotPin(box);
        });
        showPin.setOnAction(event -> {
            box.getChildren().clear();


            if (showPin.isSelected()) {
                unPin.setText(Pin.getText());
                box.getChildren().addAll(Pint,unPin, showPin, Submit, forgotPin, Back);
            } else {
                Pin.setText(unPin.getText());
                box.getChildren().addAll(Pint,Pin, showPin, Submit, forgotPin, Back);
            }
        });


        box.getChildren().addAll(Pint,Pin, showPin, Submit, forgotPin, Back);




    }
    public void forgotPin(VBox box) {
        box.getChildren().clear();


        Label nameLabel = new Label("Please Enter Your Name");
        nameLabel.setStyle("-fx-font-size: 20pt;");


        TextField nameField = new TextField();
        nameField.setPromptText("Ex. John Doe");
        nameField.setPrefSize(300, 50);
        nameField.setMaxSize(500,200);
        nameField.setMinSize(100,50);


        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");


        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            if (checkAccountExists(name)) {
                resetPin(vbox, name);
            } else {
                Label error = new Label("No account found with that name.");
                vbox.getChildren().add(error);
            }});


        backButton.setOnAction(event -> login(vbox));


        box.getChildren().addAll(nameLabel, nameField, submitButton, backButton);
    }


    public boolean checkAccountExists(String name) {
        for (account acc : Storage) {
            if (acc != null && acc.name.equals(name)) {
                return true;
            }
        }
        return false;
    }


    public void resetPin(VBox box, String name) {
        box.getChildren().clear();


        Label resetLabel = new Label("Enter a new 4-digit Pin");
        resetLabel.setStyle("-fx-font-size: 20pt;");


        PasswordField newPinField = new PasswordField();
        newPinField.setPromptText("Ex. 1234");
        newPinField.setPrefSize(300, 50);
        newPinField.setMaxSize(500,200);
        newPinField.setMinSize(100,50);


        TextField unPin = new TextField();
        unPin.setPrefSize(300, 50);
        unPin.setMaxSize(500,200);
        unPin.setMinSize(100,50);


        CheckBox showPin = new CheckBox("Show PIN");
        unPin.textProperty().bindBidirectional(newPinField.textProperty());




        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");


        submitButton.setOnAction(event -> {
            int newPin = Integer.parseInt(newPinField.getText());
            updatePin(name, newPin);
            Home();
        });


        backButton.setOnAction(event -> login(vbox));
        showPin.setOnAction(event -> {
            box.getChildren().clear();


            if (showPin.isSelected()) {
                unPin.setText(newPinField.getText());
                box.getChildren().addAll(resetLabel,unPin, showPin, submitButton, backButton);
            } else {
                newPinField.setText(unPin.getText());
                box.getChildren().addAll(resetLabel,newPinField, showPin, submitButton,backButton);
            }
        });
        box.getChildren().addAll(resetLabel, newPinField, showPin, submitButton, backButton);
    }


    public void updatePin(String name, int newPin) {
        for (account acc : Storage) {
            if (acc != null && acc.name.equals(name)) {
                acc.accnum = newPin;
                break;
            }
        }
    }

    public void NewAcc(VBox box) {

        box.getChildren().clear();

        Label namet = new Label("Please Enter A Name");
        TextField name = new TextField();
        name.setPromptText("Ex. John Doe"); // Placeholder for name
        name.setStyle("-fx-prompt-text-fill: black;");
        name.setPrefSize(300, 50);
        name.setMaxSize(500,200);
        name.setMinSize(100,50);

        Label Pint = new Label("Please Enter A 4 Digit Pin");
        PasswordField Pin = new PasswordField();
        Pin.setPromptText("Ex. 1234");
        Pin.setPrefSize(300, 50);
        Pin.setMaxSize(500,200);
        Pin.setMinSize(100,50);






        TextField unPin = new TextField();
        unPin.setPrefSize(300, 50);
        unPin.setMaxSize(500,200);
        unPin.setMinSize(100,50);


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
        CheckBox showPin = new CheckBox("Show PIN");




        unPin.textProperty().bindBidirectional(Pin.textProperty());

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");

        Submit.setOnAction(event -> {
            int pin = Integer.parseInt(Pin.getText());
            NewAccMes(pin, name.getText());
        });
        Back.setOnAction(event -> Home());



        showPin.setOnAction(event -> {
            box.getChildren().clear();


            if (showPin.isSelected()) {
                unPin.setText(Pin.getText());
                box.getChildren().addAll(namet,name, Pint,unPin, showPin, Submit, Back);
            } else {
                Pin.setText(unPin.getText());
                box.getChildren().addAll(namet,name,Pint,Pin, showPin, Submit, Back);
            }
        });
        box.getChildren().addAll(namet, name, Pint, Pin, showPin, Submit, Back);


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

