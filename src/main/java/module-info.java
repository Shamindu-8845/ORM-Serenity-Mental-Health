module Serenity.Mental.Health {
    requires javafx.fxml;
    requires javafx.controls;
    //not include the jfoenix
    requires org.hibernate.orm.core;
    // not requires jakarta.persistence
    requires java.naming;
    requires java.sql;

    opens com.serenity.controllers to javafx.fxml;
    opens com.serenity to javafx.fxml;

    exports com.serenity;

}