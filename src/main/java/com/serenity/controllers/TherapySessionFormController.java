package com.serenity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TherapySessionFormController {

    @FXML
    private ComboBox<?> cmbPatientId;

    @FXML
    private ComboBox<?> cmbProgramId;

    @FXML
    private ComboBox<?> cmbTherapistId;

    @FXML
    private Label lblId;

    @FXML
    private TextField txtSessionDate;

    @FXML
    void onActionReset(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

}
