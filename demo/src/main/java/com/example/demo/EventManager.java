package com.example.demo;

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
