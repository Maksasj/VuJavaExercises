/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common.packet.update;

import com.moody_blues.common.Room;
import com.moody_blues.common.packet.DataPacket;

import java.util.ArrayList;

public class UpdatePrivateRoomListPacket extends DataPacket {
    private final ArrayList<Room> rooms;

    public UpdatePrivateRoomListPacket(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
