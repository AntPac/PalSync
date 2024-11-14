package com.example.demo;

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
