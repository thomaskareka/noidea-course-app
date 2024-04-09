package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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

    private void initStudentMenu() {
        menuAccount.getItems().add(new MenuItem("Student"));
    }

    private void initAdvisorMenu() {
        menuAccount.getItems().add(new MenuItem("Advisor"));
    }
}