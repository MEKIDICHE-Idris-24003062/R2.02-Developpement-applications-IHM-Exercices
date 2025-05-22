package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation extends Application {
    // Constantes pour rendre le code plus lisible et facilement modifiable
    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 400;
    private static final int ANIMATION_DURATION_MS = 1500;

    // Transitions et boutons comme propriétés de classe pour y accéder facilement
    private SequentialTransition sequentialTransition;
    private CustomButton customButton;

    @Override
    public void start(Stage primaryStage) {
        // Configuration de la scène
        BorderPane root = new BorderPane();
        customButton = new CustomButton();
        root.setCenter(customButton);

        // Ajout des contrôles d'animation
        HBox controls = createAnimationControls();
        root.setBottom(controls);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        // Création de l'animation
        sequentialTransition = createAnimation();

        // Association du clic sur le bouton personnalisé avec le démarrage de l'animation
        customButton.setOnMousePressed(mouseEvent -> playAnimation());

        // Configuration de la fenêtre principale
        primaryStage.setTitle("Animation Améliorée");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Crée une animation séquentielle pour le bouton personnalisé
     * @return Une transition séquentielle qui fait bouger le bouton en carré
     */
    private SequentialTransition createAnimation() {
        Duration duration = Duration.millis(ANIMATION_DURATION_MS);

        // Tableau de mouvements (X, Y) pour définir le parcours
        int[][] movements = {
                {150, -150},  // En haut à droite
                {-300, 0},    // À gauche
                {0, 300},     // En bas
                {300, 0},     // À droite
                {-150, -150}  // Retour au centre
        };

        // Création des transitions à partir du tableau de mouvements
        TranslateTransition[] transitions = new TranslateTransition[movements.length];

        for (int i = 0; i < movements.length; i++) {
            transitions[i] = new TranslateTransition(duration, customButton);
            transitions[i].setByX(movements[i][0]);
            transitions[i].setByY(movements[i][1]);
        }

        // Création de la transition séquentielle avec toutes les transitions
        return new SequentialTransition(transitions);
    }

    /**
     * Crée les contrôles pour gérer l'animation
     * @return Une boîte horizontale contenant les boutons de contrôle
     */
    private HBox createAnimationControls() {
        Button playButton = new Button("Jouer");
        Button pauseButton = new Button("Pause");
        Button stopButton = new Button("Arrêter");
        Button resetButton = new Button("Réinitialiser");

        playButton.setOnAction(event -> playAnimation());
        pauseButton.setOnAction(event -> pauseAnimation());
        stopButton.setOnAction(event -> stopAnimation());
        resetButton.setOnAction(event -> resetAnimation());

        HBox controls = new HBox(10, playButton, pauseButton, stopButton, resetButton);
        controls.setStyle("-fx-padding: 10px; -fx-alignment: center;");

        return controls;
    }

    /**
     * Démarre l'animation
     */
    private void playAnimation() {
        sequentialTransition.play();
    }

    /**
     * Met en pause l'animation
     */
    private void pauseAnimation() {
        sequentialTransition.pause();
    }

    /**
     * Arrête l'animation
     */
    private void stopAnimation() {
        sequentialTransition.stop();
    }

    /**
     * Réinitialise l'animation et la position du bouton
     */
    private void resetAnimation() {
        sequentialTransition.stop();
        customButton.setTranslateX(0);
        customButton.setTranslateY(0);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}