package com.moody_blues.server.client;

import com.moody_blues.common.Logger;
import com.moody_blues.common.packet.request.RequestRoomListPacket;
import com.moody_blues.common.packet.update.CreateRoomPacket;
import com.moody_blues.common.packet.update.UpdateRoomListPacket;
import com.moody_blues.common.packet.update.UsernameUpdatePacket;
import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.Database;
import com.moody_blues.server.MoodyBluesServer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientInputHandler implements Runnable {
    private final ClientInstance clientInstance;
    private final ObjectInputStream inputStream;

    public ClientInputHandler(ClientInstance clientInstance) throws IOException {
        clientInstance.setInputHandler(this);
        this.clientInstance = clientInstance;

        var socket = clientInstance.getSocket();

        inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    @Override
    public void run() {
        var database = MoodyBluesServer.database;

        try {
            while (MoodyBluesServer.isRunnning()) {
                if(!clientInstance.online())
                    break;

                var packet = inputStream.readObject();

                if(packet instanceof UsernameUpdatePacket) {
                    clientInstance.setUsername(((UsernameUpdatePacket) packet).getUsername());

                    Logger.log("Received username update packet (username: " + clientInstance.getUsername() + ")");
                }

                if(packet instanceof CreateRoomPacket) {
                    String roomName = ((CreateRoomPacket) packet).getRoomName();

                    if(!MoodyBluesServer.database.isRoomExist(roomName))
                        MoodyBluesServer.database.createRoom(roomName);

                    Logger.log("Received create room packet, creating new room (room name: " + roomName + ")");

                    MoodyBluesServer.sendPacketToEachClient(new UpdateRoomListPacket(database.getRooms()));
                }

                if(packet instanceof RequestRoomListPacket) {
                    var outputHandler = clientInstance.getOutputHandler();

                    outputHandler.queuePacket(new UpdateRoomListPacket(database.getRooms()));

                    Logger.log("Received update room list packet");
                }
            }

            inputStream.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
