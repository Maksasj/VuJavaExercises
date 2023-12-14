/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.client.controllers;

import com.moody_blues.client.ClientData;
import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.client.common.ChatListCell;
import com.moody_blues.client.work.ClientOutputWorker;
import com.moody_blues.common.Message;
import com.moody_blues.common.Room;
import com.moody_blues.common.RoomType;
import com.moody_blues.common.packet.update.SendMessagePacket;
import com.moody_blues.common.packet.update.SendPrivateMessagePacket;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatTabController extends CommonController implements Initializable {
    @FXML public Tab tabTab;
    @FXML public TextField messageTextField;
    @FXML public ListView<Message> messageListView;
    @FXML public ListView activeUserListView;

    @FXML public Text totalMessageText;
    @FXML public Text roomTypeText;
    @FXML public Text roomNameText;
    @FXML public Text activeUserRecordText;

    private Room room = null;

    public ChatTabController() {
        super();

        MoodyBluesClient.addControllerAsChat(this);
    }

    public void setRoom(Room room) {
        this.room = room;

        tabTab.setText(room.getRoomName());

        updateRoomInfo();
        updateMessagesList();
    }

    private void sendMessage() {
        var text = messageTextField.getText();
        if(text != null && !text.isEmpty()) {
            var message = new Message(MoodyBluesClient.getClientData().getUsername(), text);

            if(room.getType() == RoomType.PUBLIC) {
                ClientOutputWorker.queuePacket(new SendMessagePacket(room.getRoomUUID(), message));
            } else {
                ClientOutputWorker.queuePacket(new SendPrivateMessagePacket(room.getRoomUUID(), message));
            }

            messageTextField.setText("");
        }
    }

    private void updateMessagesList() {
        if (room == null)
            return;


        if(room.getType() == RoomType.PUBLIC) {
            var messages = MoodyBluesClient.getClientData().getRoom(room.getRoomUUID()).getMessages();
            messageListView.getItems().setAll(messages);
            messageListView.scrollTo(messageListView.getItems().size());
        } else {
            var messages = MoodyBluesClient.getClientData().getPrivateRoom(room.getRoomUUID()).getMessages();
            messageListView.getItems().setAll(messages);
            messageListView.scrollTo(messageListView.getItems().size());
        }
    }


    @FXML
    protected void onSendMessage() {
        sendMessage();
    }

    @FXML
    protected void onClearMessage() {
        messageTextField.setText("");
    }

    @Override
    public void onAnyUpdate() {
        Platform.runLater(() -> {
            updateMessagesList();
            updateRoomInfo();
        });
    }

    private void updateRoomInfo() {
        totalMessageText.setText("Total messages: " + room.getMessages().size());
        roomTypeText.setText("Room type: " + room.getType().toString());
        roomNameText.setText("Room name " + room.getRoomName());
        activeUserRecordText.setText("Active user record: 0");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageListView.setCellFactory(param -> new ChatListCell());

        messageTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    sendMessage();
                }
            }
        });
    }
}
