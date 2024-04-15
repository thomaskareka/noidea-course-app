package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import noidea.App;
import model.*;

public class LoginController {

    @FXML
    private CheckBox menuAdvisorButton;

    @FXML
    private TextField menuEmailField;

    @FXML
    private Button menuLoginButton;

    @FXML
    private PasswordField menuPasswordField;

    @FXML
    private Button menuSignupButton;

    @FXML
    void doLogin(ActionEvent event) throws IOException { // TODO, error handlers, scene transition
        CourseSystem system = App.system;
        String email = menuEmailField.getText();
        String password = menuPasswordField.getText();
        Alert a = new Alert(AlertType.ERROR);

        if(system.login(email, password)) {
            if(system.getUserType().contains("Student")) {
                App.setRoot("degreeProgress");
            } else {
                System.out.println(system.getUserType());
                App.setRoot("advisorHome");
            }
        } else {
           a.setHeaderText("Signin failed!");
           a.setContentText("Check your email and password again.");
           a.show(); 
        }
    }

    @FXML
    void switchToSignup(ActionEvent event) throws IOException {
        System.out.println("Switching to Signup");
        App.setRoot("signup");
    }

}
