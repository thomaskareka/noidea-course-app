package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.Student;
import noidea.App;

public class StudentNotesController implements Initializable{
    @FXML
    private TextArea studentNotesField;

    @FXML @Override
    public void initialize(URL location, ResourceBundle resources) {
            Student student = App.system.getStudent();
            if (student != null) {
                populateStudentNotes(student);
            }
        }
    
    private void populateStudentNotes(Student student) {
        String notesText = String.join("\n", student.getNotes());
        studentNotesField.setText(notesText);
    }
}
