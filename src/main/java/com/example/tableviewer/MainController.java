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
import java.util.Comparator;
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
    @FXML public TextField startCharTextField;
    @FXML public TextField endCharTextField;

    @FXML public CheckBox idFilterCheckBox;
    @FXML public Spinner<Integer> startIdSpinner;
    @FXML public Spinner<Integer> endIdSpinner;

    @FXML public CheckBox sortFilterCheckBox;
    @FXML public ChoiceBox<AlpTabs> sortTabChoiceBox;
    @FXML public CheckBox reverseOrederSortCheckBox;

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

        if( alphabetFilterCheckBox.isSelected() &&
            (tabChoiceBox.getValue() != null) &&
            (startCharTextField.getText() != null) &&
            (endCharTextField.getText() != null) &&
            (!startCharTextField.getText().isEmpty()) &&
            (!endCharTextField.getText().isEmpty())

        ) {
            stream = stream.filter(input -> {
                var human = (Human) input;

                var min = startCharTextField.getText().toLowerCase().charAt(0);
                var max =  endCharTextField.getText().toLowerCase().charAt(0);

                String fieldValue = " ";

                switch (tabChoiceBox.getValue()) {
                    case FIRST_NAME -> fieldValue = human.firstName;
                    case LAST_NAME -> fieldValue = human.lastName;
                    case EMAIL -> fieldValue = human.email;
                    case GENDER -> fieldValue = human.gender;
                    case COUNTRY -> fieldValue = human.country;
                    case DOMAIN_NAME -> fieldValue = human.domain;
                }

                if(fieldValue.isEmpty())
                    return false;

                var val = fieldValue.toLowerCase().charAt(0);

                return Utils.inRange(val, min, max);
            });
        }

        if(sortFilterCheckBox.isSelected() && (sortTabChoiceBox.getValue() != null)) {
            var cmp = Comparator.comparing(Human::getFirstName);

            switch (sortTabChoiceBox.getValue()) {
                case ID_TAB ->      cmp = Comparator.comparing(Human::getId);
                case FIRST_NAME ->  cmp = Comparator.comparing(Human::getFirstName);
                case LAST_NAME ->   cmp = Comparator.comparing(Human::getLastName);
                case EMAIL ->       cmp = Comparator.comparing(Human::getEmail);
                case GENDER ->      cmp = Comparator.comparing(Human::getGender);
                case COUNTRY ->     cmp = Comparator.comparing(Human::getCountry);
                case DOMAIN_NAME -> cmp = Comparator.comparing(Human::getDomain);
                case BIRTH_DAY_TAB -> cmp = Comparator.comparing(Human::getBirthDate);
            }

            if(reverseOrederSortCheckBox.isSelected())
                cmp = cmp.reversed();

            stream = stream.sorted(cmp);
        }

        List<Human> result = stream.collect(Collectors.toList());
        humanTableView.getItems().setAll(result);
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

        sortTabChoiceBox.getItems().add(AlpTabs.ID_TAB);
        sortTabChoiceBox.getItems().add(AlpTabs.FIRST_NAME);
        sortTabChoiceBox.getItems().add(AlpTabs.LAST_NAME);
        sortTabChoiceBox.getItems().add(AlpTabs.EMAIL);
        sortTabChoiceBox.getItems().add(AlpTabs.GENDER);
        sortTabChoiceBox.getItems().add(AlpTabs.COUNTRY);
        sortTabChoiceBox.getItems().add(AlpTabs.DOMAIN_NAME);
        sortTabChoiceBox.getItems().add(AlpTabs.BIRTH_DAY_TAB);

        setIntegerValueFactory(startIdSpinner, 0, Integer.MAX_VALUE);
        setIntegerValueFactory(endIdSpinner, 0, Integer.MAX_VALUE);
    }
}