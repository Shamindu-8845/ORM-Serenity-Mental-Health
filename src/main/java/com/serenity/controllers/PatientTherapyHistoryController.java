package com.serenity.controllers;

import com.serenity.bo.BOFactory;
import com.serenity.bo.custom.PatientTheropyHistoryBO;
import com.serenity.dto.TherapySessionsDTO;
import com.serenity.tm.TherapySessionTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class PatientTherapyHistoryController implements Initializable {

    @FXML
    private TableColumn<TherapySessionTm,Double> colCost;

    @FXML
    private TableColumn<TherapySessionTm,String> colDescription;

    @FXML
    private TableColumn<TherapySessionTm,Integer> colPatient;

    @FXML
    private TableColumn<TherapySessionTm,Integer> colProgram;

    @FXML
    private TableColumn<TherapySessionTm,Integer> colTherapy;

    @FXML
    private TableView<TherapySessionTm> tblPatientTherapyHistory;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTherapy.setCellValueFactory(new PropertyValueFactory<>("therapy"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        loadTables();
    }

    private void loadTables() {
        PatientTheropyHistoryBO patientTheropyHistoryBO = (PatientTheropyHistoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT_HISTORY);
        try {
            List<TherapySessionsDTO> all = patientTheropyHistoryBO.getAll();

            ObservableList<TherapySessionTm> objects = FXCollections.observableArrayList();

            for (TherapySessionsDTO therapySessionsDTO : all) {
                objects.add(new TherapySessionTm(
                        therapySessionsDTO.getTherapy(),
                        therapySessionsDTO.getPatient(),
                        therapySessionsDTO.getProgram(),
                        therapySessionsDTO.getDate(),
                        therapySessionsDTO.getDescription(),
                        therapySessionsDTO.getCost() // ✅ method call, not variable
                ));
            }

            tblPatientTherapyHistory.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
