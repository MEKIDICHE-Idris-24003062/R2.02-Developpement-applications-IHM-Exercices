package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class IHMPendu extends Application {

    private static final int MAX_VIES = 7;

    private String motADeviner;
    private boolean[] lettresTrouvees;
    private int viesRestantes;
    private final Dico dico = new Dico();

    private Label labelVies;
    private Label labelMot;
    private Label message;
    private ImageView imagePendu;
    private GridPane clavier;
    private Button boutonRejouer;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        labelVies = new Label();
        labelMot = new Label();
        message = new Label();
        imagePendu = new ImageView();
        imagePendu.setFitWidth(200);
        imagePendu.setPreserveRatio(true);

        clavier = new GridPane();
        clavier.setHgap(5);
        clavier.setVgap(5);
        clavier.setAlignment(Pos.CENTER);

        boutonRejouer = new Button("Rejouer");
        boutonRejouer.setOnAction(e -> initialiserJeu());

        root.getChildren().addAll(labelVies, imagePendu, labelMot, clavier, message, boutonRejouer);

        Scene scene = new Scene(root, 500, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.show();

        initialiserJeu();
    }

    private void initialiserJeu() {
        motADeviner = dico.getMot().toUpperCase();
        lettresTrouvees = new boolean[motADeviner.length()];
        viesRestantes = MAX_VIES;

        labelVies.setText("Vies restantes : " + viesRestantes);
        labelMot.setText(masquerMot());
        message.setText("");
        imagePendu.setImage(chargerImage(viesRestantes));

        clavier.getChildren().clear();
        int col = 0, row = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            Button btn = new Button(String.valueOf(c));
            btn.setPrefWidth(40);
            btn.setOnAction(e -> {
                btn.setDisable(true);
                traiterLettre(btn.getText().charAt(0));
            });
            clavier.add(btn, col, row);
            col++;
            if (col == 10) {
                col = 0;
                row++;
            }
        }
    }

    private void traiterLettre(char lettre) {
        ArrayList<Integer> positions = dico.getPositions(lettre, motADeviner);

        if (positions.isEmpty()) {
            viesRestantes--;
            labelVies.setText("Vies restantes : " + viesRestantes);
            imagePendu.setImage(chargerImage(viesRestantes));
        } else {
            for (int pos : positions) {
                lettresTrouvees[pos] = true;
            }
        }

        labelMot.setText(masquerMot());

        if (!labelMot.getText().contains("*")) {
            message.setText(" Gagné !");
            desactiverClavier();
        } else if (viesRestantes == 0) {
            message.setText(" Perdu ! Le mot était : " + motADeviner);
            desactiverClavier();
        }
    }

    private String masquerMot() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < motADeviner.length(); i++) {
            if (lettresTrouvees[i]) {
                sb.append(motADeviner.charAt(i)).append(" ");
            } else {
                sb.append("* ");
            }
        }
        return sb.toString();
    }

    private void desactiverClavier() {
        for (Node node : clavier.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }
    }

    private Image chargerImage(int vies) {
        return new Image(getClass().getResourceAsStream("/exercice6/pendu" + vies + ".png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
