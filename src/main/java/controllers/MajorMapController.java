package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import noidea.App;
import model.*;

public class MajorMapController implements Initializable {

    @FXML
    private ScrollPane majorPane;

    @FXML
    private VBox majorBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMap();
    }

    private void initMap() {
        ArrayList<String> plan = App.system.getEightSemesterPlanList();

        for(int i = 0; i < plan.size(); i++) {
            String s = plan.get(i);
            String internalS = s.substring(s.indexOf('\n')+1);

            Text t = new Text(internalS);
            t.setWrappingWidth(1200);
            TitledPane pane = new TitledPane(s.substring(0, s.indexOf('\n')), t);
            pane.setExpanded(false);
            pane.setStyle("-fx-text-fill: #FFFFFF;");
            pane.setStyle("-fx-color: #73000A;");
            majorBox.getChildren().add(pane);
        }

        majorPane.setContent(majorBox);
    }

}
