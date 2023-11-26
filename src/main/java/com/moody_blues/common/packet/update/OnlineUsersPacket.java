package com.moody_blues.common.packet.update;

import com.moody_blues.common.packet.DataPacket;

import java.util.ArrayList;

public class OnlineUsersPacket extends DataPacket {
    private ArrayList<String> names;

    public ArrayList<String> getNames() {
        return names;
    }

    public OnlineUsersPacket(ArrayList<String> names) {
        this.names = names;
    }
}
