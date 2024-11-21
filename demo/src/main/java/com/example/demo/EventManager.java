package com.example.demo;


import java.sql.*;
import java.util.ArrayList;


public class EventManager {


    // Fetch events for a specific date and user
    public static ArrayList<Event> getEventsForDate(String date, int userId) {
        ArrayList<Event> events = new ArrayList<>();
        String query = "SELECT event_name, start_time, end_time, note FROM events WHERE event_date = ? AND user_id = ?";


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/me", "root", "Password1");
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
}
