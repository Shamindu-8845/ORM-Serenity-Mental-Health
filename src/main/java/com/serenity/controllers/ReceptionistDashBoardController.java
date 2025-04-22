package com.serenity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionistDashBoardController {

    @FXML
    private AnchorPane optionAnchorPane;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPassword;

    @FXML
    void onActionPatients(ActionEvent event) throws IOException {
        optionAnchorPane.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/PatientForm.fxml"));
        optionAnchorPane.getChildren().add(load);
    }

    @FXML
    void onActionPaymentHistory(ActionEvent event) throws IOException {
        optionAnchorPane.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/PatientTherapyHistory.fxml"));
        optionAnchorPane.getChildren().add(load);
    }

    @FXML
    void onActionPayments(ActionEvent event) throws IOException {
        optionAnchorPane.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/PaymentForm.fxml"));
        optionAnchorPane.getChildren().add(load);
    }

    @FXML
    void onActionTherapySessions(ActionEvent event) throws IOException {
        optionAnchorPane.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/TherapySessionForm.fxml"));
        optionAnchorPane.getChildren().add(load);
    }

    public void onActionLoginForm(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.show();
    }

    public void setValues(String name, String password) {
        lblName.setText(name);
        lblPassword.setText(password);
    }
}
