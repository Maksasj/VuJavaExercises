/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common.packet.update;

import com.moody_blues.common.packet.DataPacket;

import java.util.ArrayList;

public class CreatePrivateRoomPacket extends DataPacket {
    private ArrayList<String> names;

    public ArrayList<String> getNames() {
        return names;
    }

    public CreatePrivateRoomPacket(ArrayList<String> names) {
        this.names = names;
    }
}
