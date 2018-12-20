package com.github.khalory;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Created by declan on 03/04/2017.
 */
public class BotUtils {

    // Constants for use throughout the bot
    public static final String BOT_PREFIX = "/";

    // Handles the creation and getting of a IDiscordClient object for a token
    protected static IDiscordClient getBuiltDiscordClient(String token) {
        // The ClientBuilder object is where you will attach your params for configuring the instance of your bot.
        // Such as withToken, setDaemon etc
        return new ClientBuilder()
                .withToken(token)
                .withRecommendedShardCount()
                .build();

    }

    // Helper functions to make certain aspects of the bot easier to use.
    protected static void sendMessage(IChannel channel, String message) {
        // Buffer a message request
        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });

    }

	// Reports some message to the  Discord channel of the bot creator
    protected static void report(String message) {
		IChannel channel = MainRunner.client.getChannelByID(392512963916857347L);
		sendMessage(channel, message);
    }
}
