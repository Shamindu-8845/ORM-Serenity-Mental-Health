package com.serenity.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PatientFormController {

    @FXML
    private TableColumn<?, ?> colPatientId;

    @FXML
    private TableColumn<?, ?> colPhoneno;

    @FXML
    private TableColumn<?, ?> colgender;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private Label lblPatientId;

    @FXML
    private TableView<?> tblPatient;

    @FXML
    private TextField txtPhoneno;

    @FXML
    private TextField txtgender;

    @FXML
    private TextField txtname;
}
