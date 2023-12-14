/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.client.work;

import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.common.Logger;
import com.moody_blues.common.packet.DataPacket;
import com.moody_blues.common.packet.update.*;
import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.MoodyBluesServer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientInputWorker implements Runnable {
    private final ObjectInputStream inputStream;

    public ClientInputWorker() throws IOException {
        var socket = MoodyBluesClient.getClientData().getSocket();

        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        var client = MoodyBluesClient.getClientData();

        while(MoodyBluesClient.isRunnning()) {
            try {
                DataPacket dataPacket = null;

                try {
                    dataPacket = (DataPacket) inputStream.readObject();
                } catch (Exception ex) {
                    Logger.log("Closing input connection");
                    continue;
                }

                if(dataPacket instanceof UpdateRoomListPacket) {
                    var packet = (UpdateRoomListPacket) dataPacket;
                    var rooms = packet.getRooms();

                    client.addRooms(rooms);

                    MoodyBluesClient.onAnyUpdate();
                }

                if(dataPacket instanceof UpdatePrivateRoomListPacket) {
                    var packet = (UpdateRoomListPacket) dataPacket;
                    var rooms = packet.getRooms();

                    client.addPrivateRooms(rooms);

                    MoodyBluesClient.onAnyUpdate();
                }

                if(dataPacket instanceof UpdateRoomDataPacket) {
                    var packet = (UpdateRoomDataPacket) dataPacket;

                    var room = client.getRoom(packet.getRoomUUID());
                    if(room == null)
                        continue;

                    room.setMessages(packet.getMessages());

                    MoodyBluesClient.onAnyUpdate();
                }

                if(dataPacket instanceof UpdatePrivateRoomDataPacket) {
                    var packet = (UpdatePrivateRoomDataPacket) dataPacket;

                    var room = client.getPrivateRoom(packet.getRoomUUID());
                    if(room == null)
                        continue;

                    room.setMessages(packet.getMessages());

                    MoodyBluesClient.onAnyUpdate();
                }

                if(dataPacket instanceof OnlineUsersPacket) {
                    var packet = (OnlineUsersPacket) dataPacket;

                    var names = packet.getNames();

                    client.setOnlineUserNames(names);
                    MoodyBluesClient.onAnyUpdate();
                }

                if(dataPacket instanceof UpdatePrivateRoomPacket) {
                    var packet = (UpdatePrivateRoomPacket) dataPacket;

                    var room = packet.getRoom();

                    client.addPrivateRoom(room);

                    MoodyBluesClient.onAnyUpdate();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
