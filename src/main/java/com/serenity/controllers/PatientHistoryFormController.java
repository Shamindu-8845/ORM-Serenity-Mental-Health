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

public class PatientHistoryFormController implements Initializable {


    @FXML
    private TableColumn<TherapySessionTm,String> colDescription;

    @FXML
    private TableColumn<TherapySessionTm,Integer> colPatient;

    @FXML
    private TableColumn<TherapySessionTm,Integer> colProgram;

    @FXML
    private TableColumn<TherapySessionTm,Integer> colTherapy;

    @FXML
    private TableView<TherapySessionTm> tblPatientHistory;
    @FXML
    private TableColumn<TherapySessionTm,Double> colCost;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing TableView...");  // Debugging print statement
        colTherapy.setCellValueFactory(new PropertyValueFactory<>("therapy"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("cost"));
        loadTables();
    }

    private void loadTables() {
        PatientTheropyHistoryBO patientTheropyHistoryBO = (PatientTheropyHistoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT_HISTORY);
        try {
            List<TherapySessionsDTO> all = patientTheropyHistoryBO.getAll();
            System.out.println("Fetched data: " + all.size() + " records");  // Debugging print statement

            if (all.isEmpty()) {
                System.out.println("No data available.");  // Additional check if list is empty
            }

            ObservableList<TherapySessionTm> objects = FXCollections.observableArrayList();

            for (TherapySessionsDTO therapySessionsDTO : all) {
                // Debugging output for each TherapySessionDTO
                System.out.println("Therapy ID: " + therapySessionsDTO.getTherapy() + ", Patient ID: " + therapySessionsDTO.getPatient());

                objects.add(new TherapySessionTm(
                        therapySessionsDTO.getTherapy(),
                        therapySessionsDTO.getPatient(),
                        therapySessionsDTO.getProgram(),
                        therapySessionsDTO.getDate(),
                        therapySessionsDTO.getDescription(),
                        therapySessionsDTO.getCost() // ✅ FIXED: method call with correct case
                ));
            }

                tblPatientHistory.setItems(objects);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
