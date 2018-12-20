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

        /*EmbedBuilder builder = new EmbedBuilder();

        builder.appendField("fieldTitleInline", "fieldContentInline", true);
        builder.appendField("fieldTitleInline2", "fieldContentInline2", true);
        builder.appendField("fieldTitleNotInline", "fieldContentNotInline", false);
        builder.appendField(":tada: fieldWithCoolThings :tada:", "[hiddenLink](http://i.imgur.com/Y9utuDe.png)", false);

        builder.withAuthorName("authorName");
        builder.withAuthorIcon("http://i.imgur.com/PB0Soqj.png");
        builder.withAuthorUrl("http://i.imgur.com/oPvYFj3.png");

        builder.withColor(255, 0, 0);
        builder.withDesc("withDesc");
        builder.withDescription("withDescription");
        builder.withTitle("withTitle");
        builder.withTimestamp(100);
        builder.withUrl("http://i.imgur.com/IrEVKQq.png");
        builder.withImage("http://i.imgur.com/agsp5Re.png");

        builder.withFooterIcon("http://i.imgur.com/Ch0wy1e.png");
        builder.withFooterText("footerText");
        builder.withFooterIcon("http://i.imgur.com/TELh8OT.png");
        builder.withThumbnail("http://www.gstatic.com/webp/gallery/1.webp");

        builder.appendDesc(" + appendDesc");
        builder.appendDescription(" + appendDescription");

        RequestBuffer.request(() -> event.getChannel().sendMessage(builder.build()));*/

    }

	private void getHelp(MessageReceivedEvent messageEvent) {
		List<String> helpArgs = new ArrayList<>();
		helpArgs.add("createEvent");
		(new HelpCommand()).runCommand(messageEvent, helpArgs);
	}
}
