package com.github.decyg;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class DeleteEventCommand implements ICommand {
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        // TODO: check that there a properly formatted title in the args.
        // TODO: if the format is wrong, send the correct format to the channel.

        // TODO: check that the title corresponds to an existing event.
        // TODO: if the event exists, delete it and send confirmation to the channel.
        // TODO: if the event does not exist, send an error message to the channel.

    }
}
