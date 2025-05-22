package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    // compteurs individuels pour chaque bouton
    private int countVert, countRouge, countBleu;

    // propriété du nombre de clics pour la couleur courante
    private final IntegerProperty nbFois = new SimpleIntegerProperty(0);
    // propriété du nom de la couleur courante (Vert, Rouge, Bleu)
    private final StringProperty message = new SimpleStringProperty("");
    // propriété du code couleur CSS du panneau et du texte bas
    private final StringProperty couleurPanneau = new SimpleStringProperty();

    @Override
    public void start(Stage stage) {
        // --- construction de l'UI ---
        BorderPane root = new BorderPane();
        root.setPrefSize(400, 200);

        // Label du haut
        Label texteDuHaut = new Label();
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);
        root.setTop(texteDuHaut);

        // Pane central dont on bindera le style
        Pane panneau = new Pane();
        root.setCenter(panneau);

        // Les trois boutons
        Button btnVert  = new Button("Vert");
        Button btnRouge = new Button("Rouge");
        Button btnBleu  = new Button("Bleu");

        HBox hbox = new HBox(10, btnVert, btnRouge, btnBleu);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));

        // Label du bas
        Label texteDuBas = new Label();
        texteDuBas.setMinHeight(24);
        texteDuBas.setAlignment(Pos.CENTER);

        // On met boutons + label bas dans un VBox pour le placer en bas du BorderPane
        VBox bottomBox = new VBox(5, hbox, texteDuBas);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(5));
        root.setBottom(bottomBox);

        // --- handlers : on n'y met que la mise à jour des propriétés, PAS de setText ni setStyle direct ---
        btnVert.setOnAction(e -> {
            countVert++;
            nbFois.set(countVert);
            message.set("Vert");
            couleurPanneau.set("#1ABC9C");
        });
        btnRouge.setOnAction(e -> {
            countRouge++;
            nbFois.set(countRouge);
            message.set("Rouge");
            couleurPanneau.set("#E74C3C");
        });
        btnBleu.setOnAction(e -> {
            countBleu++;
            nbFois.set(countBleu);
            message.set("Bleu");
            couleurPanneau.set("#3498DB");
        });

        // on crée tous les bindings en une seule méthode
        createBindings(texteDuHaut, panneau, texteDuBas);

        // affichage
        stage.setScene(new Scene(root));
        stage.setTitle("Exercice 4 – BorderPane, HBox & Bindings");
        stage.show();
    }

    /**
     * Ici on extrait tous les bindings :
     *  - texteDuHaut : affichage conditionnel avant/après premier clic
     *  - style du panneau central
     *  - texte et couleur du label bas
     */
    private void createBindings(Label texteDuHaut, Pane panneau, Label texteDuBas) {
        // BooleanProperty vrai tant qu'aucun clic
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty();
        pasEncoreDeClic.bind(Bindings.equal(nbFois, 0));

        // binding texte en haut
        // avant premier clic : "Aucun clic pour l'instant"
        // sinon : "<Couleur> choisi <nbFois> fois"
        texteDuHaut.textProperty().bind(
                Bindings.when(pasEncoreDeClic)
                        .then("Cliquez sur un bouton")
                        .otherwise(
                                Bindings.concat(
                                        message,
                                        " choisi ",
                                        nbFois.asString(),
                                        " fois"
                                )
                        )
        );

        // binding style du panneau (background-color)
        panneau.styleProperty().bind(
                Bindings.concat(
                        "-fx-background-color: ",
                        couleurPanneau,
                        ";"
                )
        );

        // binding texte du label bas : "Le <Couleur> est une jolie couleur !"
        texteDuBas.textProperty().bind(
                Bindings.concat(
                        "Le ",
                        message,
                        " est une jolie couleur !"
                )
        );
        texteDuBas.setMaxWidth(Double.MAX_VALUE);
        texteDuBas.setAlignment(Pos.CENTER_RIGHT);

        // binding couleur du texte bas
        texteDuBas.styleProperty().bind(
                Bindings.concat(
                        "-fx-text-fill: ",
                        couleurPanneau,
                        ";"
                )
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
