module com.alphateam.application {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.alphateam.application to javafx.fxml;
    exports com.alphateam.application;
}
