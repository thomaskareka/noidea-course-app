package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import noidea.App;
import model.*;

public class AdvisorHomeController implements Initializable {

    @FXML
    private VBox studentBox;

    @FXML
    private Spinner<Integer> pageSpinner;

    @FXML
    private TextField studentField;

    @FXML
    private ScrollPane studentPane;

    @FXML
    private CheckBox allStudentButton;

    @FXML
    private NavigationBarController NavigationBarController;

    @FXML @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudents(1, "", false);
        pageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000));
        pageSpinner.valueProperty().addListener((o, oldPage, newPage) -> loadStudents(newPage, studentField.getText(), allStudentButton.isSelected()));
    }

    @FXML
    void studentSearchButtonPressed(ActionEvent event) {
        pageSpinner.getValueFactory().setValue(1);
        loadStudents(1, studentField.getText(), allStudentButton.isSelected());
    }

    private void loadStudents(int page, String search, boolean all) {

        studentBox.getChildren().clear();
        if(all) {  // search all students to add them
            List<Student> foundStudents = App.system.getStudentsFromSearch(page - 1, search);
            for(Student s : foundStudents) {
                Text t = new Text("Current advisor: " + s.getAdvisorReference());
                t.setWrappingWidth(1200);
                t.setTextAlignment(TextAlignment.LEFT);

                Button addButton = new Button("Add to Student List");
                addButton.setOnAction(event -> addStudentButton(s));

                VBox studentV = new VBox(t, addButton);
                TitledPane pane = new TitledPane(s.getFirstName() + " " + s.getLastName() + " - " + s.getStudentID(), studentV);
                pane.setExpanded(false);
                studentBox.getChildren().add(pane);
            }
        } else {  // only students that you can view and modify
            List<Student> foundStudents = App.system.getAdvisorsStudentsFromSearch(page - 1, search);
            for(Student s : foundStudents) {
                String studentLabelText = "";
                if(s.getFailureRisk()) {
                    studentLabelText += "❗";
                }
                studentLabelText += s.getFirstName() + " " + s.getLastName() + " - " + s.getStudentID();

                Text t = new Text(s.toString());
                t.setWrappingWidth(1200);
                t.setTextAlignment(TextAlignment.LEFT);

                Button chooseButton = new Button("Choose as active student");
                chooseButton.setOnAction(event -> chooseActiveStudentButton(s.getID()));

                VBox studentV = new VBox(t, chooseButton);
                TitledPane pane = new TitledPane(studentLabelText, studentV);
                pane.setExpanded(false);
                studentBox.getChildren().add(pane);
            }
        }
    }

    private void addStudentButton(Student s) {
        if(App.system.addAdvisee(s)) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setHeaderText("Student succesfully added!");
            a.setContentText(s.getFirstName() + " " + s.getLastName() + " has been added to your student list.");
            a.show();
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Error");
            a.setContentText(s.getFirstName() + " " + s.getLastName() + " could not be added to your student list.");
            a.show();
        }
    }

    private void chooseActiveStudentButton(UUID s) {
        if(App.system.chooseActiveStudent(s)) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setHeaderText("Student succesfully chosen!");
            a.setContentText(App.system.getStudent().getFirstName());
            a.show();
            NavigationBarController.refresh();
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Choosing student failed!");
            a.setContentText("An unknown error occured.");
            a.show();
        }
    }
}
