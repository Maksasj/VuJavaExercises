/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common;

import java.util.ArrayList;

public class PrivateRoom extends Room {
    private ArrayList<String> users;

    public PrivateRoom(ArrayList<String> users, String roomName, RoomType type) {
        super(roomName, type);

        this.users = users;
    }

    public ArrayList<String> getUsers() {
        return users;
    }
}
