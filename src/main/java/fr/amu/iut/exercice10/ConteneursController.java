package fr.amu.iut.exercice10;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;

public class ConteneursController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleSubmit(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        System.out.println("Nom : " + name);
        System.out.println("Email : " + email);
        System.out.println("Mot de passe : " + password);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        nameField.clear();
        emailField.clear();
        passwordField.clear();
    }
}
