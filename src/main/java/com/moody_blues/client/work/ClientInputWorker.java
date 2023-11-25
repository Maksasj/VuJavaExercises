package com.moody_blues.client.work;

import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.common.packet.update.UpdateRoomDataPacket;
import com.moody_blues.common.packet.update.UpdateRoomListPacket;
import com.moody_blues.server.ClientInstance;

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
                var dataPacket = inputStream.readObject();

                if(dataPacket instanceof UpdateRoomListPacket) {
                    var packet = (UpdateRoomListPacket) dataPacket;
                    var rooms = packet.getRooms();

                    client.addRooms(rooms);

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
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
