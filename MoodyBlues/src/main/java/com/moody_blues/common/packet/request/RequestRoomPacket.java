/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common.packet.request;

import com.moody_blues.common.packet.DataPacket;

public class RequestRoomPacket extends DataPacket {
    private String roomName;

    public RequestRoomPacket(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }
}
