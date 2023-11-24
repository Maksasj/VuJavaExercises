package com.moody_blues.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.moody_blues.common.Common;
import com.moody_blues.server.work.ClientAcceptWorker;
import com.moody_blues.server.work.ClientOnlineCleaner;

public class MoodyBluesServer {
    private static boolean runnning;
    private static ServerSocket serverSocket;

    public static Database database;
    public static ArrayList<ClientInstance> connectedClients;

    public static void main(String[] args) {
        System.out.println("Server has started !");

        try {
            serverSocket = new ServerSocket(Common.DEFAULT_PORT);
        } catch (Exception ex) {
            System.out.println("Failed to initialize server socket");
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

    static synchronized public void addClientInstance(ClientInstance clientInstance) {
        connectedClients.add(clientInstance);
    }
}
