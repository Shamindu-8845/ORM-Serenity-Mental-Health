package com.serenity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbJobRoll;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {
        String value = cmbJobRoll.getValue();
        if (value.equalsIgnoreCase("admin")){
            Window window = cmbJobRoll.getScene().getWindow();
            window.hide();
            Parent load = FXMLLoader.load(getClass().getResource("/view/AdminDashBoard.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();
        }else{
            Window window = cmbJobRoll.getScene().getWindow();
            window.hide();
            Parent load = FXMLLoader.load(getClass().getResource("/view/ReceptionistDashBoard.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> objects = FXCollections.observableArrayList();
        objects.add("Admin");
        objects.add("Recepsionist");
        cmbJobRoll.setItems((objects));
    }
}
