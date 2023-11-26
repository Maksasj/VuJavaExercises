package com.moody_blues.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Room implements Serializable {
    private final String roomName;
    private final UUID roomUUID;
    private ArrayList<Message> messages;
    private RoomType type;

    public RoomType getType() {
        return type;
    }

    public Room(String roomName, RoomType type) {
        messages = new ArrayList<>();

        this.roomUUID = UUID.randomUUID();
        this.roomName = roomName;
        this.type = type;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public UUID getRoomUUID() {
        return roomUUID;
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

    @Override
    public String toString() {
        return roomName;
    }
}
