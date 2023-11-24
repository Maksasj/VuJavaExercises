package com.moody_blues.server.client;

import com.moody_blues.common.packet.CreateRoomPacket;
import com.moody_blues.common.packet.DataPacket;
import com.moody_blues.common.packet.UsernameUpdatePacket;
import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.MoodyBluesServer;
import javafx.scene.chart.PieChart;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientInputHandler implements Runnable {
    private ClientInstance clientInstance;
    private ObjectInputStream inputStream;

    public ClientInputHandler(ClientInstance clientInstance) throws IOException {
        this.clientInstance = clientInstance;

        var socket = clientInstance.getSocket();

        inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    @Override
    public void run() {
        while (MoodyBluesServer.isRunnning()) {
            try {
                var packet = inputStream.readObject();

                if(packet instanceof UsernameUpdatePacket) {
                    clientInstance.setUsername(((UsernameUpdatePacket) packet).getUsername());
                    System.out.println("Connected client user name: " + clientInstance.getUsername());
                }

                if(packet instanceof CreateRoomPacket) {
                    System.out.println("User have created new room");
                }
            } catch(Exception ex) {
                ex.printStackTrace();
                break;
            }
        }

        try {
            inputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
