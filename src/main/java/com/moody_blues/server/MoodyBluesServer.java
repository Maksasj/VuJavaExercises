package com.moody_blues.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.moody_blues.common.Common;
import com.moody_blues.common.Logger;
import com.moody_blues.common.packet.DataPacket;
import com.moody_blues.common.packet.update.OnlineUsersPacket;
import com.moody_blues.common.packet.update.UpdateRoomListPacket;
import com.moody_blues.server.work.ClientAcceptWorker;
import com.moody_blues.server.work.ClientOnlineCleaner;
import com.moody_blues.server.work.DatabaseBackupWorker;

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

        loadDatabase();

        connectedClients = new ArrayList<>();

        new Thread(new ClientAcceptWorker(serverSocket)).start();
        new Thread(new ClientOnlineCleaner()).start();
        new Thread(new DatabaseBackupWorker()).start();

        while(isRunnning()) {
            // We just wait there
        }
    }

    synchronized public static ClientInstance getClientInstance(String name) {
        for(var client : connectedClients) {
            if(client.getUsername().contentEquals(name)) {
                return client;
            }
        }

        return null;
    }

    private static void loadDatabase() {
        try {
            var file = new File("database.txt");
            file.createNewFile();

            var input = new ObjectInputStream(new FileInputStream(file));

            database = (Database) input.readObject();

            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.log("Failed to load database from file, creating new one");

            database = new Database();
        }

        if(database == null) {
            database = new Database();
        }

        database.initPrivateRooms();
    }

    static public boolean isRunnning() {
        return runnning;
    }

    synchronized static public void sendPacketToEachClient(DataPacket dataPacket) {
        Logger.log("Sending packet to each client");

        for(var client : connectedClients) {
            if(!client.getSocket().isClosed()) {
                var handler = client.getOutputHandler();
                handler.queuePacket(dataPacket);
            }
        }
    }

    synchronized static public ArrayList<String> getOnlineUsersnames() {
        var userNames = new ArrayList<String>();
        for(var client : MoodyBluesServer.connectedClients) {
            if(!client.getSocket().isClosed()) {
                userNames.add(client.getUsername());
            }
        }

        return userNames;
    }

    synchronized static public void addClientInstance(ClientInstance clientInstance) {
        connectedClients.add(clientInstance);
    }
}
