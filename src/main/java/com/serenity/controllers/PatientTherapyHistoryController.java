package com.serenity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class PatientTherapyHistoryController {

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPatient;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colTherapy;

    @FXML
    private TableView<?> tblPatientTherapyHistory;


}
