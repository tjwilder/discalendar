package com.github.decyg;

import sx.blah.discord.api.IDiscordClient;

import java.util.ArrayList;

/**
 * Created by declan on 03/04/2017.
 */
public class MainRunner {

    public static final boolean DEBUG = false;

    public static IDiscordClient client;

    public static void main(String[] args) {

        if (DEBUG == false) {
            if (args.length != 1) {
                System.out.println("Please enter the bots token as the first argument" +
                        "e.g java -jar thisjar.jar tokenhere");
                return;
            }

            IDiscordClient cli = BotUtils.getBuiltDiscordClient(args[0]);
            client = cli;

            // Register a listener via the EventSubscriber annotation which
            // allows for organisation and delegation of events
            cli.getDispatcher().registerListener(new CommandHandler());

            // Only login after all events are registered otherwise some may be missed.
            cli.login();
        }
        else {
            // Test Event.java
            String debugTitle = "basicTitle";
            String debugDesscription = "extra Long Description";
            String debugTime = "8767896";
            Event testEvent = new Event(debugTitle, debugDesscription, debugTime);
            if (!testEvent.getTitle().equals(debugTitle))
                System.err.println("Title is not saved and loaded properly by Event");
            if (!testEvent.getDescription().equals(debugDesscription))
                System.err.println("Description is not saved and loaded properly by Event");
            if (!testEvent.getTime().equals(debugTime))
                System.err.println("Time is not saved and loaded properly by Event");
            testEvent.getTitle();
            // TODO: Test IDataStore - FileLocalStore.java
            //   Create a list of events to save into the data file
            ArrayList<Event> eventList = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                Event thisEvent = new Event("title" + i, "description" + i, "time" + i);
                eventList.add(thisEvent);
            }
            IDataStore storageTest = new FileLocalStore();
            storageTest.saveData(eventList);

        }

    }

}
