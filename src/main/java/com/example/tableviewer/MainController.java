package com.example.tableviewer;

import com.example.tableviewer.utils.Utils;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainController extends CommonController implements Initializable {
    @FXML public TableView<Human> humanTableView;

    @FXML public TableColumn<Human, String> idTableColumn;
    @FXML public TableColumn<Human, String> firstNameTableColumn;
    @FXML public TableColumn<Human, String> lastNameTableColumn;
    @FXML public TableColumn<Human, String> emailTableColumn;
    @FXML public TableColumn<Human, String> genderTableColumn;
    @FXML public TableColumn<Human, String> countryTableColumn;
    @FXML public TableColumn<Human, String> domainNameTableColumn;
    @FXML public TableColumn<Human, String> birthDateTableColumn;


    @FXML public CheckBox dateFilterCheckBox;

    @FXML public DatePicker startDatePicker;
    @FXML public DatePicker endDatePicker;


    @FXML public CheckBox alphabetFilterCheckBox;

    @FXML public ChoiceBox<AlpTabs> tabChoiceBox;


    @FXML public CheckBox idFilterCheckBox;
    @FXML public Spinner<Integer> startIdSpinner;
    @FXML public Spinner<Integer> endIdSpinner;


    @FXML public void onFilter() {
        Stream<Human> stream = TableViewer.getHumanRecords().stream();

        if(idFilterCheckBox.isSelected() && (startIdSpinner.getValue() != null) && (endIdSpinner.getValue() != null)) {
            stream = stream.filter(input -> {
                var human = (Human) input;

                var min = startIdSpinner.getValue();
                var max = endIdSpinner.getValue();

                return Utils.inRange(human.id, min, max);
            });
        }

        if(dateFilterCheckBox.isSelected() && (startDatePicker.getValue() != null) && (endDatePicker.getValue() != null)) {
            stream = stream.filter(input -> {
                var human = (Human) input;

                var min = startDatePicker.getValue();
                var max = endDatePicker.getValue();

                return Utils.inRange(human.birthDate, min, max);
            });
        }

        List<Human> result = stream.collect(Collectors.toList());
        humanTableView.getItems().setAll(result);

        System.out.println("Filtering");
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
        domainNameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().domain));
        birthDateTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().birthDate.toString()));

        tabChoiceBox.getItems().add(AlpTabs.FIRST_NAME);
        tabChoiceBox.getItems().add(AlpTabs.LAST_NAME);
        tabChoiceBox.getItems().add(AlpTabs.EMAIL);
        tabChoiceBox.getItems().add(AlpTabs.GENDER);
        tabChoiceBox.getItems().add(AlpTabs.COUNTRY);
        tabChoiceBox.getItems().add(AlpTabs.DOMAIN_NAME);

        setIntegerValueFactory(startIdSpinner, 0, Integer.MAX_VALUE);
        setIntegerValueFactory(endIdSpinner, 0, Integer.MAX_VALUE);
    }
}