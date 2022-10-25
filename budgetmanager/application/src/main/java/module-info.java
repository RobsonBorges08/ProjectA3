module com.alphateam.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.alphateam.budgetmanager.core;

    opens com.budgetmanager.application to javafx.fxml;
    exports com.budgetmanager.application.controllers;
    exports com.budgetmanager.application;
}
