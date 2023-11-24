package com.moody_blues.client;

import com.moody_blues.common.Common;
import com.moody_blues.common.DataPacket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MoodyBluesClient extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodyBluesClient.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Client has started !");

        try {
            var clientSocket = new Socket(Common.DEFAULT_IP_ADRESS, Common.DEFAULT_PORT);
            System.out.println("Successfully created client socket, at port " + Common.DEFAULT_PORT);

            var inputStream = new ObjectInputStream(clientSocket.getInputStream());
            var outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("Successfully created client input and output streams");

            while(true) {
                DataPacket packet = (DataPacket) inputStream.readObject();

                System.out.println("Received data packet from server: " + packet.message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // launch();
    }
}