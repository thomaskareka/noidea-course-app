package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import noidea.App;
import model.*;

public class NavigationBarController implements Initializable {
    @FXML
    private Menu menuAccount;

    @FXML
    private Menu menuGoTo;

    @FXML @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(App.system.getUserType().contains("Student")) {
            initStudentMenu();
        } else {
            initAdvisorMenu();
        }
    }

    private MenuItem makeButton(String text, String goTo) {
        MenuItem button = new MenuItem(text);
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent a) {
                try {
                    App.setRoot(goTo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return button;
    }

    private MenuItem makeLogoutButton() {
        MenuItem button = new MenuItem("Sign Out");
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent a) {
                try {
                    App.system.logout();
                    App.setRoot("login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return button;
    }

    private void initStudentMenu() {
        MenuItem noteItem = makeButton("Notes", "notes");
        MenuItem mapItem = makeButton("Major Map", "majorMap");
        MenuItem progressItem = makeButton("Degree Progress", "degreeProgress");
        MenuItem catalogItem = makeButton("Course Catalog", "courseListing");
        
        MenuItem profileItem = makeButton("Profile", "studentProfile");
        MenuItem editItem = makeButton("Edit Profile", "editStudentProfile");

        MenuItem logoutButton = makeLogoutButton();

        menuGoTo.getItems().addAll(noteItem, mapItem, progressItem, catalogItem);
        menuAccount.getItems().addAll(profileItem, editItem, logoutButton);
    }

    private void initAdvisorMenu() {
        //TODO
    }
}