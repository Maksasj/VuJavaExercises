/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common.packet.update;

import com.moody_blues.common.Message;
import com.moody_blues.common.packet.DataPacket;

import java.util.UUID;

public class SendPrivateMessagePacket extends DataPacket {
    private UUID roomUUID;
    private Message message;

    public SendPrivateMessagePacket(UUID roomUUID, Message message) {
        this.roomUUID = roomUUID;
        this.message = message;
    }

    public UUID getRoomUUID() {
        return roomUUID;
    }

    public Message getMessage() {
        return message;
    }
}
