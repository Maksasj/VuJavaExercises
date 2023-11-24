package com.moody_blues.client.work;

import com.moody_blues.client.ClientData;
import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.common.packet.UsernameUpdatePacket;

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
        while(true) {
            try {
                var packet = inputStream.readObject();

            } catch (Exception ex) {

            }
        }
    }
}
