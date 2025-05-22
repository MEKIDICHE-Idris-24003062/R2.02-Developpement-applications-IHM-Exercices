package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe extends Application {

    private static final int SIZE = 3;
    private String[][] grille = new String[SIZE][SIZE];
    private boolean tourCroix = true; // X commence
    private Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        // Initialiser la grille vide
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grille[i][j] = null;
            }
        }

        jouerPartie();

        // Afficher la grille
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Label label = new Label();
                ImageView imageView = null;

                if ("X".equals(grille[row][col])) {
                    imageView = new ImageView(new Image("exercice2/Croix.png"));
                } else if ("O".equals(grille[row][col])) {
                    imageView = new ImageView(new Image("exercice2/Rond.png"));
                } else {
                    imageView = new ImageView(new Image("exercice2/Vide.png"));
                }

                imageView.setFitWidth(80);
                imageView.setFitHeight(80);

                label.setGraphic(imageView);
                label.setStyle("-fx-border-color: grey; -fx-border-width: 1px;");
                grid.add(label, col, row);
            }
        }

        Scene scene = new Scene(grid);

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void jouerPartie() {
        List<int[]> casesVides = getCasesVides();

        while (!casesVides.isEmpty()) {
            // Choisir une case vide au hasard
            int[] caseChoisie = casesVides.remove(random.nextInt(casesVides.size()));
            int row = caseChoisie[0];
            int col = caseChoisie[1];

            grille[row][col] = tourCroix ? "X" : "O";

            // Vérifier si le joueur a gagné
            if (aGagne(tourCroix ? "X" : "O")) {
                break;
            }

            tourCroix = !tourCroix; // Changer de joueur
        }
    }

    private List<int[]> getCasesVides() {
        List<int[]> cases = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grille[i][j] == null) {
                    cases.add(new int[]{i, j});
                }
            }
        }
        return cases;
    }

    private boolean aGagne(String joueur) {
        // Vérifier lignes et colonnes
        for (int i = 0; i < SIZE; i++) {
            if ((joueur.equals(grille[i][0]) && joueur.equals(grille[i][1]) && joueur.equals(grille[i][2])) ||
                    (joueur.equals(grille[0][i]) && joueur.equals(grille[1][i]) && joueur.equals(grille[2][i]))) {
                return true;
            }
        }
        // Vérifier diagonales
        if ((joueur.equals(grille[0][0]) && joueur.equals(grille[1][1]) && joueur.equals(grille[2][2])) ||
                (joueur.equals(grille[0][2]) && joueur.equals(grille[1][1]) && joueur.equals(grille[2][0]))) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
