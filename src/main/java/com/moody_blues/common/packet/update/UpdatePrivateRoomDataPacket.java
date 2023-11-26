package com.moody_blues.common.packet.update;

import com.moody_blues.common.Message;
import com.moody_blues.common.packet.DataPacket;

import java.util.ArrayList;
import java.util.UUID;

public class UpdatePrivateRoomDataPacket extends DataPacket {
    private final UUID roomUUID;
    private final ArrayList<Message> messages;

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public UUID getRoomUUID() {
        return roomUUID;
    }

    public UpdatePrivateRoomDataPacket(UUID roomUUID, ArrayList<Message> messages) {
        this.roomUUID = roomUUID;
        this.messages = messages;
    }
}
