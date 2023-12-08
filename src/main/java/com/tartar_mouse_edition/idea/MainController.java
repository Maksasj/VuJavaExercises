package com.tartar_mouse_edition.idea;

import com.tartar_mouse_edition.game.TartarMEGame;
import com.tartar_mouse_edition.idea.components.codeEditor.CodeEditor;
import com.tartar_mouse_edition.idea.components.logMessage.LogMessage;
import com.tartar_mouse_edition.idea.components.logMessage.LogMessageListCell;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private CodeArea codeArea;
    @FXML private Pane codePane;
    @FXML private ListView<LogMessage> consoleListView;

    @FXML protected void onExecuteCode() {
        TartarMEGame.submitCode(codeArea.getText());
    }
    @FXML protected void onClearLogs() {
        TartarMEIDEA.logMessages.clear();
        consoleListView.getItems().clear();
    }
    @FXML protected void onForceStop() {
        TartarMEGame.forceStop();
    }
    @FXML protected void onRestart() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeArea = new CodeEditor();
        codeArea.getStylesheets().add(TartarMEIDEA.class.getResource("java-keywords.css").toExternalForm());

        var s = new StackPane(new VirtualizedScrollPane<>(codeArea));
        s.getStylesheets().add(TartarMEIDEA.class.getResource("java-keywords.css").toExternalForm());

        s.setPrefWidth(600);
        s.setPrefHeight(450);

        codePane.getChildren().add(s);

        TartarMEIDEA.controller = this;

        consoleListView.setCellFactory(param -> new LogMessageListCell());
        consoleListView.getStylesheets().add(TartarMEIDEA.class.getResource("java-keywords.css").toExternalForm());
    }

    synchronized public void logMessage(LogMessage message) {
        TartarMEIDEA.logMessages.add(message);

        Platform.runLater(() -> {
            consoleListView.getItems().setAll(TartarMEIDEA.logMessages);
            int index = consoleListView.getItems().size();
            consoleListView.scrollTo(index - 1);
        });
    }
}