/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.lab2.rkc.scenes;

import com.lab2.rkc.CreditCalculator;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class CommonController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CreditCalculator.addActiveController(this);
        clearErrorLog();
    }

    public abstract void clearErrorLog();
    public abstract void notifyError(String message);
    public abstract void updateCreditUILists();
}
