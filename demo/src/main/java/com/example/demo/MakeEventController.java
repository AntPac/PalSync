package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MakeEventController implements Initializable {

    @FXML
    private TextField eventNameField;

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

    private String selectedDate;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
        System.out.println("Selected date set in MakeEventController: " + selectedDate);
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for (int i = 0; i < 24; i++) {
            String time = String.format("%02d:00", i);
            startTimeComboBox.getItems().add(time);
            endTimeComboBox.getItems().add(time);
        }
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String eventName = eventNameField.getText();
                String eventDate = selectedDate;
                String startTime = startTimeComboBox.getValue();
                String endTime = endTimeComboBox.getValue();
                String note = noteField.getText();

                if (eventDate == null) {
                    System.out.println("Error: No date selected for the event.");
                    return;
                }

                if (eventName.isEmpty() || eventDate.isEmpty() || startTime == null || endTime == null) {
                    System.out.println("Please fill in all the required fields.");
                    return;
                }

                saveEventToDatabase(eventName, eventDate, startTime, endTime, note);

                closeWindow();
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Clear all fields
                eventNameField.clear();
                startTimeComboBox.setValue(null);
                endTimeComboBox.setValue(null);
                noteField.clear();

                System.out.println("Event creation canceled");

                closeWindow();
            }
        });
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public void saveEventToDatabase(String eventName, String eventDate, String startTime, String endTime, String note) {
        if (eventDate == null) {
            System.out.println("Error: Cannot save event. Event date is null.");
            return;
        }

        note = noteField.getText();
        if (note != null && note.trim().isEmpty()) {
            note = "";
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/AugChico", "root", "AugChico");
            preparedStatement = connection.prepareStatement("SELECT user_ID FROM users WHERE username = ?");
            preparedStatement.setString(1, this.username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_ID");

                preparedStatement = connection.prepareStatement(
                        "INSERT INTO events (user_id, event_name, event_date, start_time, end_time, note) " +
                                "VALUES (?, ?, ?, ?, ?, ?)"
                );
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, eventName);
                preparedStatement.setDate(3, java.sql.Date.valueOf(eventDate));
                preparedStatement.setTime(4, java.sql.Time.valueOf(startTime + ":00"));
                preparedStatement.setTime(5, java.sql.Time.valueOf(endTime + ":00"));
                preparedStatement.setString(6, note);

                preparedStatement.executeUpdate();
                System.out.println("Event saved to database.");
                if (calendarController != null) {
                    calendarController.displayEventsForDate(eventDate);
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private LoggedInController calendarController;

    public void setCalendarController(LoggedInController calendarController) {
        this.calendarController = calendarController;
    }
}
