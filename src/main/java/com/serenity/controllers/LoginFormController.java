package com.serenity.controllers;

import com.serenity.bo.BOFactory;
import com.serenity.bo.custom.LoginBO;
import com.serenity.dto.UsersDTO;
import com.serenity.util.PasswordUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbJobRoll;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnLogin;

    /*@FXML
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
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> objects = FXCollections.observableArrayList();
        objects.add("Admin");
        objects.add("Receptionist");
        cmbJobRoll.setItems((objects));
    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {
        // Get user input
        String name = txtUsername.getText();
        String password = txtPassword.getText();
        String value = cmbJobRoll.getValue();

        // Validate empty fields
        if (name == null || password == null || value == null || name.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
            return;
        }

        // Get LoginBO to check user
        LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
        List<UsersDTO> usersDTOS = loginBO.checkUser(new UsersDTO(name, password, value));

        boolean isAvailable = false;
        String hashPassword = null;

        // Check user credentials
        for (UsersDTO usersDTO : usersDTOS) {
            if (PasswordUtil.checkpassword(password, usersDTO.getPassword())) {
                isAvailable = true;
                hashPassword = usersDTO.getPassword();
                break;
            }
        }

        // If credentials are valid
        if (isAvailable) {
            // Close current window
            Window window = btnLogin.getScene().getWindow();
            window.hide();

            // Load appropriate dashboard
            FXMLLoader fxmlLoader = null;
            Parent load = null;

            if ("Admin".equals(value)) {
                // Make sure the location of AdminDashBoard.fxml is correct
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminDashBoard.fxml"));
                load = fxmlLoader.load();
                AdminDashBoardController controller = fxmlLoader.getController();
                controller.setValue(name, hashPassword);
            } else if ("Receptionist".equals(value)) {
                // Ensure the path of ReceptionistDashBoard.fxml is correct
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/ReceptionistDashBoard.fxml"));
                load = fxmlLoader.load();
                ReceptionistDashBoardController controller = fxmlLoader.getController();
                controller.setValues(name, hashPassword);
            }

            if (load != null) {
                Stage stage = new Stage();
                Scene scene = new Scene(load);
                stage.setScene(scene);
                stage.show();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Credentials").show();
        }
    }


}
