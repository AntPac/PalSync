package com.example.demo;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class EventManager {


    // Fetch events for a specific date and user
    public static ArrayList<Event> getEventsForDate(String date, int userId) {
        ArrayList<Event> events = new ArrayList<>();
        String query = "SELECT event_name, start_time, end_time, note FROM events WHERE event_date = ? AND user_id = ?";


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PalSyncDB", "root", "AugChico");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, userId);


            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString("event_name");
                String startTime = resultSet.getTime("start_time").toString();
                String endTime = resultSet.getTime("end_time").toString();
                String note = resultSet.getString("note");


                // Create an Event object for each result
                events.add(new Event(name, date, startTime, endTime, note));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return events;
    }
    public static ArrayList<Event> getEventsForMonth(LocalDate start, LocalDate end, int userId) {
        ArrayList<Event> events = new ArrayList<>();
        String query = "SELECT event_name, event_date, start_time, end_time, note FROM events WHERE user_id = ? AND event_date BETWEEN ? AND ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataBaseName", "root", "Password");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, Date.valueOf(start));
            preparedStatement.setDate(3, Date.valueOf(end));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("event_name");
                LocalDate eventDate = resultSet.getDate("event_date").toLocalDate();
                String startTime = resultSet.getTime("start_time").toString();
                String endTime = resultSet.getTime("end_time").toString();
                String note = resultSet.getString("note");

                events.add(new Event(name, eventDate.toString(), startTime, endTime, note));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

}
