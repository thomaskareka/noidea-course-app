package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void doLogin(ActionEvent event) { // TODO, error handlers, scene transition
        CourseSystem system = CourseSystem.getInstance();
        String email = menuEmailField.getText();
        String password = menuPasswordField.getText();

        if(system.login(email, password)) {
            System.out.println("succesfully signed in");
            system.printActiveUser();
        } else {
            System.out.println("signin failed");
        }
    }

    @FXML
    void switchToSignup(ActionEvent event) throws IOException {
        System.out.println("Switching to Signup");
        App.setRoot("signup");
    }

}
