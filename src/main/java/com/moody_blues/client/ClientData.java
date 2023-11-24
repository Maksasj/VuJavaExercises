package com.moody_blues.client;

import java.net.Socket;

public class ClientData {
    private Socket socket;
    private String username;

    public ClientData(Socket socket, String username) {
        this.username = username;
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }
}
