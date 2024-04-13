package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import noidea.App;
import model.*;

public class NavigationBarController implements Initializable {
    @FXML
    private Menu menuAccount;

    @FXML
    private Menu menuGoTo;

    @FXML
    private Menu studentMenu;

    @FXML
    private MenuBar navBar;


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
        MenuItem transcriptItem = makeButton("Student Transcript", "studentTranscript");

        
        MenuItem profileItem = makeButton("Profile", "studentProfile");
        MenuItem editItem = makeButton("Edit Profile", "editStudentProfile");

        MenuItem logoutButton = makeLogoutButton();

        menuGoTo.getItems().addAll(noteItem, mapItem, progressItem, catalogItem, transcriptItem);
        menuAccount.getItems().addAll(profileItem, editItem, logoutButton);
    }

    private void initAdvisorMenu() {
        MenuItem homeItem = makeButton("Home", "advisorHome");
        MenuItem courseItem = makeButton("Course Listing", "courseListing");
        

        MenuItem profileItem = makeButton("Profile", "advisorProfile");
        MenuItem editItem = makeButton("Edit Profile", "editAdvisorProfile");

        MenuItem logoutButton = makeLogoutButton();
        
        menuGoTo.getItems().addAll(homeItem, courseItem);
        menuAccount.getItems().addAll(profileItem, editItem, logoutButton);

        Student s = App.system.getStudent();
        if(s != null) {
            studentMenu = new Menu(s.getFirstName() + " " + s.getLastName());

            MenuItem deselectItem = makeDeselectStudentButton();

            studentMenu.getItems().addAll(deselectItem);

            navBar.getMenus().add(studentMenu);
        }
    }

    public void refresh() {
        menuAccount.getItems().clear();
        menuGoTo.getItems().clear();
        initAdvisorMenu();
    }

    private MenuItem makeDeselectStudentButton() {
        MenuItem button = new MenuItem("Deselect");
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a) {
                navBar.getMenus().remove(studentMenu);
                App.system.removeActiveStudent();
                refresh();
                
                Alert al = new Alert(AlertType.INFORMATION);
                al.setHeaderText("Student succesfully deselected.");
                al.setContentText("Their information has been saved.");
                al.show();
            }
        });
        return button;
    }
}