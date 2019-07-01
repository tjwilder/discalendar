package com.github.khalory;

public class Event {
    final String TITLE;
    final String DESCRIPTION;
    final String TIME;
    final Long SERVER_ID;
    public Event(String title, String description, String time, Long serverID) {
        TITLE = title;
        DESCRIPTION = description;
        TIME = time;
        SERVER_ID = serverID;
    }
    public String getTitle() { return TITLE; }
    public String getDescription() { return DESCRIPTION; }
    public String getTime() { return TIME; }
    public Long getServerID() { return SERVER_ID; }

	public String toString() {
		return String.format("(%s, %s, %s, %s)", TITLE, DESCRIPTION, TIME, SERVER_ID);
	}

	public boolean equals(Object o) {
        Event e = (Event) o;

        return TITLE.equals(e.TITLE)
			&& DESCRIPTION.equals(e.DESCRIPTION)
			&& TIME.equals(e.TIME)
            && SERVER_ID.equals(e.SERVER_ID);
	}
}
