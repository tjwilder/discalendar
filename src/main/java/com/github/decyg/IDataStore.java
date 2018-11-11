package com.github.decyg;

import java.util.ArrayList;

public interface IDataStore {
    void saveData(ArrayList<Event> eventList);
    ArrayList<Event> loadData();
}
