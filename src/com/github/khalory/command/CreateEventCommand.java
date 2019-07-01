package com.github.khalory.command;

import com.github.khalory.Event;
import com.github.khalory.Logger;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class CreateEventCommand implements ICommand {
    public void runCommand(MessageReceivedEvent messageEvent, List<String> args) {

        // TODO: If parse is correct, save into an event. Print confirmation to channel

        // TODO: If parse is incorrect, print indication of correct message format

        // If the args aren't correct, pretend the user entered the help command for createEvent
        if (args.size() != 3) {
            getHelp(messageEvent);
//			System.out.println("fail at line 26"); // for testing
//			for (String itemized : args) {
//				System.out.println(itemized);
//			}
            return;
        }

        // Presumably the arguments at this point are exactly [title, description, time]
        String title = args.get(0).trim();
//		System.out.println(title); // for testing
        String description = args.get(1).trim();
        String timeString = args.get(2).trim();

        // The title and description can be any string, but we need to parse the
        // timeString properly. It must be like 02-25-19-23:59
        String[] regexSplit = timeString.split("[:/ .-]");
        if (regexSplit.length != 5) {
            getHelp(messageEvent);
//			System.out.println("Failure on line 43. Problematic section: " + timeString);
            return;
        }
        timeString = String.join("-", regexSplit);

        Date date = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-hh-mm");
            date = dateFormat.parse(timeString);
        } catch (Exception e) {
            Logger.debug("Failed to parse event date with exception", e);
            getHelp(messageEvent);
            return;
        }

        String time = date.toString(); // parsed time string

        long serverID = messageEvent.getGuild().getLongID();

        Event event = new Event(title, description, time, serverID);
        Logger.info("Created event " + event.toString());
        // TODO: Add Event to EventHandler
        // TODO: Acknowledge receipt in Discord
        messageEvent.getMessage().reply("Proper event received! Events cannot currently save...");
    }

    // TODO: trim event pieces and check for empty strings.
    // TODO: If parse is correct, save into an event. Print confirmation to channel

    private void getHelp(MessageReceivedEvent messageEvent) {
        messageEvent.getMessage().reply("Please format your message: /createEvent 'Event Title'; 'event description';" +
                " 04/18/2018 21:35\n Your message was: " + messageEvent.getMessage());
    }
    /* Another option:
     * Also send the help message?
     * List<String> helpArgs = new ArrayList<>();
     * helpArgs.add("createEvent");
     * (new HelpCommand()).runCommand(messageEvent, helpArgs);
     */
}