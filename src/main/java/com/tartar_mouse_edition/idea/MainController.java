package com.tartar_mouse_edition.idea;

import com.tartar_mouse_edition.game.TartarMEGame;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MainController {
    @FXML private TextArea codeTextArea;

    @FXML
    protected void onCodeSubmit() {
        System.out.println("Submitting code");

        new Thread(new TartarMEGame()).start();
    }
}