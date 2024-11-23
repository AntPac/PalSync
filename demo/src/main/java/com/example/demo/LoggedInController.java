package com.example.demo;
/*
a) LoggedInController
b) Creation Date: September 30 2024
c) Programmer’s Name: Anthony Pacheco, Mohy Elhelw, Javier Pulido
d) Purpose: Controls the behavior of the homepage after successful user authentication. Initialize() is what creates the setup of the calendar itself, that being what days the dates fall on and how many days in the specific month itself. getDateForDay() formats the date string for each day. displayEventsForDate() Displays the events when a date is selected in a list view on the left hand side. setDays() uses the calendar Setup class to organize and display the proper dates in the GUIcalendar.
e) Role: Fetches and displays user-specific data such as events.
f) Data Structures: Array List
g) Algorithms: SetDays() creates a calendar object then calls the calendarMonth method to create a display of the current class. It then uses the textbox arraylist to equal each index to its counterpart on the GUI and display the proper month
displayEventsForDay() checks the sql for event data on a specifically selected dat then presents it on the side event view of the day has set events
getDateForEvents()  Uses the calendarSetup class to find the specific date of the day selected and returns the date
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    private String currentUsername;

    public void setUserInformation(String username) {
        this.currentUsername = username;
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
        // Add all day boxes to the list
        dayBoxes.add(vbox0); dayBoxes.add(vbox1); dayBoxes.add(vbox2); dayBoxes.add(vbox3); dayBoxes.add(vbox4);
        dayBoxes.add(vbox5); dayBoxes.add(vbox6); dayBoxes.add(vbox7); dayBoxes.add(vbox8); dayBoxes.add(vbox9);
        dayBoxes.add(vbox10); dayBoxes.add(vbox11); dayBoxes.add(vbox12); dayBoxes.add(vbox13); dayBoxes.add(vbox14);
        dayBoxes.add(vbox15); dayBoxes.add(vbox16); dayBoxes.add(vbox17); dayBoxes.add(vbox18); dayBoxes.add(vbox19);
        dayBoxes.add(vbox20); dayBoxes.add(vbox21); dayBoxes.add(vbox22); dayBoxes.add(vbox23); dayBoxes.add(vbox24);
        dayBoxes.add(vbox25); dayBoxes.add(vbox26); dayBoxes.add(vbox27); dayBoxes.add(vbox28); dayBoxes.add(vbox29);
        dayBoxes.add(vbox30); dayBoxes.add(vbox31); dayBoxes.add(vbox32); dayBoxes.add(vbox33); dayBoxes.add(vbox34);
        dayBoxes.add(vbox35); dayBoxes.add(vbox36); dayBoxes.add(vbox37); dayBoxes.add(vbox38); dayBoxes.add(vbox39);
        dayBoxes.add(vbox40); dayBoxes.add(vbox41);

        // Add all text boxes to the list
        TextBoxes.add(Text0); TextBoxes.add(Text1); TextBoxes.add(Text2); TextBoxes.add(Text3); TextBoxes.add(Text4);
        TextBoxes.add(Text5); TextBoxes.add(Text6); TextBoxes.add(Text7); TextBoxes.add(Text8); TextBoxes.add(Text9);
        TextBoxes.add(Text10); TextBoxes.add(Text11); TextBoxes.add(Text12); TextBoxes.add(Text13); TextBoxes.add(Text14);
        TextBoxes.add(Text15); TextBoxes.add(Text16); TextBoxes.add(Text17); TextBoxes.add(Text18); TextBoxes.add(Text19);
        TextBoxes.add(Text20); TextBoxes.add(Text21); TextBoxes.add(Text22); TextBoxes.add(Text23); TextBoxes.add(Text24);
        TextBoxes.add(Text25); TextBoxes.add(Text26); TextBoxes.add(Text27); TextBoxes.add(Text28); TextBoxes.add(Text29);
        TextBoxes.add(Text30); TextBoxes.add(Text31); TextBoxes.add(Text32); TextBoxes.add(Text33); TextBoxes.add(Text34);
        TextBoxes.add(Text35); TextBoxes.add(Text36); TextBoxes.add(Text37); TextBoxes.add(Text38); TextBoxes.add(Text39);
        TextBoxes.add(Text40); TextBoxes.add(Text41);

        // Add event listeners to day boxes
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
        show = now; // Save current CalendarSetup instance
        setDays(show);

        // Step 4: Force refresh of the UI components
        for (VBox dayBox : dayBoxes) {
            dayBox.requestLayout(); // Ensure all layout updates are applied
        }

        // Step 5: Add navigation button functionality
        Next.setOnMouseClicked(event -> {
            show.changeMonth(true);
            setDays(show);
        });
        Prev.setOnMouseClicked(event -> {
            show.changeMonth(false);
            setDays(show);
        });
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

    private void displayEventsForDate(String date) {
        eventListView.getItems().clear();

        if (date == null) {
            eventListView.getItems().add("No valid date selected.");
            return;
        }

        ArrayList<Event> events = EventManager.getEventsForDate(date);
        if (events.isEmpty()) {
            eventListView.getItems().add("No events available.");
        } else {
            for (Event event : events) {
                eventListView.getItems().add(event.getName() + " (" + event.getStartTime() + " - " + event.getEndTime() + ")");
            }
        }
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
                    i++;
                }
            }
        }
    }
}
