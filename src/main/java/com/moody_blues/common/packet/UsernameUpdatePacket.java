package com.moody_blues.common.packet;

public class UsernameUpdatePacket extends DataPacket {
    private String username;

    public UsernameUpdatePacket(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
