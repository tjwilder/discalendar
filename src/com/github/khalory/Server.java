package com.github.khalory;

  // Basic storage for info that might be different based on a server's layout and whims

public class Server {
    Long serverID;
    String serverPrefix;
    Long announcementChannel;
    public Server(long newServerID){
        serverID = newServerID;
    }

    public Long getID() { return serverID; }
    public String getPrefix() { return serverPrefix; }
    public Long getAnnouncementChannel() { return announcementChannel; }

    public void setPrefix(String newPrefix) { serverPrefix = newPrefix; }
    public void setAnnouncements(Long newAnnouncements) { announcementChannel = newAnnouncements; }

}
