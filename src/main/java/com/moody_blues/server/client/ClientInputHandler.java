package com.moody_blues.server.client;

import com.moody_blues.common.*;
import com.moody_blues.common.packet.request.RequestRoomListPacket;
import com.moody_blues.common.packet.update.*;
import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.Database;
import com.moody_blues.server.MoodyBluesServer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;

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
                Object packet = null;

                try {
                    packet = inputStream.readObject();
                } catch (Exception ex) {
                    Logger.log("Failed to receive packet from client, closing connection");
                    clientInstance.closeConnection();
                    break;
                }

                if(packet instanceof UsernameUpdatePacket) {
                    Logger.log("Received username update packet (username: " + clientInstance.getUsername() + ")");

                    clientInstance.setUsername(((UsernameUpdatePacket) packet).getUsername());

                    var userNames = MoodyBluesServer.getOnlineUsersnames();
                    MoodyBluesServer.sendPacketToEachClient(new OnlineUsersPacket(userNames));
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

                if(packet instanceof CreatePrivateRoomPacket) {
                    var names = ((CreatePrivateRoomPacket) packet).getNames();

                    String roomName = "Private " + names.get(0) + " - " + names.get(1);

                    Logger.log("Received create private room packet, creating new private room (room name: " + roomName + ")");

                    if(!MoodyBluesServer.database.isPrivateRoomExist(roomName)) {
                        var room = MoodyBluesServer.database.createPrivateRoom(names ,roomName, RoomType.PRIVATE);

                        var user1 = MoodyBluesServer.getClientInstance(names.get(0));
                        var user2 = MoodyBluesServer.getClientInstance(names.get(1));

                        if(user1 != null) {
                            var sendRoom = room;

                            user1.getOutputHandler().queuePacket(new UpdatePrivateRoomPacket(sendRoom));
                        }

                        if(user2 != null) {
                            var sendRoom = room;

                            user2.getOutputHandler().queuePacket(new UpdatePrivateRoomPacket(sendRoom));
                        }
                    }
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

                if(packet instanceof SendPrivateMessagePacket) {
                    Logger.log("Received send private message packet");

                    var message = ((SendPrivateMessagePacket) packet).getMessage();
                    var roomUUID = ((SendPrivateMessagePacket) packet).getRoomUUID();

                    var room = (PrivateRoom) MoodyBluesServer.database.getPrivateRoom(roomUUID);
                    if(room == null) {
                        Logger.log("Error: '" + roomUUID.toString() + "' private room does not exist");
                        continue;
                    }

                    room.addMessage(message);

                    var messages = new ArrayList<>(room.getMessages());

                    for(var username : room.getUsers()) {
                        var client = MoodyBluesServer.getClientInstance(username);

                        if(client != null) {
                            client.getOutputHandler().queuePacket(new UpdatePrivateRoomDataPacket(room.getRoomUUID(), messages));
                        }
                    }
                }
            }

            inputStream.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
