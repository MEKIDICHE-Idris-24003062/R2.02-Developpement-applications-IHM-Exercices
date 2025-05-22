package fr.amu.iut.exercice15;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginControl {

    @FXML private TextField userId;
    @FXML private PasswordField pwd;
    @FXML private Button ok;
    @FXML private Button cancel;

    /** appelé automatiquement après injection FXML */
    @FXML
    private void initialize() {
        // 1) Champ mot de passe désactivé si identifiant < 6 caractères
        pwd.disableProperty().bind(
                userId.textProperty().length().lessThan(6)
        );

        // 2) Bouton cancel désactivé si les deux champs sont vides
        cancel.disableProperty().bind(
                userId.textProperty().isEmpty()
                        .and(pwd.textProperty().isEmpty())
        );

        // 3) Bouton ok désactivé tant que le mot de passe ne respecte pas les critères :
        //    - au moins 8 caractères
        //    - au moins une majuscule
        //    - au moins un chiffre
        BooleanBinding longEnough =
                pwd.textProperty().length().greaterThanOrEqualTo(8);

        BooleanBinding hasUpper = Bindings.createBooleanBinding(
                () -> {
                    String text = pwd.getText();
                    return text != null && text.matches(".*[A-Z].*");
                },
                pwd.textProperty()
        );

        BooleanBinding hasDigit = Bindings.createBooleanBinding(
                () -> {
                    String text = pwd.getText();
                    return text != null && text.matches(".*\\d.*");
                },
                pwd.textProperty()
        );

        // Le bouton OK est désactivé si une des conditions n'est PAS remplie
        ok.disableProperty().bind(
                longEnough.and(hasUpper).and(hasDigit).not()
        );
    }

    @FXML
    private void okClicked() {
        // Affiche l'utilisateur et masque le mot de passe
        String user = userId.getText();
        String maskedPassword = "*".repeat(pwd.getText().length());

        System.out.println("User : " + user + " \nPassword : " + maskedPassword);

        // Ici vous pourriez ajouter la logique de vérification réelle du login
        // Par exemple, vérifier dans une base de données ou un fichier

        // Optionnel: fermer la fenêtre après connexion réussie
        // ((Stage) ok.getScene().getWindow()).close();
    }

    @FXML
    private void cancelClicked() {
        // Efface les deux champs
        userId.clear();
        pwd.clear();

        // Optionnel: remettre le focus sur le champ utilisateur
        userId.requestFocus();
    }
}