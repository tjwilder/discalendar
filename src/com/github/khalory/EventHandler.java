package com.github.khalory;

// USAGE: Creates, backs up, and sends announcements using array lists.


import java.util.ArrayList;

public class EventHandler {

    static ArrayList<com.github.khalory.Event> allEvents = new ArrayList();

    // TODO: load events into arraylist when the bot starts up.

    public static void addEvent(com.github.khalory.Event newEvent) {
        allEvents.add(newEvent);
        // TODO: if event is happening soon, add it to the coming soon list instead of the all events list.
    }
    // We may want to use a more efficient list system, such as a binary search tree.

    /* TODO: add quartz to this project and call upon it here so that it can
        SOMETIMES: check to see if there are events coming soon, and optimize the "coming soon" list.
        OFTEN: check the "coming soon" list for events that are happening or past happening, and ping the proper server
        RARELY: back up the events list using FileLocalStore.
     */
}
