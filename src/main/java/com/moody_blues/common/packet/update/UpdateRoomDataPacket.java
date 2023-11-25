package com.moody_blues.common.packet.update;

import com.moody_blues.common.Message;
import com.moody_blues.common.Room;
import com.moody_blues.common.packet.DataPacket;

import java.util.ArrayList;

public class UpdateRoomDataPacket extends DataPacket {
    private final Room room;

    public Room getRoom() {
        return room;
    }

    public UpdateRoomDataPacket(Room room) {
        this.room = room;
    }
}
