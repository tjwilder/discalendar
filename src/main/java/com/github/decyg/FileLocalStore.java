package com.github.decyg;

import sx.blah.discord.handle.obj.IChannel;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FileLocalStore implements IDataStore {
    private static final String FILE_NAME = "data/data.json";
    private static final String SECONDARY_FILE_NAME = "data/backup_data.json";

    @SuppressWarnings("unchecked")
    public void saveData(ArrayList<Event> eventList) {
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            JSONObject obj = new JSONObject();
            JSONObject eventObj;

            JSONArray data = new JSONArray();
            for (Event event : eventList) {
                eventObj = new JSONObject();
                eventObj.put("title", event.getTitle());
                eventObj.put("description", event.getDescription());
                eventObj.put("timestamp", event.getTime());
                data.add(eventObj);
            }

            obj.put("data", data);

            file.write(obj.toJSONString());
            System.out.println("Saved events");
        }
        catch (IOException e) {
            String errorLog = "";
            errorLog += "ERROR: Discalendar failed to save calendar data to local storage with Exception\n";
            BotUtils.log(errorLog, e);
        }
    }

    public ArrayList<Event> loadData() {
        return null; // TODO: load the data
    }
}