package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class Login extends Application {
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
        vbox.getChildren().addAll(Image, WelcomeText, LB, SB);
    }
  /*  public void switchPin(VBox box, boolean showUn, PasswordField Pin, TextField unPin, CheckBox showPin, Button Submit, Button Back) {
    // Clear the current children to avoid duplicate components
    box.getChildren().clear();

    // Add the common components (label and buttons)
    Label Pint = new Label("Please Enter Your Personal Pin");
    Pint.setStyle("-fx-font-size:20pt;");

    // Check whether to show the unmasked TextField or the masked PasswordField
    if (showUn) {
        // Show the unmasked PIN (TextField)
        box.getChildren().addAll(Pint, unPin, showPin, Submit, Back);
    } else {
        // Show the masked PIN (PasswordField)
        box.getChildren().addAll(Pint, Pin, showPin, Submit, Back);
    }
}

// Updated login method with the switch between masked and unmasked PIN
public void login(VBox box) {
    box.getChildren().clear();

    // PasswordField for masked input
    PasswordField Pin = new PasswordField();
    Pin.setPromptText("Ex. 1234");
    Pin.setPrefSize(300, 50);
    Pin.setMaxSize(500,200);
    Pin.setMinSize(100,50);


    // TextField for unmasked input
    TextField unPin = new TextField();
    unPin.setPrefSize(300, 50);
    unPin.setMaxSize(500,200);
    unPin.setMinSize(100,50);


    // Initially, show the masked input (PasswordField)
    unPin.setVisible(false);

    // Sync the text between PasswordField and TextField
    unPin.textProperty().bindBidirectional(Pin.textProperty());

    // Toggle visibility CheckBox
    CheckBox showPin = new CheckBox("Show PIN");


    Button Submit = new Button("Submit");
    Button Back = new Button("Back");

    // switchPinView
    switchPin(box, false, Pin, unPin, showPin, Submit, Back);

    // Toggle between PasswordField and TextField when the checkbox is selected
    showPin.setOnAction(event -> {
        if (showPin.isSelected()) {
            // switchPinView
            switchPin(box, true, Pin, unPin, showPin, Submit, Back);
        } else {
            // switchPinView
            switchPin(box, false, Pin, unPin, showPin, Submit, Back);
        }
    });

    // Event handler for Submit button
    Submit.setOnAction(event -> {
        int pin = Integer.parseInt(Pin.getText());
        LoginMes(pin);
    });

    // Event handler for Back button
    Back.setOnAction(event -> Home());
}
*/
    public void login(VBox box) {
        box.getChildren().clear();

        Label Pint = new Label("Please Enter Your Personal Pin");
        Pint.setStyle("-fx-font-size:20pt;");


        PasswordField Pin = new PasswordField();
        Pin.setPromptText("Ex. 1234");
        Pin.setPrefSize(300, 50);
        Pin.setMaxSize(500,200);
        Pin.setMinSize(100,50);


        TextField unPin = new TextField();
        unPin.setPrefSize(300, 50);
        unPin.setMaxSize(500,200);
        unPin.setMinSize(100,50);

        unPin.setVisible(false); // Initially invisible


        CheckBox showPin = new CheckBox("Show PIN");


        unPin.textProperty().bindBidirectional(Pin.textProperty());
        showPin.setOnAction(event -> {
            if (showPin.isSelected()) {
                unPin.setText(Pin.getText());
                Pin.setVisible(false);
                unPin.setVisible(true);
            } else {
                Pin.setText(unPin.getText());
                Pin.setVisible(true);
                unPin.setVisible(false);
            }
        });

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Button forgotPin = new Button ("ForgotPin");

        Submit.setOnAction(event -> {
            int pin = Integer.parseInt(Pin.getText());
            LoginMes(pin);
        });

        Back.setOnAction(event -> Home());
        forgotPin.setOnAction(event -> {
            forgotPin(box);
        });


        box.getChildren().addAll(Pint,Pin, unPin, showPin, Submit,forgotPin, Back);
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
        unPin.setVisible(false);

        CheckBox showPin = new CheckBox("Show PIN");
        unPin.textProperty().bindBidirectional(newPinField.textProperty());
        showPin.setOnAction(event -> {
            if (showPin.isSelected()) {
                unPin.setVisible(true);
                newPinField.setVisible(false);
            } else {
                unPin.setVisible(false);
                newPinField.setVisible(true);
            }
        });

        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        submitButton.setOnAction(event -> {
            int newPin = Integer.parseInt(newPinField.getText());
            updatePin(name, newPin);
            Home();
        });

        backButton.setOnAction(event -> login(vbox));

        box.getChildren().addAll(resetLabel, newPinField, unPin, showPin, submitButton, backButton);
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
        namet.setStyle("-fx-font-size:20pt;");

        TextField name = new TextField();
        name.setPromptText("Ex. John Doe");
        name.setPrefSize(300, 50);
        name.setMaxSize(500,200);
        name.setMinSize(100,50);

        Label Pint = new Label("Please Enter A 4 Digit Pin");
        Pint.setStyle("-fx-font-size:20pt;");


        PasswordField Pin = new PasswordField();
        Pin.setPromptText("Ex. 1234");
        Pin.setPrefSize(300, 50);
        Pin.setMaxSize(500,200);
        Pin.setMinSize(100,50);



        TextField unPin = new TextField();
        unPin.setPrefSize(300, 50);
        unPin.setMaxSize(500,200);
        unPin.setMinSize(100,50);

        unPin.setVisible(false); // Initially invisible


        CheckBox showPin = new CheckBox("Show PIN");


        unPin.textProperty().bindBidirectional(Pin.textProperty());


        showPin.setOnAction(event -> {
            if (showPin.isSelected()) {
                Pin.setVisible(false);
                unPin.setVisible(true);
            } else {
                Pin.setVisible(true);
                unPin.setVisible(false);
            }
        });

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");

        Submit.setOnAction(event -> {
            int pin = Integer.parseInt(Pin.getText());
            NewAccMes(pin, name.getText());
        });

        Back.setOnAction(event -> Home());

        box.getChildren().addAll(namet, name, Pint, Pin, unPin, showPin, Submit, Back);
    }

    public void NewAccMes(int pin, String name) {
        account a = new account(pin, name);
        Storage[numofacc++] = a;

        vbox.getChildren().clear();

        Label Message = new Label("Welcome To PalSync " + name);
        Button Back = new Button("Return to Home Page");

        Back.setOnAction(event -> Home());
        vbox.getChildren().addAll(Message, Back);
    }

    public void LoginMes(int pin) {
        vbox.getChildren().clear();
        boolean found = false;
        String name = "";

        for (int i = 0; i < Storage.length; i++) {
            if (Storage[i] != null && Storage[i].accnum == pin) {
                found = true;
                name = Storage[i].name;
                break;
            }
        }

        Button Back = new Button("Return to Home Page");
        Back.setOnAction(event -> Home());

        if (found) {
            Label Acc = new Label("Welcome Back " + name);
            vbox.getChildren().addAll(Acc, Back);
        } else {
            Label noAcc = new Label("Account Doesn't Exist");
            vbox.getChildren().addAll(noAcc, Back);
        }
    }

    public static void main(String[] args) {
        launch();
    }

    static class account {
        int accnum;
        String name;

        public account(int accnum, String name) {
            this.accnum = accnum;
            this.name = name;
        }
    }
}
