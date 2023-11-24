package com.moody_blues.server.client;

import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.MoodyBluesServer;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientOutputHandler implements Runnable {
    private ClientInstance clientInstance;
    private ObjectOutputStream outputStream;

    public ClientOutputHandler(ClientInstance clientInstance) throws IOException {
        this.clientInstance = clientInstance;

        var socket = clientInstance.getSocket();

        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (MoodyBluesServer.isRunnning()) {
            try {
                // DataPacket packet = new UsernameUpdatePacket();

                // outputStream.writeObject(packet);
            } catch(Exception ex) {
                ex.printStackTrace();
                break;
            }
        }

        try {
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
