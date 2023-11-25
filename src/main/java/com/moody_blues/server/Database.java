package com.moody_blues.server;

import com.moody_blues.common.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Database implements Serializable {
    private ArrayList<Room> rooms;

    public Database() {
        this.rooms = new ArrayList<>();
    }

    synchronized public boolean isRoomExist(String roomName) {
        for(var room : rooms)
            if(room.getRoomName().contentEquals(roomName))
                return true;

        return false;
    }
    synchronized public void createRoom(String roomName) {
        rooms.add(new Room(roomName));
    }

    synchronized public ArrayList<Room> getRooms() {
        return new ArrayList<>(rooms);
    }
}
