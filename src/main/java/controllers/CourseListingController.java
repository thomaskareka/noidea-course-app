package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import noidea.App;
import model.*;

public class CourseListingController implements Initializable {

    @FXML
    private ScrollPane coursePane;
    
    @FXML
    private VBox courseBox;

    @FXML
    private TextField courseField;

    @FXML
    private Spinner<Integer> pageSpinner;

    @FXML @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCourses(1, "");
        pageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000));
        pageSpinner.valueProperty().addListener((o, oldPage, newPage) -> loadCourses(newPage, courseField.getText()));
    }
    
    @FXML
    void courseSearchButtonPressed(ActionEvent event) {
        pageSpinner.getValueFactory().setValue(1);
        loadCourses(1, courseField.getText());
    }

    private void loadCourses(int page, String search) {
        courseBox.getChildren().clear();
        List<Course> foundCourses = App.system.getCoursesFromSearch(page - 1, search);
        for(Course c : foundCourses) {
            Text t = new Text(c.toString());
            t.setWrappingWidth(1200);
            t.setTextAlignment(TextAlignment.LEFT);
            TitledPane pane = new TitledPane(c.getIdentifier() + " - " + c.getName(), t);
            pane.setExpanded(false);
            courseBox.getChildren().add(pane);
        }
    }
}