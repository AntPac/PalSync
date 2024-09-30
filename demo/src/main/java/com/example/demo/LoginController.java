package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private ImageView img;
    @FXML
    private Text pinText;
    @FXML
    private TextField pinTF;
    @FXML
    private PasswordField pinPF;
    @FXML
    private CheckBox check;
    @FXML
    private Button subButton;
    @FXML
    private Button forButton;
    @FXML
    private Button backButton;
    @FXML
    public void showPin(MouseEvent e) {
    if(check.isSelected()){
        pinTF.setVisible(true);
        pinTF.setText(pinPF.getText());
        pinPF.setVisible(false);
    } else {
        pinPF.setVisible(true);
        pinPF.setText(pinTF.getText());
        pinTF.setVisible(false);
    }
    }
    @FXML
    public void submit(MouseEvent e) throws IOException {
        int pin=0;
        boolean booPin = false;
        if(pinTF.isVisible() ){
            pin = Integer.parseInt(pinTF.getText());
            booPin = true;
        }
        if(pinPF.isVisible()){
            pin = Integer.parseInt(pinPF.getText());
            booPin = true;
        }
        if(booPin && pin >= 1000 && pin <= 9999){

                Parent root = FXMLLoader.load(getClass().getResource("/FXML Files/PlaceholderSuccessSB.fxml"));
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        } else {
            pinText.setText("Please make sure your pin is four digits");
        }


    }
    @FXML
    public void forgotPin(MouseEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/FXML Files/ForgotPinSB.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void back(MouseEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/FXML Files/welcomePagefxml.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
