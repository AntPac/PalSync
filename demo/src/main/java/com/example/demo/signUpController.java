package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class signUpController {
    @FXML
    private ImageView img;
    @FXML
    private Text nameText;
    @FXML
    private TextField nameTF;
    @FXML
    private Text pinText;
    @FXML
    private TextField pinTF;
    @FXML
    private PasswordField pinPF;
    @FXML
    private Text conpinText;
    @FXML
    private TextField conpinTF;
    @FXML
    private PasswordField conpinPF;
    @FXML
    private CheckBox check;
    @FXML
    private CheckBox concheck;
    @FXML
    private Button subButton;
    @FXML
    private Button backButton;

    @FXML
    public void showPin(MouseEvent e) {
        if (check.isSelected()) {
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
    public void conshowPin(MouseEvent e) {
        if(concheck.isSelected()){
            conpinTF.setVisible(true);
            conpinTF.setText(conpinPF.getText());
            conpinPF.setVisible(false);
        } else {
            conpinPF.setVisible(true);
            conpinPF.setText(conpinTF.getText());
            conpinTF.setVisible(false);
        }
    }


    @FXML
    public void submit(MouseEvent e) throws IOException {
        int pin=0;
        boolean booPin = false;
        int conpin=0;
        boolean conbooPin = false;
        if(pinTF.isVisible() ){
            pin = Integer.parseInt(pinTF.getText());
            booPin = true;

        }
        if (conpinTF.isVisible()) {
            conpin = Integer.parseInt(conpinTF.getText());
            conbooPin = true;
        }
        if(pinPF.isVisible()){
            pin = Integer.parseInt(pinPF.getText());
            booPin = true;
        }
        if (conpinPF.isVisible()) {
            conpin = Integer.parseInt(conpinPF.getText());
            conbooPin = true;
        }
        if(conbooPin && booPin && pin >= 1000 && pin <= 9999){
            if(pin == conpin){
                Parent root = FXMLLoader.load(getClass().getResource("/FXML Files/PlaceholderSuccessSB.fxml"));
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                pinText.setText("Please make sure your pins match");
            }


        } else {
            pinText.setText("Please make sure your pin is four digits");
            conpinText.setText("Please make sure your pin is four digits");
        }
    }
    @FXML
    public void back(MouseEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML Files/LoginSB.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
