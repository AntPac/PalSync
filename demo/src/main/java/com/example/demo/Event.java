package com.example.demo;
/*
a) Event
b) Creation Date: November 8, 2024
c) Programmer’s Name: Mohy Elhelw
d) Purpose: Represents an individual event in the application.
e) Role: Defines the properties and behavior of an event, such as its title, date and associate user. The important method that we have used in this class is the Getter Method.
f) Data Structures: None
g) Algorithms: None

 */
public class Event {
    private String name;
    private String date;
    private String startTime;
    private String endTime;
    private String note;

    public Event(String name, String date, String startTime, String endTime, String note) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    // Getters for each property
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getNote() {
        return note;
    }
}
