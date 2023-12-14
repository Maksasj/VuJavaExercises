/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.client.controllers;

import com.moody_blues.client.ClientData;
import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.client.work.ClientInputWorker;
import com.moody_blues.client.work.ClientOutputWorker;
import com.moody_blues.common.Common;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.Socket;

public class ConnectionWindowController extends CommonController {
    @FXML public TextField usernameTextField;

    public ConnectionWindowController() {
        super();
    }

    @FXML
    protected void onConnectToServer() {
        try {
            var clientSocket = new Socket(Common.DEFAULT_IP_ADRESS, Common.DEFAULT_PORT);

            String username = usernameTextField.getText();

            MoodyBluesClient.setClientData(new ClientData(clientSocket, username));

            new Thread(new ClientOutputWorker()).start();
            new Thread(new ClientInputWorker()).start();

            FXMLLoader fxmlLoader = new FXMLLoader(MoodyBluesClient.class.getResource("mainChatWindow.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 800, 600);

            var controller = (ChatWindowController) fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setTitle("Moody Blues");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(e -> controller.shutdown());
            stage.show();

            closeWindow();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.close();
    }

    @Override
    public void onAnyUpdate() {

    }
}