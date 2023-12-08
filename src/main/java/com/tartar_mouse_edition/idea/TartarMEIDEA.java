package com.tartar_mouse_edition.idea;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tartar_mouse_edition.game.TartarMEGame;
import com.tartar_mouse_edition.idea.components.logMessage.LogMessage;
import javafx.scene.layout.BorderPane;

import com.tartar_mouse_edition.idea.components.codeEditor.CodeEditor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TartarMEIDEA extends Application {
    public static TartarMEGame game;
    public static Thread gameThread;
    public static MainController controller;

    public static ArrayList<LogMessage> logMessages;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TartarMEIDEA.class.getResource("ideaWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Tartar: Mouse Edition IDEA");
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();

        logMessages = new ArrayList<>();

        game = new TartarMEGame();
        gameThread = new Thread(game);

        gameThread.start();
    }
}