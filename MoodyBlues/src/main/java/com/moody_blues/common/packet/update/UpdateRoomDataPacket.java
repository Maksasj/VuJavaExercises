/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common.packet.update;

import com.moody_blues.common.Message;
import com.moody_blues.common.Room;
import com.moody_blues.common.packet.DataPacket;

import java.util.ArrayList;
import java.util.UUID;

public class UpdateRoomDataPacket extends DataPacket {
    private final UUID roomUUID;
    private final ArrayList<Message> messages;

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public UUID getRoomUUID() {
        return roomUUID;
    }

    public UpdateRoomDataPacket(UUID roomUUID, ArrayList<Message> messages) {
        this.roomUUID = roomUUID;
        this.messages = messages;
    }
}
