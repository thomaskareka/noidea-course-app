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

public class SignupController {

    @FXML
    private CheckBox menuAdvisorCheckbox;

    @FXML
    private Button menuBackButton;

    @FXML
    private PasswordField menuConfirmField;

    @FXML
    private TextField menuEmailField;

    @FXML
    private TextField menuFirstField;

    @FXML
    private TextField menuLastField;

    @FXML
    private PasswordField menuPasswordField;

    @FXML
    private Button menuSignupButton;

    @FXML
    void doSignup(ActionEvent event) {  // TODO: make error handlers show text, transition scene
        CourseSystem system = CourseSystem.getInstance();
        String email = menuEmailField.getText();
        String firstName = menuFirstField.getText();
        String lastName = menuLastField.getText();
        String password = menuPasswordField.getText();
        boolean type = !menuAdvisorCheckbox.isSelected();

        if(!password.equals(menuConfirmField.getText())) {
            System.out.println("passwords must match");
            return;
        } else if(password.length() < 8) {
            System.out.println("passwords must be 8 characters or longer");
            return;
        }

        if(email.equals("") || firstName.equals("") || lastName.equals("")) {
            System.out.println("fields can not be blank");
            return;
        }

        if(system.signUp(type, firstName, lastName, email, password)) {
            System.out.println("Successfully signed up. Current user:");
            system.printActiveUser();
        } else {
            System.out.println("Signup failed!");
            return;
        }
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        System.out.println("Switching to Login");
        App.setRoot("login");
    }

}
