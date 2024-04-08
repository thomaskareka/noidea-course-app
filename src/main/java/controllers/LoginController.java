package controllers;

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
    void doLogin(ActionEvent event) {
        System.out.println("Login");
    }

    @FXML
    void switchToSignup(ActionEvent event) {
        System.out.println("Signup");
    }

}
