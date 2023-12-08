package com.tartar_mouse_edition.idea.components.logMessage;

import com.tartar_mouse_edition.idea.TartarMEIDEA;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Date;

public class LogMessageListCell extends ListCell<LogMessage> {
    @FXML public AnchorPane mainAnchorPane;

    @FXML public Text messageText;
    @FXML public Text timestampText;

    private FXMLLoader loader;

    public LogMessageListCell() {
        super();
    }

    @Override
    public void updateItem(LogMessage message, boolean empty) {
        super.updateItem(message, empty);

        setStyle("-fx-background-color: #1e1f22");

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(TartarMEIDEA.class.getResource("logMessageCell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            var text = message.messages;

            switch (message.getLevel()) {
                case RAT -> {
                    text = "[Rat] " + text;
                    messageText.setStyle("-fx-fill: #57955c");
                }
                case ERROR -> {
                    text = "[Error] " + text;
                    messageText.setStyle("-fx-fill: #c84f4f");
                }
                case WARNING -> {
                    text = "[Warning] " + text;
                    messageText.setStyle("-fx-fill: #c8aa4f");
                }
                case SYSTEM -> {
                    text = "[System] " + text;
                    messageText.setStyle("-fx-fill: #4f87c8");
                }
            }

            messageText.setText(text);
            timestampText.setText(new Date(message.timestamp).toString());

            setGraphic(mainAnchorPane);
        }
    }
}