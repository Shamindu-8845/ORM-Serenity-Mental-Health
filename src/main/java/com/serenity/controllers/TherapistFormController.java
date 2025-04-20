package com.serenity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TherapistFormController {
        @FXML
        private Button btnRefresh;

        @FXML
        private Button btnSave;

        @FXML
        private ComboBox<?> cmbPrograms;

        @FXML
        private TableColumn<?, ?> colPrograms;

        @FXML
        private TableColumn<?, ?> colname;

        @FXML
        private TableColumn<?, ?> colphone;

        @FXML
        private TableView<?> tblTherapist;

        @FXML
        private TextField txtPhone;

        @FXML
        private TextField txtname;

        @FXML
        void onActionRefresh(ActionEvent event) {

        }

        @FXML
        void onActionSave(ActionEvent event) {

        }


}
