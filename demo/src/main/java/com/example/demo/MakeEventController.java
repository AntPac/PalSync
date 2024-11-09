package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MakeEventController implements Initializable {

    @FXML
    private TextField eventNameField;

    @FXML
    private DatePicker eventDatePicker;

    @FXML
    private ComboBox<String> startTimeComboBox;

    @FXML
    private ComboBox<String> endTimeComboBox;

    @FXML
    private TextField noteField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for (int i = 0; i < 24; i++) {
            String time = String.format("%02d:00", i);
            startTimeComboBox.getItems().add(time);
            endTimeComboBox.getItems().add(time);
        }

        // Set the action for the save button
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String eventName = eventNameField.getText();
                String eventDate = eventDatePicker.getValue() != null ? eventDatePicker.getValue().toString() : "";
                String startTime = startTimeComboBox.getValue();
                String endTime = endTimeComboBox.getValue();
                String note = noteField.getText();

                // Create a new Event instance
                Event newEvent = new Event(eventName, eventDate, startTime, endTime, note);

                // Add the event to EventManager
                EventManager.addEvent(newEvent);

                System.out.println("Event saved to EventManager:");
                System.out.println("Name: " + eventName);
                System.out.println("Date: " + eventDate);
                System.out.println("Start Time: " + startTime);
                System.out.println("End Time: " + endTime);
                System.out.println("Note: " + note);
            }
        });


        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Clear all fields
                eventNameField.clear();
                eventDatePicker.setValue(null);
                startTimeComboBox.setValue(null);
                endTimeComboBox.setValue(null);
                noteField.clear();

                System.out.println("Event creation canceled");
            }
        });
    }
}
