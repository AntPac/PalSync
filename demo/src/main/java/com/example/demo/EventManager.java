package com.example.demo;
/*
a) EventManager
b) Creation Date: November 8, 2024
c) Programmer’s Name: Mohy Elhelw
d) Purpose:Provides centralized functionality for managing events.
e) Role: Acts as a bridge between the database and the application logic and to a centralized utility class  responsible for managing all event operations in the application.
f) Data Structures:ArrayList
g) Algorithms: None
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    private static Map<String, ArrayList<Event>> eventMap = new HashMap<>();

    public static void addEvent(Event event) {
        String eventDate = event.getDate();
        eventMap.computeIfAbsent(eventDate, k -> new ArrayList<>()).add(event);
    }

    public static ArrayList<Event> getEventsForDate(String date) {
        return eventMap.getOrDefault(date, new ArrayList<>());
    }
}
