package com.example.demo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;



public class WelcomePageSB {
    @FXML
    private Button logButton;
    @FXML
    private Button signButton;
    @FXML
    private ImageView imageView;
    @FXML
    private Text welcomeText;
@FXML
public void logAct(MouseEvent me1) throws IOException{
    System.out.println("clicked1");
    Parent root = FXMLLoader.load(getClass().getResource("/FXML Files/LoginSB.fxml"));
    Stage stage = (Stage)((Node)me1.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}

@FXML
public void signAct(MouseEvent me2)throws IOException{
    System.out.println("clicked2");
    Parent root = FXMLLoader.load(getClass().getResource("/FXML Files/SignUPSB.fxml"));
    Stage stage = (Stage)((Node)me2.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
}
