package com.moody_blues.common.packet;

public class CreateRoomPacket extends DataPacket {
    private String roomName;

    public CreateRoomPacket(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }
}
