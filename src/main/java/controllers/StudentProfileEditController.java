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

public class StudentProfileEditController implements Initializable{

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField studentIDField;

    @FXML
    private TextField passwordField;

    @FXML 
    private TextField conPasswordField;

    @FXML
    void saveBtnPressed(ActionEvent event) {
        Student s = App.system.getStudent();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String studentID = studentIDField.getText();
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

        s.setFirstName(firstName);
        s.setLastName(lastName);
        s.setEmail(email);
        s.setStudentID(studentID);
        s.setPassword(password);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFields();
    }

    private void initFields() {
        Student s = App.system.getStudent();
        firstNameField.setText(s.getFirstName());
        lastNameField.setText(s.getLastName());
        emailField.setText(s.getEmail());
        studentIDField.setText(s.getStudentID());

    }

}


