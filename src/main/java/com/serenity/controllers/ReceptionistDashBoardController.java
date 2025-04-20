package com.serenity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionistDashBoardController {

    @FXML
    void onActionPatients(ActionEvent event) {

    }

    @FXML
    void onActionPaymentHistory(ActionEvent event) {

    }

    @FXML
    void onActionPayments(ActionEvent event) {

    }

    @FXML
    void onActionTherapySessions(ActionEvent event) {

    }

    public void onActionLoginForm(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.show();
    }
}
