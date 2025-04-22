package com.serenity.controllers;

import com.serenity.bo.BOFactory;
import com.serenity.bo.custom.ChangeCredentialsBO;
import com.serenity.bo.exception.InvalidCredentialsException;
import com.serenity.dto.UsersDTO;
import com.serenity.util.PasswordUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeCredentailsFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbJobRoll;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtpassword;

    ChangeCredentialsBO changeCredentialsBO = (ChangeCredentialsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CHANGE_CREDENTIALS);

    @FXML
    void onActionSave(ActionEvent event) {
        String name = txtname.getText();
        String password = txtpassword.getText();
        String role = cmbJobRoll.getValue().toString();

        if (name == null || password == null || role == null) {
            new Alert(Alert.AlertType.ERROR, "Missing Fields.").show();
            throw new NullPointerException("Input Fields are Empty..");
        }

        String hashPassword = PasswordUtil.hashPassword(password);

        try {
            boolean isSave = changeCredentialsBO.saveCredentials(new UsersDTO(name, hashPassword, role));
            if (isSave) {
                new Alert(Alert.AlertType.INFORMATION, "User Credentials Saved Successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "User Credentials Not Saved.").show();
                throw new InvalidCredentialsException("Invalid Credentials..");
            }
        } catch (IOException | InvalidCredentialsException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> objects = FXCollections.observableArrayList();
        objects.add("Admin");
        objects.add("Receptionist");
        cmbJobRoll.setItems((objects));
    }
}
