package com.github.khalory;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Created by declan on 03/04/2017.
 */
class BotUtils {

    // Constants for use throughout the bot
    static String BOT_PREFIX = "/";

    // Handles the creation and getting of a IDiscordClient object for a token
    protected static IDiscordClient getBuiltDiscordClient(String token){

        // The ClientBuilder object is where you will attach your params for configuring the instance of your bot.
        // Such as withToken, setDaemon etc
        return new ClientBuilder()
                .withToken(token)
                .withRecommendedShardCount()
                .build();

    }

    // Helper functions to make certain aspects of the bot easier to use.
    protected static void sendMessage(IChannel channel, String message){

        // This might look weird but it'll be explained in another page.
        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });

    }

	protected static void debug(String message) {
		if (MainRunner.DEBUG)
			log(message);
	}

    protected static void log(String message, Exception e) {
        message += e.getMessage() + "\nStack trace:";
        for (StackTraceElement stackPiece : e.getStackTrace()) {
            message += "\n" + stackPiece.toString();
        }
        log(message);
    }

    protected static void log(String message) {
        if (!MainRunner.DEBUG) {
            IChannel channel = MainRunner.client.getChannelByID(392512963916857347L);
            sendMessage(channel, message);
        }
        else {
            System.err.println(message);
        }
    }
}
