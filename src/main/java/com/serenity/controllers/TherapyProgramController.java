package com.serenity.controllers;

import com.serenity.bo.BOFactory;
import com.serenity.bo.custom.TherapyProgramManagementBO;
import com.serenity.dto.TherapyProgramsDTO;
import com.serenity.tm.TherapyProgramTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapyProgramController implements Initializable {

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<TherapyProgramTm,String> colProgramId;

    @FXML
    private TableColumn<TherapyProgramTm,String> colProgramname;

    @FXML
    private TableColumn<TherapyProgramTm,String> coldescription;

    @FXML
    private TableColumn<TherapyProgramTm,String> colduration;

    @FXML
    private TableColumn<TherapyProgramTm,Double> colfee;

    @FXML
    private Label lblProgramId;

    @FXML
    private TableView<TherapyProgramTm> tblPrograms;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtduration;

    @FXML
    private TextField txtfee;

    TherapyProgramManagementBO therapyProgramManagementBO = (TherapyProgramManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);


    @FXML
    void onActionRefresh(ActionEvent event) {
        loadTables();
        refreshPage();
        btnSave.setText("Save");
    }

    @FXML
    void onActionSave(ActionEvent event) {
        String name = txtProgramName.getText();
        String duration = txtduration.getText();
        String cost = txtfee.getText();
        String description = txtdescription.getText();
        String id = lblProgramId.getText();

        if (name == null || duration == null || cost == null || description == null) {
            new Alert(Alert.AlertType.ERROR, "Missing Fields.").show();
            throw new NullPointerException("Input Fields are Empty..");
        }

        if (btnSave.getText().equals("Save")) {
            try {
                boolean isSave = therapyProgramManagementBO.saveTherapy(
                        new TherapyProgramsDTO(name, duration, Double.parseDouble(cost), description)
                );

                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapy Program Saved Successfully.").show();
                    loadTables();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Therapy Program Not Saved.").show();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (btnSave.getText().equals("Update")) {
            try {
                String lid = id.replaceAll("^TP", "");
                boolean isUpdate = therapyProgramManagementBO.updateTherapyProgram(
                        new TherapyProgramsDTO(Integer.parseInt(lid), name, duration, Double.parseDouble(cost), description)
                );

                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapy Program Updated Successfully.").show();
                    loadTables();
                    refreshPage();
                    btnSave.setText("Save");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Therapy Program Not Updated.").show();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProgramname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colfee.setCellValueFactory(new PropertyValueFactory<>("cost"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));

        loadTables();
        refreshPage();
        setIds();
    }

    private void loadTables() {

        try {
            List<TherapyProgramsDTO> therapyPrograms = therapyProgramManagementBO.getTherapyPrograms();
            ObservableList<TherapyProgramTm> objects = FXCollections.observableArrayList();
            for (TherapyProgramsDTO dto : therapyPrograms) {
                String id = "TP" + dto.getId(); // If programId is an int, prefix it
                objects.add(new TherapyProgramTm(id, dto.getName(), dto.getDuration(), dto.getCost(), dto.getDescription()));
            }
            tblPrograms.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void refreshPage() {
        txtProgramName.clear();
        txtduration.clear();
        txtfee.clear();
        txtdescription.clear();
    }

    private void setIds() {
        try {
            String lastId = therapyProgramManagementBO.getLastId();
            lblProgramId.setText(lastId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
