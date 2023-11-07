package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.students.Faculty;
import com.example.omat.students.Group;
import com.example.omat.students.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GroupsTabController implements Initializable {
    @FXML public TextField groupNameTextField;
    @FXML public TextArea groupDescriptionTextArea;
    @FXML public ListView<Group> groupsListView;

    public void onAnyUpdate() {
        fillListView();
    }

    public void fillListView() {
        var items = FXCollections.observableArrayList(Omat.getGroups());
        groupsListView.setItems(items);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onAnyUpdate();
    }

    public void notifyError(String error) {
        // Todo
    }

    @FXML public void onAddGroup() {
        String groupName = "";
        String groupDescription = "";

        {
            var value = groupNameTextField.getText();
            if(value != null && !value.contentEquals(""))
                groupName = value;
            else {
                notifyError("Group name could not be empty");
                return;
            }
        }

        {
            var value = groupDescriptionTextArea.getText();
            if(value != null && !value.contentEquals(""))
                groupDescription = value;
            else {
                notifyError("Group description could not be empty");
                return;
            }
        }

        Omat.addGroup(new Group(groupName, groupDescription));

        onAnyUpdate();
    }

    @FXML public void onClearGroupName() {

        onAnyUpdate();
    }

    @FXML public void onClearGroupDescription() {

        onAnyUpdate();
    }
}
