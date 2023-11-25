package com.moody_blues.server;

import com.moody_blues.server.client.ClientInputHandler;
import com.moody_blues.server.client.ClientOutputHandler;

import java.net.Socket;

public class ClientInstance {
    private final Socket clientSocket;
    private String username;

    private ClientInputHandler inputHandler;
    private ClientOutputHandler outputHandler;

    public void setInputHandler(ClientInputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void setOutputHandler(ClientOutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    public ClientInputHandler getInputHandler() {
        return inputHandler;
    }

    public ClientOutputHandler getOutputHandler() {
        return outputHandler;
    }

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
