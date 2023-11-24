package com.moody_blues.client.work;

import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.common.packet.UsernameUpdatePacket;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientOutputWorker implements Runnable {
    private final ObjectOutputStream outputStream;

    public ClientOutputWorker() throws IOException {
        var socket = MoodyBluesClient.getClientData().getSocket();

        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        var client = MoodyBluesClient.getClientData();

        try {
            outputStream.writeObject(new UsernameUpdatePacket(client.getUsername()));

            while(true) {
                // for now yes
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
