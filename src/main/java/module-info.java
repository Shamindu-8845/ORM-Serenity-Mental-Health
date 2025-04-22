module Serenity.Mental.Health {
    requires javafx.fxml;
    requires javafx.controls;
    //not include the jfoenix
    requires org.hibernate.orm.core;
    // not requires jakarta.persistence
    requires java.naming;
    requires java.sql;
    requires jakarta.persistence;
    requires static lombok;
    requires com.jfoenix;
    requires jbcrypt;

    opens com.serenity.controllers to javafx.fxml;
    opens com.serenity to javafx.fxml;
    opens com.serenity.entity to org.hibernate.orm.core;
    opens com.serenity.tm to javafx.base;


    exports com.serenity;

}