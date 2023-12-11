package com.example.tableviewer;

import com.example.tableviewer.componets.ThreadInfoListCell;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ThreadsController implements Initializable, ICommonController {
    @FXML public ListView<ThreadInfo> threadsListView;
    @FXML public ListView<String> logsListView;

    private Stage selfStage;

    public void setState(Stage stage) {
        selfStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableViewer.addController(this);

        FileChooser chooser = new FileChooser();

        var files = chooser.showOpenMultipleDialog(selfStage);

        for(int i = 0; i < files.size(); ++i) {
            var runnable = new FileLoaderWorker(i, files.get(i));
            var thread = new Thread(runnable);

            thread.start();

            threadsListView.getItems().add(new ThreadInfo(thread, runnable));
        }

        threadsListView.setCellFactory(ThreadInfoListCell::new);
    }

    @Override
    public void onAnyUpdate() {
        Platform.runLater(() -> {

        });
    }
}
