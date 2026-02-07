module com.mobs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.base;

    opens com.mobs to javafx.fxml;
    exports com.mobs;
}