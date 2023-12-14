/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat.common;

import com.example.omat.OmatApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class CommonController implements Initializable, OnAnyUpdateble {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OmatApplication.addController(this);
    }

    static protected void setIntegerValueFactory(Spinner<Integer> spinner, int min, int max) {
        var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
        valueFactory.setValue(min);
        spinner.setValueFactory(valueFactory);

        spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            spinner.getValueFactory().setValue(newValue);
        });
    }

    public abstract void notifyError(String error);
}
