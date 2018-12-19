package com.github.khalory;

import java.util.ArrayList;

public interface IDataStore {
    void saveData(ArrayList<Event> eventList);
    ArrayList<Event> loadData();
}
