/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common.packet.update;

import com.moody_blues.common.packet.DataPacket;

public class CreateRoomPacket extends DataPacket {
    private final String roomName;

    public CreateRoomPacket(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }
}
