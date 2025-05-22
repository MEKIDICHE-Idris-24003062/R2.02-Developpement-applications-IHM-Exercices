package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Palette extends Application {

    // Compteurs pour chaque couleur (à utiliser plus tard si besoin)
    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    // Boutons de couleur
    private Button vert;
    private Button rouge;
    private Button bleu;

    // Layouts principaux
    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;
    private Rectangle rectangle;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialisation de la racine de la scène
        root = new BorderPane();

        // Création des composants
        label = new Label("Vert: " + nbVert + ", Rouge: " + nbRouge + ", Bleu: " + nbBleu);
        panneau = new VBox(10);
        bas = new HBox(10);
        rectangle = new Rectangle(400, 200, Color.WHITE);
        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        vert.setOnAction(e -> {
            incrementerVert();
            mettreAJourCouleur();
        });
        rouge.setOnAction(e -> {
            incrementerRouge();
            mettreAJourCouleur();
        });
        bleu.setOnAction(e -> {
            incrementerBleu();
            mettreAJourCouleur();
        });
        bas.getChildren().addAll(vert, rouge, bleu);
        bas.setPadding(new Insets(10));
        bas.setAlignment(Pos.CENTER);

        panneau.getChildren().add(rectangle);
        panneau.setPadding(new Insets(10));


        HBox topBox = new HBox(label);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10));
        root.setTop(topBox);
        root.setCenter(panneau);
        root.setBottom(bas);

        // Configuration de la scène
        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("Palette");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void incrementerVert() {
        nbVert++;
        mettreAJourLabel();
    }

    private void incrementerRouge() {
        nbRouge++;
        mettreAJourLabel();
    }

    private void incrementerBleu() {
        nbBleu++;
        mettreAJourLabel();
    }

    private void mettreAJourCouleur() {
        double r = Math.min(nbRouge / 10.0, 1.0);
        double g = Math.min(nbVert / 10.0, 1.0);
        double b = Math.min(nbBleu / 10.0, 1.0);
        rectangle.setFill(Color.color(r, g, b));
    }

    private void creerZoneBas() {
        bas = new HBox(10); // Espacement entre les boutons
        bas.setPadding(new Insets(10));
        bas.setAlignment(Pos.CENTER); // Centrage des boutons
        bas.getChildren().addAll(vert, rouge, bleu);
    }
    private void mettreAJourLabel() {
        label.setText("Vert: " + nbVert + ", Rouge: " + nbRouge + ", Bleu: " + nbBleu);

    }
}
