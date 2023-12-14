/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.tableviewer.componets;

import com.example.tableviewer.TableViewer;
import com.example.tableviewer.ThreadInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ThreadInfoListCell extends ListCell<ThreadInfo> {
    @FXML public AnchorPane mainAnchorPane;

    @FXML public Text threadNameText;
    @FXML public Text fileNameText;

    @FXML public ProgressBar threadProgressBar;

    private FXMLLoader loader;

    public ThreadInfoListCell(ListView<ThreadInfo> data) {
        super();
    }

    @Override
    public void updateItem(ThreadInfo message, boolean empty) {
        super.updateItem(message, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(TableViewer.class.getResource("threadInfoPane.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            message.getWorker().setListCell(this);

            threadNameText.setText("Thread " + message.getWorker().getId());
            fileNameText.setText(message.getWorker().getActiveFileName());

            threadProgressBar.setProgress(message.getWorker().getProgress());

            setGraphic(mainAnchorPane);
        }
    }

    public synchronized void queueProgressUpdate(float progress) {
        Platform.runLater(() -> {
            try {
                threadProgressBar.setProgress(progress);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}