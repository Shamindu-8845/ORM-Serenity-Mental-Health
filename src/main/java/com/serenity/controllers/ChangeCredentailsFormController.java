package com.serenity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeCredentailsFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbJobRoll;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtpassword;

    @FXML
    void onActionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> objects = FXCollections.observableArrayList();
        objects.add("Admin");
        objects.add("Recepsionist");
        cmbJobRoll.setItems((objects));
    }
}
