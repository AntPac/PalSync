package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    public void setUserInformation(String username){

        //label_welcome.setText("Welcome " + username + "!");
    }
    // ListView to display events for the selected day
    @FXML
    private ListView<String> eventListView;

    // Day boxes (each day in the calendar)
    @FXML private VBox vbox0;
    @FXML private VBox vbox1;
    @FXML private VBox vbox2;
    @FXML private VBox vbox3;
    @FXML private VBox vbox4;
    @FXML private VBox vbox5;
    @FXML private VBox vbox6;
    @FXML private VBox vbox7;
    @FXML private VBox vbox8;
    @FXML private VBox vbox9;
    @FXML private VBox vbox10;
    @FXML private VBox vbox11;
    @FXML private VBox vbox12;
    @FXML private VBox vbox13;
    @FXML private VBox vbox14;
    @FXML private VBox vbox15;
    @FXML private VBox vbox16;
    @FXML private VBox vbox17;
    @FXML private VBox vbox18;
    @FXML private VBox vbox19;
    @FXML private VBox vbox20;
    @FXML private VBox vbox21;
    @FXML private VBox vbox22;
    @FXML private VBox vbox23;
    @FXML private VBox vbox24;
    @FXML private VBox vbox25;
    @FXML private VBox vbox26;
    @FXML private VBox vbox27;
    @FXML private VBox vbox28;
    @FXML private VBox vbox29;
    @FXML private VBox vbox30;
    @FXML private VBox vbox31;
    @FXML private VBox vbox32;
    @FXML private VBox vbox33;
    @FXML private VBox vbox34;

    // List to hold all vBoxes for easy access
    private List<VBox> dayBoxes = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add all day boxes to the list for easier management
        dayBoxes.add(vbox0);
        dayBoxes.add(vbox1);
        dayBoxes.add(vbox2);
        dayBoxes.add(vbox3);
        dayBoxes.add(vbox4);
        dayBoxes.add(vbox5);
        dayBoxes.add(vbox6);
        dayBoxes.add(vbox7);
        dayBoxes.add(vbox8);
        dayBoxes.add(vbox9);
        dayBoxes.add(vbox10);
        dayBoxes.add(vbox11);
        dayBoxes.add(vbox12);
        dayBoxes.add(vbox13);
        dayBoxes.add(vbox14);
        dayBoxes.add(vbox15);
        dayBoxes.add(vbox16);
        dayBoxes.add(vbox17);
        dayBoxes.add(vbox18);
        dayBoxes.add(vbox19);
        dayBoxes.add(vbox20);
        dayBoxes.add(vbox21);
        dayBoxes.add(vbox22);
        dayBoxes.add(vbox23);
        dayBoxes.add(vbox24);
        dayBoxes.add(vbox25);
        dayBoxes.add(vbox26);
        dayBoxes.add(vbox27);
        dayBoxes.add(vbox28);
        dayBoxes.add(vbox29);
        dayBoxes.add(vbox30);
        dayBoxes.add(vbox31);
        dayBoxes.add(vbox32);
        dayBoxes.add(vbox33);
        dayBoxes.add(vbox34);


        // Initialize each day box with a click handler
        for (int i = 0; i < dayBoxes.size(); i++) {
            final int day = i + 1; // day of the month
            VBox dayBox = dayBoxes.get(i);

            dayBox.setOnMouseClicked(event -> {
                String date = getDateForDay(day); // Method to get date string for the day
                displayEventsForDate(date);
            });
        }
    }

    // Method to format a date string for each day
    private String getDateForDay(int day) {
        // For demonstration, assume current month and year, adjust as needed
        return "2023-11-" + (day < 10 ? "0" + day : day); // Format as YYYY-MM-DD
    }

    // Display events for the selected date in the ListView
    private void displayEventsForDate(String date) {
        eventListView.getItems().clear(); // Clear previous items

        ArrayList<Event> events = EventManager.getEventsForDate(date); // Get events from EventManager
        if (events.isEmpty()) {
            eventListView.getItems().add("No events available");
        } else {
            for (Event event : events) {
                String eventDetails = event.getName() + " (" + event.getStartTime() + " - " + event.getEndTime() + ")";
                eventListView.getItems().add(eventDetails);
            }
        }
    }
}
