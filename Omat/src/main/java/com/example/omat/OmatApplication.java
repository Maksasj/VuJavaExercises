/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat;

import com.example.omat.common.CommonController;
import com.example.omat.common.OnAnyUpdateble;
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

public class OmatApplication extends Application implements OnAnyUpdateble {
    private static List<CommonController> controllers = new ArrayList<>();
    public static Stage primaryStage;
    public static OmatApplication instance;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OmatApplication.class.getResource("omatMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("OMAT");
        stage.setScene(scene);
        stage.show();

        primaryStage = stage;
        instance = this;
    }

    public static void main(String[] args) {
        launch();
    }

    public static void addController(CommonController controller) {
        controllers.add(controller);
    }

    public static void doUpdate() {
        Omat.onAnyUpdate();

        for(var controller : controllers) {
            controller.onAnyUpdate();
        }
    }

    public void onAnyUpdate() {
        doUpdate();
    }

    public static void updateControllers() {
        doUpdate();
    }
}