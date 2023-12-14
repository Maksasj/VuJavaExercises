/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.client;

import com.moody_blues.common.Room;

import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class ClientData {
    private Socket socket;
    private String username;
    private ArrayList<Room> rooms;
    private ArrayList<Room> privateRooms;
    private ArrayList<String> onlineUserNames;

    synchronized public void setOnlineUserNames(ArrayList<String> onlineUserNames) {
        this.onlineUserNames = onlineUserNames;
    }

    synchronized public ArrayList<String> getOnlineUserNames() {
        return onlineUserNames;
    }

    public ClientData(Socket socket, String username) {
        this.username = username;
        this.socket = socket;

        this.rooms = new ArrayList<>();
        this.privateRooms = new ArrayList<>();
        this.onlineUserNames = new ArrayList<>();
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

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Room> getPrivateRooms() {
        return privateRooms;
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

    synchronized public void addPrivateRooms(ArrayList<Room> roomsToAdd) {
        privateRooms.removeAll(roomsToAdd);
        privateRooms.addAll(roomsToAdd);
    }

    synchronized public void addPrivateRoom(Room roomToAdd) {
        privateRooms.remove(roomToAdd);
        privateRooms.add(roomToAdd);
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
