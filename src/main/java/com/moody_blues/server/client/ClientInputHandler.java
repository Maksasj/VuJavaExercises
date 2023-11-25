package com.moody_blues.server.client;

import com.moody_blues.common.Logger;
import com.moody_blues.common.Message;
import com.moody_blues.common.RoomType;
import com.moody_blues.common.packet.request.RequestRoomListPacket;
import com.moody_blues.common.packet.update.*;
import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.Database;
import com.moody_blues.server.MoodyBluesServer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
                    Logger.log("Received username update packet (username: " + clientInstance.getUsername() + ")");

                    clientInstance.setUsername(((UsernameUpdatePacket) packet).getUsername());
                }

                if(packet instanceof CreateRoomPacket) {
                    String roomName = ((CreateRoomPacket) packet).getRoomName();

                    Logger.log("Received create room packet, creating new room (room name: " + roomName + ")");

                    if(!MoodyBluesServer.database.isRoomExist(roomName))
                        MoodyBluesServer.database.createRoom(roomName, RoomType.PUBLIC);

                    MoodyBluesServer.sendPacketToEachClient(new UpdateRoomListPacket(database.getRooms()));
                }

                if(packet instanceof RequestRoomListPacket) {
                    Logger.log("Received update room list packet");

                    var outputHandler = clientInstance.getOutputHandler();

                    outputHandler.queuePacket(new UpdateRoomListPacket(database.getRooms()));
                }

                if(packet instanceof SendMessagePacket) {
                    Logger.log("Received send message packet");

                    var message = ((SendMessagePacket) packet).getMessage();
                    var roomUUID = ((SendMessagePacket) packet).getRoomUUID();

                    var room = MoodyBluesServer.database.getRoom(roomUUID);
                    if(room == null) {
                        Logger.log("Error: '" + roomUUID.toString() + "' room does not exist");
                        continue;
                    }

                    room.addMessage(message);

                    var messages = new ArrayList<>(room.getMessages());

                    MoodyBluesServer.sendPacketToEachClient(new UpdateRoomDataPacket(room.getRoomUUID(), messages));
                }
            }

            inputStream.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
