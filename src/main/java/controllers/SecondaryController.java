package controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import noidea.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}