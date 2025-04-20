package com.serenity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class PaymentFormController {

    @FXML
    private ComboBox<?> cmbPatient;

    @FXML
    private ComboBox<?> cmbTherapy;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPatientId;

    @FXML
    private TableColumn<?, ?> colTherapyId;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> colmethod;

    @FXML
    private Label lblPaymentId;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtamount;

    @FXML
    private TextField txtdate;

    @FXML
    private TextField txtmethod;

    @FXML
    void onActionReset(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }


}
