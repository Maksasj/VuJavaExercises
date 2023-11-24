package com.moody_blues.server;

import java.net.Socket;

public class ClientInstance {
    private final Socket clientSocket;
    private String username;

    public ClientInstance(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Socket getSocket() {
        return clientSocket;
    }

    public boolean online() {
        return clientSocket.isConnected();
    }

    synchronized public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
