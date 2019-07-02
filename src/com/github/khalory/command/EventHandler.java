package com.github.khalory.command;

// USAGE: Creates, backs up, and starts announcements for server array lists.


import java.util.ArrayList;

public class EventHandler {

    ArrayList<com.github.khalory.Event> allEvents = new ArrayList();


    public void addEvent(com.github.khalory.Event newEvent) {
        allEvents.add(newEvent);
    }
}
