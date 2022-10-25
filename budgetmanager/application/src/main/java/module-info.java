module com.alphateam.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.alphateam.budgetmanager.core;

    opens com.alphateam.application to javafx.fxml;
    exports com.budgetmanager.application.controllers;
    exports com.alphateam.application;
}
