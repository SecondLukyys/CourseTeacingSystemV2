module com.example._1labdarbas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
    requires spring.context;
    requires spring.web;
    requires gson;
    requires spring.core;

    opens com.example._1labdarbas to javafx.fxml;
    exports com.example._1labdarbas;
    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
    opens model to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports model;
    exports hibControllers;
    opens hibControllers to javafx.fxml;

}