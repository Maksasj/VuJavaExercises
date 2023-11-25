package com.moody_blues.server.client;

import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.common.packet.DataPacket;
import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.MoodyBluesServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientOutputHandler implements Runnable {
    private ClientInstance clientInstance;
    private final ObjectOutputStream outputStream;
    private ArrayList<DataPacket> packetsToSend = new ArrayList<>();

    public ClientOutputHandler(ClientInstance clientInstance) throws IOException {
        clientInstance.setOutputHandler(this);
        this.clientInstance = clientInstance;

        var socket = clientInstance.getSocket();

        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    synchronized public void queuePacket(DataPacket packet) {
        packetsToSend.add(packet);
    }

    @Override
    public void run() {
        try {
            while (MoodyBluesServer.isRunnning()) {
                if(!clientInstance.online())
                    break;

                var toDelete = new ArrayList<DataPacket>();

                // Wait until any packets needs to be sent
                while (packetsToSend.isEmpty()) {
                    Thread.sleep(1);
                }

                for (var packet : packetsToSend) {
                    outputStream.writeObject(packet);
                    toDelete.add(packet);
                }

                packetsToSend.removeAll(toDelete);
            }

            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
