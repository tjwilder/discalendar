package com.github.khalory;

import com.github.khalory.command.CommandHandler;

import sx.blah.discord.api.IDiscordClient;

/**
 * Created by declan on 03/04/2017.
 */
public class MainRunner {

    public static final boolean DEBUG = true;

    public static IDiscordClient client;

    public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
			return;
		}

		IDiscordClient cli = BotUtils.getBuiltDiscordClient(args[0]);
		client = cli;

		// Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
		cli.getDispatcher().registerListener(new CommandHandler());

		// Only login after all events are registered otherwise some may be missed.
		cli.login();
    }

}
