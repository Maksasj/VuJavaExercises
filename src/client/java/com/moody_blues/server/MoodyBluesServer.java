package com.moody_blues.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.moody_blues.common.Common;

public class MoodyBluesServer {
    public static Database database;

    public static void main(String[] args) {
        System.out.println("Server has started !");

        try {
            var serverSocket = new ServerSocket(Common.DEFAULT_PORT);
            System.out.println("Successfully created server socket, at port " + Common.DEFAULT_PORT);

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Server have accepted a client");

                new Thread(new ClientHandler(socket)).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
