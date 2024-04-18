package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import noidea.App;
import model.*;

public class AdvisorProfileEditController implements Initializable{

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField conPasswordField;

    @FXML
    void saveBtnPressed(ActionEvent event) {
        Advisor a = App.system.getAdvisor();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String conPassword = conPasswordField.getText();

        Alert alert = new Alert(AlertType.ERROR);

        if(password.length() < 8) {
            alert.setHeaderText("Information NOT saved!\nPasswords must be 8 characters or longer!");
            alert.show();
            return;
        }
        
        if(!password.equals(conPassword)) {
            alert.setHeaderText("Information NOT saved!\nPasswords must match!");
            alert.show();
            return;
        }

        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setEmail(email);
        a.setPassword(password);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFields();
    }

    private void initFields() {
        Advisor a = App.system.getAdvisor();
        firstNameField.setText(a.getFirstName());
        lastNameField.setText(a.getLastName());
        emailField.setText(a.getEmail());

    }

}


