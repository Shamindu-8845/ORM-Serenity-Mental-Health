package com.serenity.controllers;


import com.serenity.bo.BOFactory;
import com.serenity.bo.custom.PatientManagementBO;
import com.serenity.dto.PatientsDTO;
import com.serenity.tm.PatientsTm;
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

public class PatientFormController implements Initializable {

    @FXML
    private TableColumn<PatientsTm,Integer> colPatientId;

    @FXML
    private TableColumn<PatientsTm,String> colPhoneno;

    @FXML
    private TableColumn<PatientsTm,String> colgender;

    @FXML
    private TableColumn<PatientsTm,String> colname;

    @FXML
    private Label lblPatientId;

    @FXML
    private TableView<PatientsTm> tblPatient;

    @FXML
    private TextField txtPhoneno;

    @FXML
    private TextField txtgender;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSave;

    PatientManagementBO patientManagementBO = (PatientManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENTS);


    public void onActionSave(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(lblPatientId.getText().replaceAll("[^\\d]", "")); // Extract only digits safely
            String name = txtname.getText();
            String gender = txtgender.getText();
            String phoneNo = txtPhoneno.getText();

            PatientsDTO patientDTO = new PatientsDTO(id, name, gender, phoneNo);

            if (btnSave.getText().equals("Save")) {
                boolean isSave = patientManagementBO.savePatient(patientDTO);

                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient Saved Successfully.").show();
                    loadTables();
                    loadIds();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Patient Not Saved.").show();
                }
            } else {
                boolean isUpdate = patientManagementBO.updatePatient(patientDTO);

                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient Updated Successfully.").show();
                    loadTables();
                    loadIds();
                    resetPage();
                    btnSave.setText("Save");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Patient Not Updated.").show();
                }
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Patient ID format.").show();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving or updating patient.").show();
        }
    }

    public void onActionReset(ActionEvent actionEvent) {
        loadTables();
        loadIds();
        resetPage();
        btnSave.setText("Save");
    }

    public void searchTherapySession(ActionEvent actionEvent) {
        try {
            String text = txtSearch.getText(); // Retrieve search text
            // Call the BO to search patients based on the input text
            List<PatientsDTO> patientsDTOS = patientManagementBO.searchPatient(text);

            // Convert the retrieved DTOs to TableModel objects for display
            ObservableList<PatientsTm> objects = FXCollections.observableArrayList();
            for (PatientsDTO patientsDTO : patientsDTOS) {
                // No modifications, just display the data
                objects.add(new PatientsTm(patientsDTO.getId(), patientsDTO.getName(), patientsDTO.getGender(), patientsDTO.getPhoneNo()));
            }

            // Set the data into the TableView for display
            tblPatient.setItems(objects);
        } catch (IOException e) {
            // Handle errors (e.g., connection issue, invalid data)
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPhoneno.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("id"));

        loadTables();
        loadIds();
        resetPage();
    }

    private void resetPage() {
        txtname.clear();
        txtgender.clear();
        txtPhoneno.clear();
        txtSearch.clear();
    }

    private void loadIds() {
        try {
            String lastId = patientManagementBO.getLastId();
            lblPatientId.setText(lastId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTables() {
        try {
            List<PatientsDTO> allPatients = patientManagementBO.getAllPatients();
            ObservableList<PatientsTm> objects = FXCollections.observableArrayList();

            for (PatientsDTO patientsDTO:allPatients){
                objects.add(new PatientsTm(patientsDTO.getId(),patientsDTO.getGender(),patientsDTO.getName(),patientsDTO.getPhoneNo()));
            }

            tblPatient.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
