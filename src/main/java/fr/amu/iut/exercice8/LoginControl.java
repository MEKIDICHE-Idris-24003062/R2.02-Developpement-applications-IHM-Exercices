package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControl {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void okClicked() {
        String username = usernameField.getText();
        String passwordStars = "*".repeat(passwordField.getText().length());
        System.out.println("Username: " + username);
        System.out.println("Password: " + passwordStars);
    }

    @FXML
    private void cancelClicked() {
        usernameField.clear();
        passwordField.clear();
    }
}