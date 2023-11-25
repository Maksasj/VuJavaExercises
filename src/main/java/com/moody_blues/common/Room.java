package com.moody_blues.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Room implements Serializable {
    private final String roomName;
    private final UUID roomUUID;
    private ArrayList<Message> messages;

    public Room(String roomName) {
        messages = new ArrayList<>();

        this.roomUUID = UUID.randomUUID();
        this.roomName = roomName;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public String getRoomName() {
        return roomName;
    }
}
