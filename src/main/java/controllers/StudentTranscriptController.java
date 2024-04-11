package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import noidea.App;

public class StudentTranscriptController implements Initializable  {

    @FXML
    private VBox courseBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Text t = new Text(App.system.createUserTranscript());
        t.setWrappingWidth(1200);
        t.setTextAlignment(TextAlignment.CENTER);
        courseBox.getChildren().add(t);
    }
}
