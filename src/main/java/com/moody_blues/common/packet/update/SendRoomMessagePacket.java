package com.moody_blues.common.packet.update;

import com.moody_blues.common.Message;
import com.moody_blues.common.packet.DataPacket;

public class SendRoomMessagePacket extends DataPacket {
    private String roomName;
    private Message message;

    public SendRoomMessagePacket(String roomName, Message message) {
        this.roomName = roomName;
        this.message = message;
    }

    public String getRoomName() {
        return roomName;
    }

    public Message getMessage() {
        return message;
    }
}
