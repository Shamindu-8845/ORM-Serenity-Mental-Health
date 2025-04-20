package com.serenity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class AdminDashBoardController {

    @FXML
    public AnchorPane optionAnchorPane;

    @FXML
    void onActionChangeCredentails(ActionEvent event) throws IOException{
        Parent load = FXMLLoader.load(getClass().getResource("/view/changeCredentailsForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionPatientHistory(ActionEvent event) throws IOException {
        optionAnchorPane.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/PatientHistoryForm.fxml"));
        optionAnchorPane.getChildren().add(load);
    }

    @FXML
    void onActionTherapist(ActionEvent event) throws IOException {
       optionAnchorPane.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/TherapistForm.fxml"));
        optionAnchorPane.getChildren().add(load);
    }


    @FXML
    void onActionTherapyPrograms(ActionEvent event) throws IOException {
        optionAnchorPane.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/TherapyProgramForm.fxml"));
        optionAnchorPane.getChildren().add(load);
    }

    public void onActionLoginForm(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.show();
    }
}
