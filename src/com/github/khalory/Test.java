package com.github.khalory;

import sx.blah.discord.api.IDiscordClient;

import java.util.ArrayList;

/**
 * Created by declan on 03/04/2017.
 */
public class Test {

    public static final boolean DEBUG = true;

    public static IDiscordClient client;

    public static void main(String[] args) {
		// Test Event.java
		String debugTitle = "basicTitle";
		String debugDesscription = "extra Long Description";
		String debugTime = "8767896";
		Event testEvent = new Event(debugTitle, debugDesscription, debugTime);

		// Test Constructor
		if (!testEvent.getTitle().equals(debugTitle))
			System.err.println("Title is not saved and loaded properly by Event");
		if (!testEvent.getDescription().equals(debugDesscription))
			System.err.println("Description is not saved and loaded properly by Event");
		if (!testEvent.getTime().equals(debugTime))
			System.err.println("Time is not saved and loaded properly by Event");

		// Create a list of events to save into the data file
		ArrayList<Event> eventList = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			Event thisEvent = new Event("title" + i, "description" + i, "time" + i);
			eventList.add(thisEvent);
		}
		IDataStore storageTest = new FileLocalStore("data/dummy.json");
		storageTest.saveData(eventList);

		ArrayList<Event> loadedEventList = storageTest.loadData();
		if (eventList.size() == loadedEventList.size()) {
			for (Event event : eventList) {
				if (!loadedEventList.contains(event)) {
					System.err.println("Loaded event list is missing " + event);
					break;
				}
			}
		}
		else {
			System.err.println("Loaded event list did not have the same number of elements"
					+ " as the original");
		}

		// Now test the backup
		IDataStore backupTest = new FileLocalStore(((FileLocalStore)storageTest).getBackupFileName());
		
		loadedEventList = backupTest.loadData();
		
		if (!loadedEventList.isEmpty()) {
			System.err.println("Backup was not empty before saving");
		}
		
		storageTest.saveData(new ArrayList<>());
		loadedEventList = backupTest.loadData();

		if (eventList.size() == loadedEventList.size()) {
			for (Event event : eventList) {
				if (!loadedEventList.contains(event)) {
					System.err.println("Loaded event list is missing " + event);
					break;
				}
			}
		}
		else {
			System.err.println("Loaded event list did not have the same number of elements"
					+ " as the original");
		}
    }

}
