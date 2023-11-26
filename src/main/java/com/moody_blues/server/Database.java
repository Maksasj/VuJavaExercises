package com.moody_blues.server;

import com.moody_blues.common.PrivateRoom;
import com.moody_blues.common.Room;
import com.moody_blues.common.RoomType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Database implements Serializable {
    private ArrayList<Room> rooms;
    private transient ArrayList<Room> privateRooms;

    public Database() {
        this.rooms = new ArrayList<>();
        this.privateRooms = new ArrayList<>();
    }

    public void initPrivateRooms() {
        privateRooms = new ArrayList<>();
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

    synchronized public Room getPrivateRoom(UUID roomUUID) {
        for(var room : privateRooms)
            if(room.getRoomUUID().compareTo(roomUUID) == 0)
                return room;

        return null;
    }

    synchronized public boolean isPrivateRoomExist(String roomName) {
        for(var room : privateRooms)
            if(room.getRoomName().contentEquals(roomName))
                return true;

        return false;
    }

    synchronized public Room createPrivateRoom(ArrayList<String> users, String roomName, RoomType type) {
        var room = new PrivateRoom(users, roomName, type);
        privateRooms.add(room);
        return room;
    }
}
