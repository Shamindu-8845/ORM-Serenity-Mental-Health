package com.serenity.controllers;

import com.serenity.bo.BOFactory;
import com.serenity.bo.custom.TherapySessionSchedulingBO;
import com.serenity.dto.TherapySessionsDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TherapySessionFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbPatientId;

    @FXML
    private ComboBox<String> cmbProgramId;

    @FXML
    private ComboBox<String> cmbTherapistId;

    @FXML
    private Label lblId;

    @FXML
    private TextField txtDescription;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtSessionDate;

    TherapySessionSchedulingBO therapySessionSchedulingBO = (TherapySessionSchedulingBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPY_SESSION);


    @FXML
    void onActionReset(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {
        try {
            // Retrieve input values from the UI components
            String patient = cmbPatientId.getValue(); // Get selected patient ID from ComboBox
            String therapy = cmbTherapistId.getValue(); // Get selected therapist ID from ComboBox
            String program = cmbProgramId.getValue(); // Get selected program ID from ComboBox
            String sessionDateString = txtSessionDate.getText(); // Session date as text from TextField
            String description = txtDescription.getText(); // Get the session description from TextField
            String id = lblId.getText(); // Get the session ID (if editing an existing session)

            // Validate that all required fields are filled
            if (patient == null || therapy == null || program == null || sessionDateString.isEmpty() || description.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Missing Fields. Please fill in all fields.").show();
                return; // Early return if validation fails
            }

            // Try parsing the session date
            Date sessionDate = null;
            try {
                sessionDate = Date.valueOf(sessionDateString); // Parse the session date from TextField
            } catch (IllegalArgumentException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid date format. Please use yyyy-MM-dd.").show();
                return; // Exit early if the date format is invalid
            }

            // Create TherapySessionsDTO object based on the action (Save or Update)
            TherapySessionsDTO therapySessionDTO = createTherapySessionDTO(patient, therapy, program, description, sessionDate, id);

            // Save or Update therapy session based on the button text
            if (btnSave.getText().equals("Save")) {
                boolean isSave = therapySessionSchedulingBO.saveTherapySession(therapySessionDTO);

                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapy Session Saved Successfully.").show();
                    refreshPage(); // Refresh the UI to reset or update data
                    loadIds();     // Load updated IDs (if necessary)
                } else {
                    new Alert(Alert.AlertType.ERROR, "Therapy Session Not Saved. Please try again.").show();
                }

            } else if (btnSave.getText().equals("Update")) {
                boolean isUpdate = therapySessionSchedulingBO.updateTherapySession(therapySessionDTO);

                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapy Session Updated Successfully.").show();
                    refreshPage(); // Refresh the UI to reset or update data
                    loadIds();     // Load updated IDs (if necessary)
                } else {
                    new Alert(Alert.AlertType.ERROR, "Therapy Session Not Updated. Please try again.").show();
                }
            }

        } catch (Exception e) {
            // Handle unexpected exceptions
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Unexpected error occurred. Please try again.").show();
        }
    }

    private TherapySessionsDTO createTherapySessionDTO(String patient, String therapy, String program, String description, Date sessionDate, String id) {
        // If editing an existing session, parse the ID
        int therapySessionId = 0;
        if (id != null && !id.isEmpty()) {
            String lid = id.replaceAll("^TS", ""); // Remove "TS" prefix from ID for numeric processing
            try {
                therapySessionId = Integer.parseInt(lid);
            } catch (NumberFormatException e) {
                // Handle invalid session ID format (e.g., non-numeric input)
                new Alert(Alert.AlertType.ERROR, "Invalid Session ID. Please try again.").show();
                throw e; // Rethrow exception to stop further processing
            }
        }

        // Return the TherapySessionsDTO object
        return new TherapySessionsDTO(
                therapySessionId,
                Integer.parseInt(therapy),
                Integer.parseInt(patient), // Convert patient ID to integer
                Integer.parseInt(program), // Convert program ID to integer
                description,               // Therapy session description
                sessionDate                // Therapy session date
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadIds();
        refreshPage();
        setValues();
    }

    private void setValues() {
        try {
            List allPatientId = therapySessionSchedulingBO.getAllPatientId();
            ObservableList<String> patientIds = FXCollections.observableArrayList();
            for (Object value : allPatientId) {
                patientIds.add(value.toString());
            }
            cmbPatientId.setItems(patientIds);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List allProgramId = therapySessionSchedulingBO.getAllProgramId();
            ObservableList<String> programIds = FXCollections.observableArrayList();
            for (Object value : allProgramId) {
                programIds.add(value.toString());
            }
            cmbProgramId.setItems(programIds);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List allTherapistId = therapySessionSchedulingBO.getAllTherapyId(); // Corrected here
            ObservableList<String> therapistIds = FXCollections.observableArrayList();
            for (Object value : allTherapistId) {
                therapistIds.add(value.toString());
            }
            cmbTherapistId.setItems(therapistIds);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() {
        txtSessionDate.clear();
        cmbProgramId.setValue("");
        cmbPatientId.setValue("");
        cmbTherapistId.setValue("");
    }

    private void loadIds() {
        try {
            String lastId = therapySessionSchedulingBO.getLastId();
            lblId.setText(lastId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
