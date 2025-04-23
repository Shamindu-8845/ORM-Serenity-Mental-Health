package com.serenity.controllers;

import com.serenity.bo.BOFactory;
import com.serenity.bo.custom.PaymentInvoiceManagementBO;
import com.serenity.dto.PaymentsDTO;
import com.serenity.tm.PaymentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;


public class PaymentFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbPatient;

    @FXML
    private ComboBox<String> cmbTherapy;

    @FXML
    private TableColumn<PaymentTm,Double> colAmount;

    @FXML
    private TableColumn<PaymentTm,Integer> colId;

    @FXML
    private TableColumn<PaymentTm,Integer> colPatientId;

    @FXML
    private TableColumn<PaymentTm,Integer> colTherapyId;

    @FXML
    private TableColumn<PaymentTm, Date> coldate;

    @FXML
    private TableColumn<PaymentTm,String> colmethod;

    @FXML
    private Label lblPaymentId;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TableColumn<PaymentTm,String> colStatus;

    @FXML
    private TextField txtamount;

    @FXML
    private TextField txtdate;

    @FXML
    private TextField txtmethod;

    @FXML
    private TextField txtStatus;

    @FXML
    private Button btnSave;

    PaymentInvoiceManagementBO paymentBO = (PaymentInvoiceManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENTS);


    @FXML
    void onActionReset(ActionEvent event) {
        refreshPage();
        loadTables();
        loadIds();
        btnSave.setText("Save");
    }

    @FXML
    void onActionSave(ActionEvent event) {
        // Get values from UI fields
        String payments = txtamount.getText();
        String status = txtStatus.getText();
        String patientId = cmbPatient.getValue();
        String therapyId = cmbTherapy.getValue();
        Date date = Date.valueOf(txtdate.getText()); // assuming correct date format is input (YYYY-MM-DD)

        try {
            // Create PaymentsDTO object
            PaymentsDTO dto = new PaymentsDTO(
                    0, // Pass 0 for a new payment (auto-generated ID will be handled by DB)
                    Integer.parseInt(therapyId),
                    Integer.parseInt(patientId),
                    Double.parseDouble(payments),
                    status,
                    Date.valueOf(txtdate.getText()), // Convert text to Date
                    txtmethod.getText() // Method value from txtmethod field
                     // assuming method is taken from txtmethod field
            );

            // Check if it's a new payment or an update
            if (btnSave.getText().equals("Save")) {
                // Save new payment
                boolean isSave = paymentBO.savePayment(dto);
                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION, "Payment Saved Successfully.").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Payment Not Saved.").show();
                }
            } else if (btnSave.getText().equals("Update")) {
                // Update existing payment (retrieve ID from label and set it in DTO)
                int id = Integer.parseInt(lblPaymentId.getText().replace("P", ""));
                dto.setId(id); // Assuming PaymentsDTO has a setter for ID

                boolean isUpdate = paymentBO.updatePayment(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Payment Updated Successfully.").show();
                    btnSave.setText("Save"); // Reset button text to "Save"
                } else {
                    new Alert(Alert.AlertType.ERROR, "Payment Not Updated.").show();
                }
            }

            // Refresh page, load tables, and load IDs
            refreshPage();
            loadTables();
            loadIds();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTherapyId.setCellValueFactory(new PropertyValueFactory<>("therapy"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colmethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        loadTables();
        loadIds();
        setValues();
        refreshPage();
    }

    private void refreshPage() {
        txtamount.clear();
        txtStatus.clear();
        cmbTherapy.setValue("");
        cmbPatient.setValue("");
        txtdate.clear();
        txtStatus.clear();
        txtmethod.clear();
    }

    private void setValues() {
        try {
            List allPatientsId = paymentBO.getAllPatientsId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allPatientsId){
                objects.add(value.toString());
            }
            cmbPatient.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List allTherapyId = paymentBO.getAllTherapyId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allTherapyId){
                objects.add(value.toString());
            }
            cmbTherapy.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   /* private void loadTables() {
        try {
            // Fetch all payments from the PaymentBO
            List<PaymentsDTO> allPayments = paymentBO.getAllPayments();

            // Create an observable list to hold the data for the TableView
            ObservableList<PaymentTm> objects = FXCollections.observableArrayList();

            // Convert PaymentsDTO to PaymentTm and add to the list
            for (PaymentsDTO paymentsDTO : allPayments) {
                objects.add(new PaymentTm(
                        paymentsDTO.getTheropy(),       // therapy
                        paymentsDTO.getPatient(),       // patient
                        paymentsDTO.getPayment(),       // payment
                        paymentsDTO.getStatus(),        // status
                        paymentsDTO.getDate(),          // date
                        paymentsDTO.getMethod()         // method
                ));
            }

            // Set the items in the TableView
            tblPayment.setItems(objects);

            // Bind the TableView columns with the properties in PaymentTm
            TableColumn<PaymentTm, Integer> therapyColumn = new TableColumn<>("Therapy");
            therapyColumn.setCellValueFactory(new PropertyValueFactory<>("therapy"));

            TableColumn<PaymentTm, Integer> patientColumn = new TableColumn<>("Patient");
            patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));

            TableColumn<PaymentTm, Double> paymentColumn = new TableColumn<>("Payment");
            paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));

            TableColumn<PaymentTm, String> statusColumn = new TableColumn<>("Status");
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

            TableColumn<PaymentTm, Date> dateColumn = new TableColumn<>("Date");
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            TableColumn<PaymentTm, String> methodColumn = new TableColumn<>("Method");
            methodColumn.setCellValueFactory(new PropertyValueFactory<>("method"));

            // Add the columns to the TableView (assuming the TableView is set up properly)
            tblPayment.getColumns().clear();  // Clear any previous columns
            tblPayment.getColumns().addAll(therapyColumn, patientColumn, paymentColumn, statusColumn, dateColumn, methodColumn);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    private void loadTables() {
        try {
            // Fetch all payments from the PaymentBO
            List<PaymentsDTO> allPayments = paymentBO.getAllPayments();

            // Create an observable list to hold the data for the TableView
            ObservableList<PaymentTm> objects = FXCollections.observableArrayList();

            // Convert PaymentsDTO to PaymentTm and add to the list
            allPayments.forEach(paymentsDTO -> objects.add(
                    new PaymentTm(
                            paymentsDTO.getTheropy(),       // therapy
                            paymentsDTO.getPatient(),       // patient
                            paymentsDTO.getPayment(),       // payment
                            paymentsDTO.getMethod(),       // status
                            paymentsDTO.getDate(),          // date
                            paymentsDTO.getStatus()// method
                    )
            ));

            // Set the items in the TableView
            tblPayment.setItems(objects);

            // Define and set the TableView columns with PropertyValueFactory
            tblPayment.getColumns().setAll(
                    createColumn("Therapy", "therapy", Integer.class),
                    createColumn("Patient", "patient", Integer.class),
                    createColumn("Payment", "payment", Double.class),
                    createColumn("Status", "status", String.class),
                    createColumn("Date", "date", Date.class),
                    createColumn("Method", "method", String.class)
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to create a TableColumn dynamically
    private <T> TableColumn<PaymentTm, T> createColumn(String header, String property, Class<T> type) {
        TableColumn<PaymentTm, T> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }


    private void loadIds() {
        try {
            String id = paymentBO.getlastId();
            lblPaymentId.setText(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
