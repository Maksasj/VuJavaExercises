package com.moody_blues.server.work;

import com.moody_blues.common.Logger;
import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.MoodyBluesServer;
import com.moody_blues.server.client.ClientInputHandler;
import com.moody_blues.server.client.ClientOutputHandler;

import java.net.ServerSocket;
import java.net.Socket;

public class ClientAcceptWorker implements Runnable {
    private ServerSocket serverSocket;

    public ClientAcceptWorker(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while(MoodyBluesServer.isRunnning()) {
            try {
                Socket socket = serverSocket.accept();

                Logger.log("Accepted new user");

                var clientInstance = new ClientInstance(socket);

                new Thread(new ClientOutputHandler(clientInstance)).start();
                new Thread(new ClientInputHandler(clientInstance)).start();

                MoodyBluesServer.addClientInstance(clientInstance);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
