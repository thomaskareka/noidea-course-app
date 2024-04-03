module noidea {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens noidea to javafx.fxml;
    exports noidea;

    opens model to javafx.fxml;
    exports model;
}
