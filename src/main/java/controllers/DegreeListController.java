package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import noidea.App;
import model.*;

public class DegreeListController implements Initializable {

    @FXML
    private VBox courseBox;

    @FXML
    private ScrollPane coursePane;

    @FXML
    private SplitMenuButton filterButton;

    @FXML
    private HBox searchHBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initList("All");
    }

    private void initList(String filter) {
        courseBox.getChildren().clear();
        filterButton.getItems().clear();

        filterButton.getItems().addAll(makeFilterItem("All"), makeFilterItem("Major"), makeFilterItem("Minor"), makeFilterItem("Application Area"));
        
        ArrayList<Degree> degrees = new ArrayList<>();
        if(filter.equals("All") || filter.equals("Major"))
            degrees.addAll(DegreeList.getInstance().getCategory("major"));
        if(filter.equals("All") || filter.equals("Minor"))
            degrees.addAll(DegreeList.getInstance().getCategory("minor"));
        if(filter.equals("All") || filter.equals("Application Area"))
            degrees.addAll(DegreeList.getInstance().getCategory("applicationArea"));
        
        for(Degree d : degrees) {
            Button b = makeChooseButton(d, d.getType());
            Text t = new Text(d.toString());
            t.setTextAlignment(TextAlignment.LEFT);
            t.setWrappingWidth(1200);
            String title = String.format("%s - %s (%s credits)", d.getType(), d.getTitle(), d.getCredits());
            TitledPane pane = new TitledPane(title, new VBox(b, t));
            pane.setExpanded(false);
            
            courseBox.getChildren().add(pane);
        }
    }

    private MenuItem makeFilterItem(String type) {
        MenuItem item = new MenuItem(type);
        item.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                initList(type);
            }
        });
        return item;
    }

    private Button makeChooseButton(Degree m, String type) {
        Button b = new Button();
        if(type.equals("major")) {
            b.setText("Set as Major");
            b.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    App.system.setStudentMajor(m.getTitle());
                }
            });
        } else if (type.equals("minor")) {
            b.setText("Set as Minor");
            b.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    App.system.setStudentMinor(m.getTitle());
                }
            });
        } else {
            b.setText("Set as Application Area");
            b.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    App.system.setStudentApplicationArea(m.getTitle());
                }
            });
        }
        return b;
    }

}
