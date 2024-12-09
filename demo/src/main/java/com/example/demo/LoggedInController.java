package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;



import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class  LoggedInController implements Initializable {
    private String currentUsername;

    @FXML
    private AnchorPane eventsView;
    @FXML
    private AnchorPane createEventView;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField noteTextField;

    @FXML
    private DatePicker startDatePicker, endDatePicker;

    @FXML
    private ComboBox<String> startTimeComboBox, startMinuteComboBox, endTimeComboBox, endMinuteComboBox;

    @FXML
    private CheckBox allDayToggle;


    public void setUserInformation(String username) {
        this.currentUsername = username;
        int userId = getUserIdFromUsername(username);

        displayEventsForMonth(LocalDate.now(), userId);
    }
    private void displayEventsForMonth(LocalDate date, int userId) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());
        ArrayList<Event> monthEvents = EventManager.getEventsForMonth(firstDayOfMonth, lastDayOfMonth, userId);
        PriorityQueue<Event> eventQueue = new PriorityQueue<>(
                Comparator.comparing(Event::getDate).thenComparing(Event::getStartTime)
        );


        // Add events to the PriorityQueue
        eventQueue.addAll(monthEvents);


        eventListView.getItems().clear();
        if (monthEvents.isEmpty()) {
            eventListView.getItems().add("No events available.");
        } else {
            while (!eventQueue.isEmpty()) {
                Event event = eventQueue.poll();


                String eventDetails = String.format("%s \n%s \n%s - %s \n%s", event.getDate(), event.getName(),  event.getStartTime(), event.getEndTime(),event.getNote());


                eventListView.getItems().add(eventDetails);
            }
        }
    }

    @FXML private ListView<String> eventListView;

    @FXML private VBox vbox0, vbox1, vbox2, vbox3, vbox4, vbox5, vbox6, vbox7, vbox8, vbox9;
    @FXML private VBox vbox10, vbox11, vbox12, vbox13, vbox14, vbox15, vbox16, vbox17, vbox18, vbox19;
    @FXML private VBox vbox20, vbox21, vbox22, vbox23, vbox24, vbox25, vbox26, vbox27, vbox28, vbox29;
    @FXML private VBox vbox30, vbox31, vbox32, vbox33, vbox34, vbox35, vbox36, vbox37, vbox38, vbox39, vbox40, vbox41;

    @FXML private Text Text0, Text1, Text2, Text3, Text4, Text5, Text6, Text7, Text8, Text9;
    @FXML private Text Text10, Text11, Text12, Text13, Text14, Text15, Text16, Text17, Text18, Text19;
    @FXML private Text Text20, Text21, Text22, Text23, Text24, Text25, Text26, Text27, Text28, Text29;
    @FXML private Text Text30, Text31, Text32, Text33, Text34, Text35, Text36, Text37, Text38, Text39, Text40, Text41;

    @FXML private Text TextMonth;
    @FXML private Button Next, Prev;

    private List<VBox> dayBoxes = new ArrayList<>();
    private List<Text> TextBoxes = new ArrayList<>();
    private CalendarSetup show = new CalendarSetup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupEventContextMenu();
        dayBoxes.add(vbox0); dayBoxes.add(vbox1); dayBoxes.add(vbox2); dayBoxes.add(vbox3); dayBoxes.add(vbox4);
        dayBoxes.add(vbox5); dayBoxes.add(vbox6); dayBoxes.add(vbox7); dayBoxes.add(vbox8); dayBoxes.add(vbox9);
        dayBoxes.add(vbox10); dayBoxes.add(vbox11); dayBoxes.add(vbox12); dayBoxes.add(vbox13); dayBoxes.add(vbox14);
        dayBoxes.add(vbox15); dayBoxes.add(vbox16); dayBoxes.add(vbox17); dayBoxes.add(vbox18); dayBoxes.add(vbox19);
        dayBoxes.add(vbox20); dayBoxes.add(vbox21); dayBoxes.add(vbox22); dayBoxes.add(vbox23); dayBoxes.add(vbox24);
        dayBoxes.add(vbox25); dayBoxes.add(vbox26); dayBoxes.add(vbox27); dayBoxes.add(vbox28); dayBoxes.add(vbox29);
        dayBoxes.add(vbox30); dayBoxes.add(vbox31); dayBoxes.add(vbox32); dayBoxes.add(vbox33); dayBoxes.add(vbox34);
        dayBoxes.add(vbox35); dayBoxes.add(vbox36); dayBoxes.add(vbox37); dayBoxes.add(vbox38); dayBoxes.add(vbox39);
        dayBoxes.add(vbox40); dayBoxes.add(vbox41);

        TextBoxes.add(Text0); TextBoxes.add(Text1); TextBoxes.add(Text2); TextBoxes.add(Text3); TextBoxes.add(Text4);
        TextBoxes.add(Text5); TextBoxes.add(Text6); TextBoxes.add(Text7); TextBoxes.add(Text8); TextBoxes.add(Text9);
        TextBoxes.add(Text10); TextBoxes.add(Text11); TextBoxes.add(Text12); TextBoxes.add(Text13); TextBoxes.add(Text14);
        TextBoxes.add(Text15); TextBoxes.add(Text16); TextBoxes.add(Text17); TextBoxes.add(Text18); TextBoxes.add(Text19);
        TextBoxes.add(Text20); TextBoxes.add(Text21); TextBoxes.add(Text22); TextBoxes.add(Text23); TextBoxes.add(Text24);
        TextBoxes.add(Text25); TextBoxes.add(Text26); TextBoxes.add(Text27); TextBoxes.add(Text28); TextBoxes.add(Text29);
        TextBoxes.add(Text30); TextBoxes.add(Text31); TextBoxes.add(Text32); TextBoxes.add(Text33); TextBoxes.add(Text34);
        TextBoxes.add(Text35); TextBoxes.add(Text36); TextBoxes.add(Text37); TextBoxes.add(Text38); TextBoxes.add(Text39);
        TextBoxes.add(Text40); TextBoxes.add(Text41);

        for (int i = 0; i < dayBoxes.size(); i++) {
            final int boxIndex = i;
            VBox dayBox = dayBoxes.get(i);

            dayBox.setOnMouseClicked(event -> {
                String date = getDateForDay(boxIndex);
                displayEventsForDate(date);
            });

            ContextMenu contextMenu = new ContextMenu();
            MenuItem createEventItem = new MenuItem("Create Event");
            createEventItem.setOnAction(actionEvent -> {
                String selectedDate = getDateForDay(boxIndex);
                openCreateEventPopup(selectedDate);
            });

            contextMenu.getItems().addAll(createEventItem);
            dayBox.setOnContextMenuRequested(event -> contextMenu.show(dayBox, event.getScreenX(), event.getScreenY()));
        }

        CalendarSetup now = new CalendarSetup();
        now.calendarMonth();
        show = now;
        setDays(show);

        for (VBox dayBox : dayBoxes) {
            dayBox.requestLayout();
        }

        Next.setOnMouseClicked(event -> {
            show.changeMonth(true);
            setDays(show);
        });
        Prev.setOnMouseClicked(event -> {
            show.changeMonth(false);
            setDays(show);
        });
        TextMonth.setOnMouseClicked(event ->{
            openDateSelector();
        });

        for (int i = 0; i < 24; i++) {
            String hour = String.format("%02d", i);
            startTimeComboBox.getItems().add(hour);
            endTimeComboBox.getItems().add(hour);
        }
        for (int i = 0; i < 60; i++) {
            String minute = String.format("%02d", i);
            startMinuteComboBox.getItems().add(minute);
            endMinuteComboBox.getItems().add(minute);
        }
    }
    private void openCreateEventPopup(String selectedDate) {
        if (selectedDate == null) {
            System.out.println("Error: No valid date selected.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("makeEvent.fxml"));
            Parent popupRoot = loader.load();

            MakeEventController controller = loader.getController();
            if (controller != null) {
                controller.setSelectedDate(selectedDate);
                controller.setUsername(currentUsername);
                controller.setCalendarController(this);
            }

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Create New Event");
            popupStage.setScene(new Scene(popupRoot, 482, 281));
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDateSelector() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dateSelector.fxml"));
            Parent popupRoot = loader.load();
            DateSelectorController controller = loader.getController();
            controller.setMainController(this);
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Create New Event");
            popupStage.setScene(new Scene(popupRoot, 300, 200));
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getDateForDay(int boxIndex) {
        Object[][] calendar = show.getCalendar();
        LocalDate currentDate = show.getCurrentDate();

        int row = boxIndex / 7;
        int col = boxIndex % 7;

        if (row >= calendar.length || col >= calendar[row].length) {
            System.out.println("Error: Index out of bounds. Row: " + row + ", Col: " + col);
            return null;
        }

        Object cellValue = calendar[row][col];
        System.out.println("Row: " + row + ", Col: " + col + ", Cell Value: " + cellValue);

        if (cellValue == null || cellValue.equals(" ")) {
            return null;
        }

        try {
            int dayOfMonth = Integer.parseInt(cellValue.toString());
            return currentDate.withDayOfMonth(dayOfMonth).toString();
        } catch (NumberFormatException e) {
            System.out.println("Invalid day value: " + cellValue);
            return null;
        }
    }

    public void displayEventsForDate(String date) {
        eventListView.getItems().clear();

        if (date == null) {
            eventListView.getItems().add("No valid date selected.");
            return;
        }
        int userId = getUserIdFromUsername(currentUsername);
        ArrayList<Event> events = EventManager.getEventsForDate(date, userId);
        if (events.isEmpty()) {
            eventListView.getItems().add("No events available.");
        } else {
            for (Event event : events) {
                String eventDetails = String.format("%s \n %s - %s \n %s", event.getName(), event.getStartTime(), event.getEndTime(),event.getNote());

                eventListView.getItems().add(eventDetails);
            }
        }
    }
    private int getUserIdFromUsername(String username) {
        int userId = -1;
        String query = "SELECT user_ID FROM users WHERE username = ?";


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename", "root", "password");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                userId = resultSet.getInt("user_ID");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    private void setDays(CalendarSetup display) {
        LocalDate current = display.getCurrentDate();
        Object[][] cal = display.getCalendar();

        TextMonth.setText(current.getMonth().toString());

        int i = 0;
        for (int j = 0; j < cal.length; j++) {
            for (int k = 0; k < cal[j].length; k++) {
                if (i < TextBoxes.size()) {
                    Object value = cal[j][k];
                    TextBoxes.get(i).setText(value == null ? "" : " " + value.toString());
                    dayBoxes.get(i).setStyle("-fx-background-color : white;");
                    i++;
                }
            }
        }
        for (int j = 0; j< TextBoxes.size(); j++){
            if (TextBoxes.get(j).getText().equals("  ")){
                dayBoxes.get(j).setStyle("-fx-background-color : #D3D3D3;");
            }
        }

    }
    public  void setSelected(int m, int y){
        show.setDate(m,y);
        setDays(show);
    }


    @FXML
    private void showCreateEventView() {
        eventsView.setVisible(false);
        createEventView.setVisible(true);
    }

    @FXML
    private void showEventsView() {
        createEventView.setVisible(false);
        eventsView.setVisible(true);

        if (currentUsername != null) {
            int userId = getUserIdFromUsername(currentUsername);
            displayEventsForMonth(LocalDate.now(), userId);
        }
    }

    private void setupEventContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Delete Event");

        deleteItem.setOnAction(event -> {
            String selectedEvent = eventListView.getSelectionModel().getSelectedItem();
            if (selectedEvent != null) {
                handleEventDeletion(selectedEvent);
            }
        });

        contextMenu.getItems().add(deleteItem);

        eventListView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(eventListView, event.getScreenX(), event.getScreenY());
            } else {
                contextMenu.hide();
            }
        });
    }

    private void handleEventDeletion(String selectedEventDetails) {

        String[] details = selectedEventDetails.split("\n");
        if (details.length > 0) {
            String eventName = details[1].trim();

            deleteEventFromDatabase(eventName);

            eventListView.getItems().remove(selectedEventDetails);

            System.out.println("Event deleted successfully: " + eventName);
        } else {
            System.out.println("Error: Unable to parse selected event details.");
        }
    }

    private void deleteEventFromDatabase(String eventName) {
        String deleteQuery = "DELETE FROM events WHERE event_name = ? AND user_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Databasename", "root", "Password");
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            int userId = getUserIdFromUsername(currentUsername);
            preparedStatement.setString(1, eventName);
            preparedStatement.setInt(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Event deleted successfully from the database.");
            } else {
                System.out.println("No event found to delete.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitApplication() {
        System.exit(0);
    }

    @FXML
    private void saveEventToDatabase() {
        try {
            String eventName = titleTextField.getText();
            LocalDate eventDate = startDatePicker.getValue();
            if (startTimeComboBox.getValue() == null || startMinuteComboBox.getValue() == null ||
                    endTimeComboBox.getValue() == null || endMinuteComboBox.getValue() == null) {
                System.out.println("Time fields are required.");
                return;
            }

            LocalTime startTime = LocalTime.of(
                    Integer.parseInt(startTimeComboBox.getValue()),
                    Integer.parseInt(startMinuteComboBox.getValue())
            );
            LocalTime endTime = LocalTime.of(
                    Integer.parseInt(endTimeComboBox.getValue()),
                    Integer.parseInt(endMinuteComboBox.getValue())
            );
            String note = noteTextField.getText();
            if (note != null && note.trim().isEmpty()) {
                note = "";
            }

            if (eventName == null || eventName.trim().isEmpty() || eventDate == null) {
                System.out.println("Event name and date are required.");
                return;
            }

            System.out.println("Event Name: " + eventName);
            System.out.println("Event Date: " + eventDate);
            System.out.println("Start Time: " + startTime);
            System.out.println("End Time: " + endTime);
            System.out.println("Note: " + note);
            System.out.println("Current Username: " + currentUsername);

            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PalSyncDB", "root", "AugChico")) {

                String getUserIdQuery = "SELECT user_ID FROM users WHERE username = ?";
                try (PreparedStatement getUserIdStmt = connection.prepareStatement(getUserIdQuery)) {
                    getUserIdStmt.setString(1, currentUsername);
                    ResultSet rs = getUserIdStmt.executeQuery();
                    if (rs.next()) {
                        int userId = rs.getInt("user_ID");

                        String insertQuery = "INSERT INTO events (user_id, event_name, event_date, start_time, end_time, note) VALUES ( ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                            insertStmt.setInt(1, userId);
                            insertStmt.setString(2, eventName);
                            insertStmt.setDate(3, java.sql.Date.valueOf(eventDate));
                            insertStmt.setTime(4, java.sql.Time.valueOf(startTime));
                            insertStmt.setTime(5, java.sql.Time.valueOf(endTime));
                            insertStmt.setString(6, note);

                            int rowsAffected = insertStmt.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Event saved successfully!");
                                showEventsView();
                            }
                        }
                    } else {
                        System.out.println("User ID not found for the current username.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid time input. Please enter valid hours and minutes.");
            e.printStackTrace();
        }
    }
}
