module com.alphateam.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.alphateam.application to javafx.fxml;
    exports com.alphateam.application;
}
