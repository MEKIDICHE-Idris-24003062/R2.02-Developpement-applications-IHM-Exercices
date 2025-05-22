package fr.amu.iut.exercice17;
import fr.amu.iut.exercice17.Exercice;
import fr.amu.iut.exercice17.LigneExercice;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AppMaths extends Application {

    private ComboBox<Integer> comboNb;
    private VBox vboxExos;

    @Override
    public void start(Stage primaryStage) {
        // --- étape 1 : combo ---
        Label lbl = new Label("Combien d’exercices ?");
        comboNb = new ComboBox<>();
        comboNb.getItems().addAll(6, 9, 12, 15);

        HBox top = new HBox(10, lbl, comboNb);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10));

        // conteneur pour les lignes d’exo + bouton
        vboxExos = new VBox(5);
        vboxExos.setPadding(new Insets(10));

        // écouteur sur le choix de la combo
        ChangeListener<Integer> listener = (obs, oldV, newV) -> {
            if (newV != null) {
                majExercices(newV);
            }
        };
        comboNb.valueProperty().addListener(listener);

        VBox root = new VBox(10, top, vboxExos);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercice 17 - Un peu d’arithmétique");
        primaryStage.show();
    }

    /** Met à jour dynamiquement la liste des exercices selon n :
     *  on garde d’abord les incorrects, puis on complète par des neufs
     */
    private void majExercices(int n) {
        // 1) extraire les Exercices incorrects déjà présents
        List<Exercice> liste = new ArrayList<>();
        for (var node : vboxExos.getChildren()) {
            if (node instanceof LigneExercice le && !le.correctProperty().get()) {
                liste.add(le.getExercice());
            }
        }
        // 2) compléter si besoin
        int manque = n - liste.size();
        for (int i = 0; i < manque; i++) {
            liste.add(new Exercice());
        }
        // 3) reconstruire les lignes
        vboxExos.getChildren().clear();
        for (Exercice exo : liste) {
            vboxExos.getChildren().add(new LigneExercice(exo));
        }
        // 4) ajouter le bouton de validation
        Button btn = new Button("Voir le résultat");
        btn.setOnAction(e -> afficherResultat());
        vboxExos.getChildren().add(btn);

        // 5) redimensionner la fenêtre pour tout afficher
        vboxExos.getScene().getWindow().sizeToScene();
    }

    /** Affiche une Alert bloquante avec le nombre de réponses correctes */
    private void afficherResultat() {
        long nbOk = vboxExos.getChildren().stream()
                .filter(n -> n instanceof LigneExercice le && le.correctProperty().get())
                .count();

        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Résultat");
        al.setHeaderText(null);
        al.setContentText("Nombre de réponses correctes : " + nbOk);
        al.initOwner(vboxExos.getScene().getWindow());
        al.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
