package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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

    @FXML
    private ScrollPane progressPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupLabels();
        setupProgress();
    }

    private void setupProgress() {
        setupCategory("major");
        setupCategory("minor");
        setupCategory("app");
    }

    private void setupCategory(String category) {
        Student student = App.system.getStudent();
        ArrayList<DegreeRequirement> drList = App.system.getCategoryRequirements(category);

        if (drList.isEmpty())
            return;
        
        String paneTitle = category;
        
        if(category.equals("major")) {
            paneTitle = student.getMajor();
        } else if(category.equals("minor")) {
            paneTitle = student.getMinor();
        } else {
            paneTitle = student.getApplicationArea();
        }

        TitledPane pane = new TitledPane(paneTitle, null);
        pane.setExpanded(false);

        ArrayList<String> courseStrings = student.getCompleteCourses();
        ArrayList<Course> courses = new ArrayList<Course>();
        CourseList cl = CourseList.getInstance();
        for(String s : courseStrings) {
            courses.add(cl.getCourseByIdentifer(s));
        }

        VBox internalBox = new VBox();

        for(DegreeRequirement dr : drList) {
            Text t = new Text(dr.calculateRequirement(courses));
            t.setWrappingWidth(1200);
            t.setTextAlignment(TextAlignment.LEFT);
            TitledPane internalPane = new TitledPane(dr.getCategory(), t);
            internalPane.setExpanded(false);
            internalBox.getChildren().add(internalPane);
        }
        pane.setContent(internalBox);
        progressBox.getChildren().add(pane);
    }

    private void setupLabels() {
        Student s = App.system.getStudent();
        applicationAreaText.setText("Application Area: " + s.getApplicationArea());
        gpaText.setText(String.format("GPA: %.3f (%.3f Major GPA)", s.getOverallGPA(), s.getMajorGPA()));
        majorText.setText("Major: " + s.getMajor());
        minorText.setText("Minor: " + s.getMinor());
        progressDisplay.setProgress(s.getDegreePercentage());
    }

}
