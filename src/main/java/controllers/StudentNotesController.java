package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.Student;
import noidea.App;

public class StudentNotesController implements Initializable{
    @FXML
    private TextArea studentNotesField;
    @FXML
    private VBox vbox;

    @FXML @Override
    public void initialize(URL location, ResourceBundle resources) {
            Student student = App.system.getStudent();
            if (student != null) {
                populateStudentNotes(student);
            }
            VBox.setMargin(studentNotesField, new Insets(20, 20, 20, 20));
        }
    
    private void populateStudentNotes(Student student) {
        String notesText = String.join("\n", student.getNotes());
        studentNotesField.setText(notesText);
    }
}
