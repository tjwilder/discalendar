package com.github.khalory.command;

import com.github.khalory.BotUtils;
import com.github.khalory.ServerHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;


public class ChangePrefixCommand implements ICommand {

    public void runCommand(MessageReceivedEvent messagePrefix, List<String> args){
        // Make sure the new prefix is only one word long.
        if (args.size() != 1) {
            HelpCommand.prefixHelpMessage(messagePrefix);
            return;
        }
        // TODO test
        System.out.println("fail");
        Long callerID = messagePrefix.getGuild().getLongID();

        ServerHandler.changePrefix(callerID, args.get(0).trim());

        BotUtils.sendMessage(messagePrefix.getChannel(), "Prefix has been updated to: " + args.get(0).trim());
    }
}
