package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import noidea.App;
import model.*;

public class DegreeProgressController implements Initializable {

    @FXML
    private Text applicationAreaText;

    @FXML
    private Text gpaText;

    @FXML
    private Text majorText;

    @FXML
    private Text minorText;

    @FXML
    private VBox progressBox;

    @FXML
    private ProgressIndicator progressDisplay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupLabels();
    }

    private void setupLabels() {
        Student s = App.system.getStudent();
        applicationAreaText.setText("Application Area: " + s.getApplicationArea());
        gpaText.setText(String.format("GPA: %s (%s)", s.getOverallGPA(), s.getMajorGPA()));
        majorText.setText("Major: " + s.getMajor());
        minorText.setText("Minor: " + s.getMinor());
        progressDisplay.setProgress(s.getDegreePercentage());
    }

}
