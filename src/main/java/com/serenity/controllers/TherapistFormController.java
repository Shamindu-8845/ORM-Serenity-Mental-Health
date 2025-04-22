package com.serenity.controllers;

import com.serenity.bo.BOFactory;
import com.serenity.bo.custom.TherapistManagementBO;
import com.serenity.dto.TherapistsDTO;
import com.serenity.tm.TherapistTm;
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

public class TherapistFormController implements Initializable {

        @FXML
        private Label lblId;

        @FXML
        private Button btnRefresh;

        @FXML
        private Button btnSave;

        @FXML
        private ComboBox<String> cmbPrograms;

        @FXML
        private TableColumn<TherapistTm,String> colPrograms;

        @FXML
        private TableColumn<TherapistTm,String> colname;

        @FXML
        private TableColumn<TherapistTm,String> colphone;

        @FXML
        private TableView<TherapistTm> tblTherapist;

        @FXML
        private TextField txtPhone;

        @FXML
        private TextField txtname;

        TherapistManagementBO therapistManagementBO = (TherapistManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPIST);


        @FXML
        void onActionRefresh(ActionEvent event) {
                refreshPage();
                loadTables();
                setId();
        }

        private void refreshPage() {
                txtname.clear();
                txtPhone.clear();
                /*cmbProgram.setValue("");*/
                btnSave.setText("Save");
        }

        @FXML
        void onActionSave(ActionEvent event) {
                String name = txtname.getText();
                String phoneNo = txtPhone.getText();
                String program = cmbPrograms.getValue().toString();
                String id = lblId.getText();

                if (name == null || phoneNo == null || program == null || id == null) {
                        new Alert(Alert.AlertType.ERROR, "Missing Fields.").show();
                        throw new RuntimeException("Input Fields are Empty..");
                }

                if (btnSave.getText().equals("Save")) {
                        try {
                                boolean isSave = therapistManagementBO.saveTherapist(new TherapistsDTO(name, phoneNo, program));
                                if (isSave) {
                                        new Alert(Alert.AlertType.INFORMATION, "Therapist Saved Successfully.").show();
                                        refreshPage();
                                        loadTables();
                                        setId();
                                } else {
                                        new Alert(Alert.AlertType.WARNING, "Therapist Not Saved.").show();
                                }
                        } catch (IOException e) {
                                throw new RuntimeException(e);
                        }
                } else if (btnSave.getText().equals("Update")) {
                        try {
                                String lastId = id.replaceAll("^T", "");

                                boolean isUpdate = therapistManagementBO.updateTherapist(
                                        new TherapistsDTO(Integer.parseInt(lastId), name, phoneNo, program)
                                );

                                if (isUpdate) {
                                        new Alert(Alert.AlertType.INFORMATION, "Therapist Updated Successfully.").show();
                                        refreshPage();
                                        loadTables();
                                        setId();
                                } else {
                                        new Alert(Alert.AlertType.WARNING, "Therapist Not Updated.").show();
                                }
                        } catch (IOException e) {
                                throw new RuntimeException(e);
                        }
                }

        }

        public void onActionPrograms(ActionEvent actionEvent) {

        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                loadTables();
                setId();
                setValues();
        }

        private void loadTables() {
                try {
                        List<TherapistsDTO> allTherapist = therapistManagementBO.getAllTherapist();
                        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
                        colphone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
                        colPrograms.setCellValueFactory(new PropertyValueFactory<>("program"));
                        ObservableList<TherapistTm> objects = FXCollections.observableArrayList();

                        for (TherapistsDTO therapistsDTO:allTherapist){
                                objects.add(new TherapistTm(therapistsDTO.getName(),therapistsDTO.getPhoneNo(),therapistsDTO.getProgram()));
                        }

                        tblTherapist.setItems(objects);
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }
        private void setId() {
                try {
                        String lastId = therapistManagementBO.getLastId();
                        lblId.setText(lastId);
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }

        }

        private void setValues() {
                try {
                        List<String> allProgram = therapistManagementBO.getAllProgram();
                        ObservableList<String> objects = FXCollections.observableArrayList();
                        for (String program:allProgram){
                                objects.add(program);
                        }
                        cmbPrograms.setItems(objects);
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

}
