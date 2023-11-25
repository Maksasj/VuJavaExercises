package com.moody_blues.client.common;

import com.moody_blues.client.MoodyBluesClient;
import com.moody_blues.common.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Date;

public class ChatListCell extends ListCell<Message> {
    @FXML public AnchorPane mainAnchorPane;

    @FXML public Text usernameText;
    @FXML public Text dateText;
    @FXML public Text messageText;

    private FXMLLoader loader;

    public ChatListCell() {
        super();
    }

    @Override
    public void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(MoodyBluesClient.class.getResource("chatMessageListCell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            var date = new Date(message.getTimestamp());

            usernameText.setText(message.getSender());
            dateText.setText(date.toString());
            messageText.setText(message.getText());

            setGraphic(mainAnchorPane);
        }
    }
}