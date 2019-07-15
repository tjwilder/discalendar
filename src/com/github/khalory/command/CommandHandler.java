package com.github.khalory.command;

import com.github.khalory.BotUtils;

import com.github.khalory.ServerHandler;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.util.*;

/**
 * Created by declan on 04/04/2017.
 */
public class CommandHandler {

    // A static map of commands mapping from command string to the functional impl
    private static Map<String, ICommand> commandMap = new HashMap<>();

    // Statically populate the commandMap with the intended functionality
    // Might be better practise to do this from an instantiated objects constructor
    static {
        commandMap.put("createevent", new CreateEventCommand());
        commandMap.put("listevents", new ListEventsCommand());
        commandMap.put("deleteevent", new DeleteEventCommand());
        commandMap.put("help", new HelpCommand());
        commandMap.put("changeprefix", new ChangePrefixCommand());
    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {

        // Note for error handling, you'll probably want to log failed commands with a logger or sout
        // In most cases it's not advised to annoy the user with a reply in case they didn't intend to trigger a
        // command anyway, such as a user typing ?notacommand, the bot should not say "notacommand doesn't exist" in
        // most situations. It's partially good practice and partially developer preference
        // NOTE: In Discalendar's case, we are not logging mis-inputted commands.

        String serverPrefix = ServerHandler.checkPrefix(event.getGuild().getLongID());

        // Given a message "/test arg1 arg2", argArray will contain ["/test", "arg1", "arg"]
        String[] argArray = event.getMessage().getContent().split(" ");

        // First ensure at least the command and prefix is present, the arg length can be handled by your command func
        if (argArray.length == 0)
            return;

        // Check if the first arg (the command) starts with the prefix defined in the utils class
        if (!argArray[0].startsWith(serverPrefix))
            return;


        // Extract the "command" part of the first arg out by ditching the amount of characters present in the prefix
        String commandStr = argArray[0].substring(serverPrefix.length()).toLowerCase();
        if(commandMap.containsKey(commandStr)) {
            // Add spaces back into the array. Split on ;
            String messageString = "";
            for (String messageBit : argArray) messageString += (messageBit + " ");
            messageString = messageString.trim();
            argArray = messageString.split(";");

            // Load the rest of the args in the array into a List for safer access
            List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
            // Remove the command without removing the first part of the data
            String removeCommand = argsList.get(0);
            removeCommand = removeCommand.substring(BotUtils.BOT_PREFIX.length() + commandStr.length());
            argsList.set(0, removeCommand);

            // Instead of delegating the work to a switch, automatically do it via calling the mapping if it exists
            commandMap.get(commandStr).runCommand(event, argsList);
        }
    }

}
