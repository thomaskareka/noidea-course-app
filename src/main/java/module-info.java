module noidea {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.graphics;

    opens noidea to javafx.fxml;
    exports noidea;

    opens controllers to javafx.fxml;
    exports controllers;

    opens model to javafx.fxml;
    exports model;
}
