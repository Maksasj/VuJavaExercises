/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.omat.controllers;

import com.example.omat.Omat;
import com.example.omat.OmatApplication;
import com.example.omat.common.CommonController;
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

public class GroupsTabController extends CommonController {
    @FXML public TextField groupNameTextField;
    @FXML public TextArea groupDescriptionTextArea;
    @FXML public ListView<Group> groupsListView;

    @Override
    public void onAnyUpdate() {
        fillListView();
    }

    public void fillListView() {
        var items = FXCollections.observableArrayList(Omat.getGroups());
        groupsListView.setItems(items);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        OmatApplication.updateControllers();
    }

    @FXML public void onDeleteAllGroups() {
        Omat.deleteAllGroups();

        OmatApplication.updateControllers();
    }

    @FXML public void onDeleteSelectedGroup() {
        var selected = groupsListView.getSelectionModel().getSelectedItem();

        if(selected != null) {
            Omat.deleteGroup(selected);
        }

        OmatApplication.updateControllers();
    }

    @Override
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

        OmatApplication.updateControllers();
    }

    @FXML public void onClearGroupName() {

        OmatApplication.updateControllers();
    }

    @FXML public void onClearGroupDescription() {

        OmatApplication.updateControllers();
    }
}
