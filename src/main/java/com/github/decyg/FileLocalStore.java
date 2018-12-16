package com.github.decyg;

import sx.blah.discord.handle.obj.IChannel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

            obj.put("events", data);

			file.write(obj.toJSONString());
			System.out.println("Saved events");
        }
        catch (IOException e) {
            BotUtils.log("ERROR: Discalendar failed to save calendar data to local storage with Exception\n"
                + e.getMessage());
        }
    }

    public ArrayList<Event> loadData() {
		ArrayList<Event> events = new ArrayList<Event>();

        JSONParser parser = new JSONParser();

		try {
			JSONObject data = (JSONObject) parser.parse(new FileReader(FILE_NAME));

			JSONArray eventData = (JSONArray) data.get("events");

			for (Object ev : eventData) {
				JSONObject e = (JSONObject) ev;

				String name = (String) e.get("title");
				String description = (String) e.get("description");
				String timestamp = (String) e.get("timestamp");

				Event event = new Event(name, description, timestamp);
				events.add(event);
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return events;
    }
}
