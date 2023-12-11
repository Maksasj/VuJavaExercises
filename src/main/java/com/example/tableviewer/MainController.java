package com.example.tableviewer;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements ICommonController, Initializable {
    @FXML public TableView<Human> humanTableView;

    @FXML public TableColumn<Human, String> idTableColumn;
    @FXML public TableColumn<Human, String> firstNameTableColumn;
    @FXML public TableColumn<Human, String> lastNameTableColumn;
    @FXML public TableColumn<Human, String> emailTableColumn;
    @FXML public TableColumn<Human, String> genderTableColumn;
    @FXML public TableColumn<Human, String> countryTableColumn;
    @FXML public TableColumn<Human, String> domainNameTableColumn;
    @FXML public TableColumn<Human, String> birthDateTableColumn;

    @FXML public void onFilter() {
        System.out.println("On filter");
    }

    @Override
    public void onAnyUpdate() {
        Platform.runLater(() -> {
            humanTableView.getItems().setAll(TableViewer.getHumanRecords());
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableViewer.addController(this);

        idTableColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().id)));
        firstNameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().firstName));
        lastNameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().lastName));
        emailTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().email));
        genderTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().gender));
        countryTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().country));
        birthDateTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().birthDate.toString()));
    }
}