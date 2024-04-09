package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.*;
import noidea.App;

public class StudentProfileController implements Initializable {
        @FXML
        private TextField firstNameField;
        @FXML
        private TextField lastNameField;
        @FXML
        private TextField emailField;
        @FXML
        private TextField studentIdField;
        @FXML
        private TextField majorField;
        @FXML
        private TextField minorField;
        @FXML
        private TextField classLevelField;
        @FXML
        private TextField applicationAreaField;
        @FXML
        private TextField majorGPAField;
        @FXML
        private TextField overallGPAField;

        @FXML @Override
        public void initialize(URL location, ResourceBundle resources) {
            Student student = App.system.getStudent();
            if (student != null) {
                populateStudentProfile(student);
            }
        }

        private void populateStudentProfile(Student student) {
            firstNameField.setText(student.getFirstName());
            lastNameField.setText(student.getLastName());
            emailField.setText(student.getEmail());
            studentIdField.setText(student.getStudentID());
            majorField.setText(student.getMajor());
            minorField.setText(student.getMinor());
            classLevelField.setText(student.getClassLevel());
            applicationAreaField.setText(student.getApplicationArea());
            majorGPAField.setText(String.valueOf(student.getMajorGPA()));
            overallGPAField.setText(String.valueOf(student.getOverallGPA()));
        }
    }
