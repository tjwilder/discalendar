package com.github.decyg;

import sx.blah.discord.handle.obj.IChannel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileLocalStore implements IDataStore {
    private String fileName = "data.txt";
    public void saveData(ArrayList<Event> eventList) {
        try {
            PrintWriter pw = new PrintWriter(new File(fileName));
            for (Event saveEvent : eventList) {
                pw.println(saveEvent.getTitle() + "" + saveEvent.getDescription() + saveEvent.getTime());
            }
        }
        catch (FileNotFoundException e) {
            IChannel channel = MainRunner.client.getChannelByID(392512963916857347L);
            BotUtils.sendMessage(channel, "ERROR: Discalendar failed to save calendar data to cloud storage");
        }
    }

    public ArrayList<Event> loadData() {
        return null;
    }
}
