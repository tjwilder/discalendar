package com.github.khalory;

import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.impl.events.guild.GuildLeaveEvent;

import java.util.ArrayList;

// TODO: Server Join: Create a ServerProfile object.
// TODO: Periodically: save data using FileLocalStore.java
// TODO: bot prefix requested: change or reply with correct bot prefix.
// TODO: Announcement channel request: change or reply with the correct announcement channel


public class ServerHandler {

    static ArrayList<com.github.khalory.Server> allServers = new ArrayList();
    //Servers can be stored in a list for now. A binary search tree would be better.

    public static void serverStartup() {

        // TODO: Bot start: Load server information from LocalFileStore into allServers array list.
    }

    // TODO: completely untested method.
    public static void changePrefix(Long serverID, String newPrefix) {
        for (int i = 1; i <= allServers.size(); i++) {
            Server currentServer = allServers.get(i);
            if (currentServer.getID().equals(serverID)){
                currentServer.setPrefix(newPrefix);
                allServers.set(i, currentServer);
                return;
            }
        }
    }

    // TODO: only tested with empty arrarylist.
    public static String checkPrefix(Long serverID) {
        for (int i = 1; i <= allServers.size(); i++) {
            Server currentServer = allServers.get(i);
            if (currentServer.getID().equals(serverID)) {
                return currentServer.getPrefix();
            }
        }
        return BotUtils.BOT_PREFIX;
    }
}
