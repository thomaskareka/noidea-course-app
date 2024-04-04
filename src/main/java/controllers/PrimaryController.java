package controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import noidea.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
