package com.serenity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TherapyProgramController {

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgramname;

    @FXML
    private TableColumn<?, ?> coldescription;

    @FXML
    private TableColumn<?, ?> colduration;

    @FXML
    private TableColumn<?, ?> colfee;

    @FXML
    private Label lblProgramId;

    @FXML
    private TableView<?> tblPrograms;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtduration;

    @FXML
    private TextField txtfee;

    @FXML
    void onActionRefresh(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }
}
