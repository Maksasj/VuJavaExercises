/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.client.controllers;

import com.moody_blues.client.work.ClientOutputWorker;
import com.moody_blues.common.packet.update.CreateRoomPacket;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateRoomWindow {
    @FXML protected TextField roomNameTextField;

    @FXML
    public void onClearRoomName() {
        roomNameTextField.setText("");
    }

    @FXML
    public void onCreateNewRoom() {
        if(!roomNameTextField.getText().isEmpty()) {
            ClientOutputWorker.queuePacket(new CreateRoomPacket(roomNameTextField.getText()));
            closeWindow();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) roomNameTextField.getScene().getWindow();
        stage.close();
    }
}
