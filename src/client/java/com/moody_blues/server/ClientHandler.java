package com.moody_blues.server;

import com.moody_blues.common.DataPacket;
import javafx.scene.chart.PieChart;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;

        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inputStream = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataPacket packet = new DataPacket("Poggers, this is a test message from server !");

                outputStream.writeObject(packet);
            } catch(Exception ex) {
                ex.printStackTrace();
                break;
            }
        }

        try {
            inputStream.close();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
