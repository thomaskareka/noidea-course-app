package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import noidea.App;
import model.*;

public class AdvisorProfileController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Advisor advisor = App.system.getAdvisor();
        if (advisor != null) {
            populateAdvisorProfile(advisor);
        }
    }

    private void populateAdvisorProfile(Advisor advisor) {
        firstNameField.setText(advisor.getFirstName());
        lastNameField.setText(advisor.getLastName());
        emailField.setText(advisor.getEmail());
        passwordField.setText(advisor.getPassword());
        
    }

}