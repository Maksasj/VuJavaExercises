package com.example.omat;

import com.example.omat.common.CommonController;
import com.example.omat.files.importing.CSVFileImport;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.net.ssl.SSLContextSpi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OmatApplication extends Application {
    private static List<CommonController> controllers = new ArrayList<>();
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OmatApplication.class.getResource("omatMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("OMAT");
        stage.setScene(scene);
        stage.show();

        primaryStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }

    public static void addController(CommonController controller) {
        controllers.add(controller);
    }

    public static void onAnyUpdate() {
        Omat.onAnyUpdate();

        for(var controller : controllers) {
            controller.onAnyUpdate();
        }
    }
}