package com.tartar_mouse_edition.idea;

import com.tartar_mouse_edition.game.TartarMEGame;
import com.tartar_mouse_edition.idea.components.codeEditor.CodeEditor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private TextArea codeTextArea;
    @FXML private Pane codePane;

    @FXML
    protected void onCodeSubmit() {
        System.out.println("Submitting code");

        new Thread(new TartarMEGame()).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CodeArea codeArea = new CodeEditor();
        codeArea.getStylesheets().add(TartarMEIDEA.class.getResource("java-keywords.css").toExternalForm());

        var s = new StackPane(new VirtualizedScrollPane<>(codeArea));
        s.getStylesheets().add(TartarMEIDEA.class.getResource("java-keywords.css").toExternalForm());

        s.setPrefWidth(600);
        s.setPrefHeight(450);

        codePane.getChildren().add(s);
    }
}