package com.example.myproject.model;

public class Event {
    String id ;
    String label;
    String description;
    String date_event;

    public Event() {
    }

    public Event(String label, String description, String date_event) {
        this.label = label;
        this.description = description;
        this.date_event = date_event;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }
}
