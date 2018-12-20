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
        // TODO: Parse arguments for title, description, time.

        // TODO: If parse is correct, save into an event. Print confirmation to channel

        // TODO: If parse is incorrect, print indication of correct message format
		
		// If the args aren't correct, pretend the user entered the help command for createEvent
		if (args.size() != 3) {
			getHelp(messageEvent);
			return;
		}

		// Presumably the arguments at this point are exactly [title, description, time]
		String title       = args.get(0);
		String description = args.get(1);
		String timeString  = args.get(2);

		// The title and description are fine as-is, but we need to parse the
		// timeString properly. It must be like 12-25-19-23:59
		String[] regexSplit = timeString.split("[:/-]");
		if (regexSplit.length != 5) {
			getHelp(messageEvent);
			return;
		}
		timeString = String.join("-", regexSplit);
		
		Date date = null;

		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-hh-mm");
			date = dateFormat.parse(timeString);
		}
		catch (Exception e) {
			Logger.debug("Failed to parse with exception", e);
			getHelp(messageEvent);
			return;
		}
		
		String time = date.toString(); // parsed time string

		Event event = new Event(title, description, time);	
		Logger.info("Created event " + event.toString());
		// TODO: Add Event to EventHandler
		
		// TODO: Acknowledge receipt in Discord
		messageEvent.getMessage().reply("Proper event received! Events cannot currently save...");
    }

	private void getHelp(MessageReceivedEvent messageEvent) {
		// Another option
		messageEvent.getMessage().reply("Please format your message: /createEvent 'Event Title'; 'event description';" +
				" 04/18/2018 21:35\n Your message was: " + messageEvent.getMessage());
        // TODO: trim event pieces and check for empty strings.
        // TODO: If parse is correct, save into an event. Print confirmation to channel

		// Also send the help message?
		// List<String> helpArgs = new ArrayList<>();
		// helpArgs.add("createEvent");
		// (new HelpCommand()).runCommand(messageEvent, helpArgs);
	}
}
