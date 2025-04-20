module Serenity.Mental.Health {
    requires javafx.fxml;
    requires javafx.controls;
    //not include the jfoenix

    opens com.serenity.controllers to javafx.fxml;

    exports com.serenity;

}