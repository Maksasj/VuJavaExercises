package com.example.tableviewer;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public abstract class CommonController {
    abstract public void onAnyUpdate();

    static protected void setIntegerValueFactory(Spinner<Integer> spinner, int min, int max) {
        var valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
            valueFactory.setValue(min);
            spinner.setValueFactory(valueFactory);

            spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            spinner.getValueFactory().setValue(newValue);
        });
    }
}
