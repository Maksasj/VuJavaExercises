package com.moody_blues.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ChatTabController {

    @FXML public TextField messageTextField;
    @FXML public ListView messageListView;
    @FXML public ListView activeUserListView;

    @FXML public Text totalMessageText;
    @FXML public Text roomTypeText;
    @FXML public Text roomNameText;
    @FXML public Text activeUserRecordText;

    @FXML
    protected void onSendMessage() {

    }

    @FXML
    protected void onClearMessage() {

    }
}
