package com.serenity;


import com.serenity.config.FactoryConfiguration;
import com.serenity.entity.Users;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppInitializer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.show();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Users users = new Users();
        users.setId(1);
        users.setName("Shamindu");
        users.setPassword("1234");
        users.setJobRole("Admin");

        session.save(users);
        transaction.commit();
        session.close();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
