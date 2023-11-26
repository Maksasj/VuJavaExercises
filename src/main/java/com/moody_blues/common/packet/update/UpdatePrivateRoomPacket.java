package com.moody_blues.common.packet.update;

import com.moody_blues.common.Room;
import com.moody_blues.common.packet.DataPacket;

import java.util.ArrayList;

public class UpdatePrivateRoomPacket extends DataPacket {
    private final Room room;

    public UpdatePrivateRoomPacket(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}
