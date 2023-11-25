package com.moody_blues.client.controllers;

import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.client.work.ClientOutputWorker;
import com.moody_blues.common.Room;
import com.moody_blues.common.packet.update.CreateRoomPacket;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class ChatWindowController extends CommonController {
    @FXML private ListView<Room> roomsListView;
    @FXML private TabPane chatTabPane;

    public ChatWindowController() {
        super();

        MoodyBluesClient.addControllerAsMain(this);
    }

    @FXML
    protected void onCreateNewRoom() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MoodyBluesClient.class.getResource("createRoomWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            Stage stage = new Stage();

            stage.setTitle("Moody Blues");
            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void onJoinSelectedRoom() {
        var room = roomsListView.getSelectionModel().getSelectedItem();
        if(room == null)
            return;

        try {
            var loader = new FXMLLoader(MoodyBluesClient.class.getResource("chatTab.fxml"));
            Tab tab = loader.load();

            ChatTabController controller = loader.getController();
            controller.setRoom(room);

            chatTabPane.getTabs().add(tab);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onAnyUpdate() {
        Platform.runLater(() -> {
            var client = MoodyBluesClient.getClientData();

            roomsListView.getItems().setAll(client.getRooms());
        });
    }
}
