package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AdvisorHomeController {

    @FXML
    private VBox courseBox;

    @FXML
    private Spinner<Integer> pageSpinner;

    @FXML
    private TextField studentField;

    @FXML
    private ScrollPane studentPane;

    @FXML
    void studentSearchButtonPressed(ActionEvent event) {

    }

}
