package com.github.khalory;

public class Event {
    final String TITLE;
    final String DESCRIPTION;
    final String TIME;
    public Event(String title, String description, String time) {
        TITLE = title;
        DESCRIPTION = description;
        TIME = time;
    }
    public String getTitle() { return TITLE; }
    public String getDescription() { return DESCRIPTION; }
    public String getTime() { return TIME; }

	public String toString() {
		return String.format("(%s, %s, %s)", TITLE, DESCRIPTION, TIME);
	}

	public boolean equals(Object o) {
		Event e = (Event) o;

		return TITLE.equals(e.TITLE)
			&& DESCRIPTION.equals(e.DESCRIPTION)
			&& TIME.equals(e.TIME);
	}
}
