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

public class forgotController {
    @FXML
    private ImageView img1;
    @FXML
    private Text pinText1;
    @FXML
    private Text nameText1;
    @FXML
    private TextField nameTF1;
    @FXML
    private TextField pinTF1;
    @FXML
    private PasswordField pinPF1;
    @FXML
    private Text conpinText1;
    @FXML
    private TextField conpinTF1;
    @FXML
    private PasswordField conpinPF1;
    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox concheck1;
    @FXML
    private Button subButton1;
    @FXML
    private Button backButton1;
    @FXML
    public void showPin1(MouseEvent e) {
        if (check1.isSelected()) {
            pinTF1.setVisible(true);
            pinTF1.setText(pinPF1.getText());
            pinPF1.setVisible(false);
        } else {
            pinPF1.setVisible(true);
            pinPF1.setText(pinTF1.getText());
            pinTF1.setVisible(false);
        }
    }
    @FXML
    public void conshowPin1(MouseEvent e) {
        if(concheck1.isSelected()){
            conpinTF1.setVisible(true);
            conpinTF1.setText(conpinPF1.getText());
            conpinPF1.setVisible(false);
        } else {
            conpinPF1.setVisible(true);
            conpinPF1.setText(conpinTF1.getText());
            conpinTF1.setVisible(false);
        }
    }


    @FXML
    public void submit1(MouseEvent e) throws IOException {
        int pin=0;
        boolean booPin = false;
        int conpin=0;
        boolean conbooPin = false;
        if(pinTF1.isVisible() ){
            pin = Integer.parseInt(pinTF1.getText());
            booPin = true;
        }
        if (conpinTF1.isVisible()) {
            conpin = Integer.parseInt(conpinTF1.getText());
            conbooPin = true;
        }
        if(pinPF1.isVisible()){
            pin = Integer.parseInt(pinPF1.getText());
            booPin = true;
        }
        if (conpinPF1.isVisible()) {
            conpin = Integer.parseInt(conpinPF1.getText());
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
                pinText1.setText("Please make sure your pins match");
            }


        } else {
            pinText1.setText("Please make sure your pin is four digits");
            conpinText1.setText("Please make sure your pin is four digits");
        }
        }
    @FXML
    public void back1(MouseEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML Files/LoginSB.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
