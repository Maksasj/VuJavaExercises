package com.example.tableviewer;

import com.example.tableviewer.componets.ThreadInfoListCell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileLoaderWorker implements Runnable {
    private int id;
    private File file;

    private boolean done;
    private float progress;

    private ThreadInfoListCell listCell = null;

    public int getId() {
        return id;
    }

    public void setListCell(ThreadInfoListCell listCell) {
        this.listCell = listCell;
    }

    public String getActiveFileName() {
        return file.getName();
    }

    public FileLoaderWorker(int id, File file) {
        this.id = id;
        this.file = file;

        this.done = false;
        this.progress = 0;
    }

    public static int countLines(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            return lineCount;
        }
    }


    public boolean isDone() {
        return done;
    }

    public float getProgress() {
        return progress;
    }

    @Override
    public void run() {
        int totalLines;
        BufferedReader reader;

        try {
            totalLines = countLines(file);
        } catch (Exception ex) {
            TableViewer.addThreadErrorMessage("[Thread " + id + "]" + ex.getMessage());
            return;
        }

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (Exception ex) {
            TableViewer.addThreadErrorMessage("[Thread " + id + "]" + ex.getMessage());
            return;
        }

        String line;
        try {
            line = reader.readLine();
        } catch (Exception ex) {
            TableViewer.addThreadErrorMessage("[Thread " + id + "]" + ex.getMessage());
            return;
        }

        int lineCount = 1;

        boolean firstLine = true;
        while (line != null) {
            ++lineCount;
            if(firstLine) {
                firstLine = false;
                try {
                    line = reader.readLine();
                } catch (Exception ex) {
                    TableViewer.addThreadErrorMessage("[Thread " + id + "]" + ex.getMessage());
                    return;
                }
                continue;
            }

            Human human;

            try {
                human = Human.loadFromString(line);
            } catch (Exception ex) {
                TableViewer.addThreadErrorMessage("[Thread " + id + "] failed to parse human string");

                try {
                    line = reader.readLine();
                } catch (Exception exx) {
                    TableViewer.addThreadErrorMessage("[Thread " + id + "]" + ex.getMessage());
                }

                continue;
            }

            try {
                Thread.sleep(Math.round(Math.random() * 20));
            } catch (Exception ex) {
                TableViewer.addThreadErrorMessage("[Thread " + id + "]" + ex.getMessage());
            }

            TableViewer.addHumanRecord(human);
            TableViewer.onAnyUpdate();

            progress = ((float) lineCount) / ((float) totalLines);

            if(listCell != null)
                listCell.queueProgressUpdate(progress);

            try {
                line = reader.readLine();
            } catch (Exception ex) {
                TableViewer.addThreadErrorMessage("[Thread " + id + "]" + ex.getMessage());
            }
        }

        done = true;

        TableViewer.addThreadErrorMessage("[Thread " + id + "] is done");
    }
}
