package com.github.decyg;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.util.List;

public class CreateEventCommand implements ICommand {
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        // Parse arguments for title, description, time.
        // If parse is incorrect, print indication of correct message format
        if (args.size() != 2) {
            event.getMessage().reply("Please format your message: /createEvent 'Event Title'; 'event description';" +
                    " 04/18/2018 21:35\n Your message was: " + event.getMessage());
        }
        // TODO: trim event pieces and check for empty strings.
        // TODO: If parse is correct, save into an event. Print confirmation to channel
        else {
            event.getMessage().reply("Proper event received! Events cannot currently save...");
            // TODO: check that time is properly formatted
            // TODO: create event object
            // TODO: add event object to event array list
        }

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
}
