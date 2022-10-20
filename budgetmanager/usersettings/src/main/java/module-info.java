module com.alphateam.usersettings {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.alphateam.usersettings to javafx.fxml;
    exports com.alphateam.usersettings;
}
