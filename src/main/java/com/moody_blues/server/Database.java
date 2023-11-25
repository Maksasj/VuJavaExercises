package com.moody_blues.server;

import com.moody_blues.common.Room;
import com.moody_blues.common.RoomType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
    synchronized public void createRoom(String roomName, RoomType type) {
        rooms.add(new Room(roomName, type));
    }

    synchronized public ArrayList<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    synchronized public Room getRoom(String roomName) {
        for(var room : rooms)
            if(room.getRoomName().contentEquals(roomName))
                return room;

        return null;
    }

    synchronized public Room getRoom(UUID roomUUID) {
        for(var room : rooms)
            if(room.getRoomUUID().compareTo(roomUUID) == 0)
                return room;

        return null;
    }
}
