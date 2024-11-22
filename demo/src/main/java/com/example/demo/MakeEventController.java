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
import java.sql.*;
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

                // Validate that all necessary fields are filled
                if (eventName.isEmpty() || eventDate.isEmpty() || startTime == null || endTime == null) {
                    System.out.println("Please fill in all the required fields.");
                    return;
                }

                // Save the event to the database
                saveEventToDatabase(eventName, eventDate, startTime, endTime, note);
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
    private void saveEventToDatabase(String eventName, String eventDate, String startTime, String endTime, String note) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/palsync-login", "root", "Silverlining1986");
            // Get the user_id based on the username
            preparedStatement = connection.prepareStatement("SELECT user_ID FROM users WHERE username = ?");
            preparedStatement.setString(1, this.username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_ID");

                // Insert the event into the database
                preparedStatement = connection.prepareStatement(
                        "INSERT INTO events (user_id, event_name, event_date, start_time, end_time) VALUES (?, ?, ?, ?, ?)"
                );
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, eventName);
                preparedStatement.setDate(3, java.sql.Date.valueOf(eventDate));
                preparedStatement.setTime(4, java.sql.Time.valueOf(startTime + ":00"));
                preparedStatement.setTime(5, java.sql.Time.valueOf(endTime + ":00"));

                preparedStatement.executeUpdate();
                System.out.println("Event saved to database.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
