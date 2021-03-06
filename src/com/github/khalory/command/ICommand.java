package com.github.khalory.command;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

/**
 * Created by declan on 04/04/2017.
 */
public interface ICommand {

    // Interface for a command to be implemented in the command map
    void runCommand(MessageReceivedEvent event, List<String> args);

}
