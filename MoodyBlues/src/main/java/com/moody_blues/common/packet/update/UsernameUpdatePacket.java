/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common.packet.update;

import com.moody_blues.common.packet.DataPacket;

public class UsernameUpdatePacket extends DataPacket {
    private String username;

    public UsernameUpdatePacket(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
