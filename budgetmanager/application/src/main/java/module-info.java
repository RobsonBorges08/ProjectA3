module com.alphateam.application {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.budgetmanager.application to javafx.fxml;
    exports com.budgetmanager.application;
}
