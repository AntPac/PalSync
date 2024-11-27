package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DateSelectorController implements Initializable {
    @FXML
    private TextField YearField;
    @FXML private Button b0;
    @FXML private Button b1;
    @FXML private Button b2;
    @FXML private Button b3;
    @FXML private Button b4;
    @FXML private Button b5;
    @FXML private Button b6;
    @FXML private Button b7;
    @FXML private Button b8;
    @FXML private Button b9;
    @FXML private Button b10;
    @FXML private Button b11;
    @FXML private Button b12;
    private List<Button> Buttons = new ArrayList<>();
    private int month, year;
    private LoggedInController main;

   public void initialize(URL location, ResourceBundle resources) {
        Buttons.add(b0);
        Buttons.add(b1);
        Buttons.add(b2);
        Buttons.add(b3);
        Buttons.add(b4);
        Buttons.add(b5);
        Buttons.add(b6);
        Buttons.add(b7);
        Buttons.add(b8);
        Buttons.add(b9);
        Buttons.add(b10);
        Buttons.add(b11);

        for (int i = 0; i < Buttons.size(); i++) {
            Button b = Buttons.get(i);
            int finalI = i;
            b.setOnMouseClicked(event -> {
                month = finalI +1;
                System.out.println(month);

            });

        }

        b12.setOnMouseClicked(event -> {
            System.out.println("selected");
            try {
                year = Integer.parseInt(YearField.getText());
                if (main != null) {
                    main.setSelected(month, year);
                    System.out.println("changed");
                }
                ((Button) event.getSource()).getScene().getWindow().hide();
            } catch (NumberFormatException e) {
                System.err.println("Invalid year input: " + YearField.getText());
            }
        });
    }
    public void setMainController(LoggedInController main) {
        this.main = main;
    }
    }
