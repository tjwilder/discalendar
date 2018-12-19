package com.github.khalory;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.NoSuchFileException;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileLocalStore implements IDataStore {
    private static final String DEFAULT_FILE_NAME = "data/data.json";
	
	private final String FILE_NAME;

	public FileLocalStore() {
		this(DEFAULT_FILE_NAME);
	}

	public FileLocalStore(String fileName) {
		FILE_NAME = fileName;
	}

	// Suppress warnings for generic HashMap and ArrayList
    @SuppressWarnings("unchecked")
    public void saveData(ArrayList<Event> eventList) {
		if (!backupData()) {
			BotUtils.log("Data backup failed, aborting saveData");
			return;
		}

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
			if (MainRunner.DEBUG) {
				BotUtils.log("Saved events");
			}
        }
        catch (IOException e) {
            String errorLog = "";
            errorLog += "ERROR: Discalendar failed to save calendar data to local storage with Exception\n";
            BotUtils.log(errorLog, e);
        }
    }

	private boolean backupData() {
		try {
			Path source = Paths.get(FILE_NAME);
			Path newDir = Paths.get(getBackupFileName());
			Files.move(source, newDir, StandardCopyOption.REPLACE_EXISTING); // Replace existing backup
		}
		catch (NoSuchFileException e) {
			return true; // If original file doesn't exist, ignore the backup
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
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

	public String getFileName() {
		return FILE_NAME;
	}

	public String getBackupFileName() {
		return FILE_NAME + ".bak";
	}
}
