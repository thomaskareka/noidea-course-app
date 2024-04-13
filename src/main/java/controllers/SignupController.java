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
    void doSignup(ActionEvent event) throws IOException {
        CourseSystem system = App.system;
        String email = menuEmailField.getText();
        String firstName = menuFirstField.getText();
        String lastName = menuLastField.getText();
        String password = menuPasswordField.getText();
        boolean type = !menuAdvisorCheckbox.isSelected();

        Alert a = new Alert(AlertType.ERROR);

        if(email.equals("") || firstName.equals("") || lastName.equals("")) {
            a.setHeaderText("All fields must be filled.");
            a.show();
            return;
        }
        
        if(!password.equals(menuConfirmField.getText())) {
            a.setHeaderText("Passwords must match!");
            a.show();
            return;
        } else if(password.length() < 8) {
            a.setHeaderText("Passwords must be 8 characters or longer!");
            a.show();
            return;
        }

        if(system.signUp(type, firstName, lastName, email, password)) {
            System.out.println("Successfully signed up. Current user:");
            system.printActiveUser();
            if(type) {  // signed up as student
                App.setRoot("degreeProgress");
            } else {  // signed up as advisor
                App.setRoot("advisorHome");
            }
        } else {
            a.setHeaderText("Signup failed...");
            a.show();
            return;
        }
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        System.out.println("Switching to Login");
        App.setRoot("login");
    }

}
