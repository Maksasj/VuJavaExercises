package com.moody_blues.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.moody_blues.common.Common;
import com.moody_blues.common.Logger;
import com.moody_blues.common.packet.DataPacket;
import com.moody_blues.common.packet.update.UpdateRoomListPacket;
import com.moody_blues.server.work.ClientAcceptWorker;
import com.moody_blues.server.work.ClientOnlineCleaner;

public class MoodyBluesServer {
    private static boolean runnning;
    private static ServerSocket serverSocket;

    public static Database database;
    public static ArrayList<ClientInstance> connectedClients;

    public static void main(String[] args) {
        Logger.log("Moody Blues server has started");

        try {
            serverSocket = new ServerSocket(Common.DEFAULT_PORT);
        } catch (Exception ex) {
            Logger.log("Failed to initialize server socket");
        }

        runnning = true;

        database = new Database();
        connectedClients = new ArrayList<>();

        new Thread(new ClientAcceptWorker(serverSocket)).start();
        new Thread(new ClientOnlineCleaner()).start();

        while(isRunnning()) {
            // We just wait there
        }
    }

    static public boolean isRunnning() {
        return runnning;
    }

    synchronized static public void sendPacketToEachClient(DataPacket dataPacket) {
        Logger.log("Sending packet to each client");

        for(var client : connectedClients) {
            if(client.online()) {
                var handler = client.getOutputHandler();
                handler.queuePacket(dataPacket);
            }
        }
    }

    synchronized static public void addClientInstance(ClientInstance clientInstance) {
        connectedClients.add(clientInstance);
    }
}
