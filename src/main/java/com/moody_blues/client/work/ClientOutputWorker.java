package com.moody_blues.client.work;

import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.common.Logger;
import com.moody_blues.common.packet.DataPacket;
import com.moody_blues.common.packet.request.RequestRoomListPacket;
import com.moody_blues.common.packet.update.UsernameUpdatePacket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientOutputWorker implements Runnable {
    private final ObjectOutputStream outputStream;
    private static ArrayList<DataPacket> packetsToSend = new ArrayList<>();

    public ClientOutputWorker() throws IOException {
        var socket = MoodyBluesClient.getClientData().getSocket();

        this.outputStream = new ObjectOutputStream(socket.getOutputStream());

        queuePacket(new UsernameUpdatePacket(MoodyBluesClient.getClientData().getUsername()));
        queuePacket(new RequestRoomListPacket());
    }

    synchronized public static void queuePacket(DataPacket packet) {
        packetsToSend.add(packet);
    }

    @Override
    public void run() {
        try {
            while(MoodyBluesClient.isRunnning()) {
                var toDelete = new ArrayList<DataPacket>();

                Thread.sleep(1);

                for(var packet : packetsToSend) {
                    try {
                        outputStream.writeObject(packet);
                    } catch (Exception ex) {
                        Logger.log("Closing output connection");
                    }

                    toDelete.add(packet);
                }

                packetsToSend.removeAll(toDelete);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
