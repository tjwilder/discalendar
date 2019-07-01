package com.github.khalory;

  // Basic storage form that needs to be different unique from server to server

public class Server {
    final Long SERVER_ID;
    String serverPrefix;
    Long announcementChannel;
    public Server(long serverID){
        SERVER_ID = serverID;
    }

    public Long getID() { return SERVER_ID; }
    public String getPrefix() { return serverPrefix; }
    public Long getAnnouncementChannel() { return announcementChannel; }

    public void setPrefix(String newPrefix) { serverPrefix = newPrefix; }
    public void setAnnouncements(Long newAnnouncements) { announcementChannel = newAnnouncements; }

}
