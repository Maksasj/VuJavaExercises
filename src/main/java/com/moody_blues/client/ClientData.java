package com.moody_blues.client;

import com.moody_blues.common.Room;

import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class ClientData {
    private Socket socket;
    private String username;
    private ArrayList<Room> rooms;

    public ClientData(Socket socket, String username) {
        this.username = username;
        this.socket = socket;

        this.rooms = new ArrayList<>();
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

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }

    synchronized public void addRooms(ArrayList<Room> roomsToAdd) {
        rooms.removeAll(roomsToAdd);
        rooms.addAll(roomsToAdd);
    }
}
