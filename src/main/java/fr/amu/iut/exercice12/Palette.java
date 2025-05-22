package fr.amu.iut.exercice12;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Palette extends Application {

    private Label texteDuHaut;
    private Label texteDuBas;
    private Pane panneau;
    private HBox boutons;
    private VBox bas;

    private CustomButton vert;
    private CustomButton rouge;
    private CustomButton bleu;
    private CustomButton sourceOfEvent;

    @Override
    public void start(Stage primaryStage) {
        // --- création de l'UI (identique) ---
        BorderPane root = new BorderPane();
        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        texteDuBas = new Label();
        texteDuBas.setMinHeight(24);

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));

        bas = new VBox(5, boutons, texteDuBas);
        bas.setAlignment(Pos.CENTER_RIGHT);

        vert  = new CustomButton("Vert",  "#31BCA4");
        rouge = new CustomButton("Rouge", "#F21411");
        bleu  = new CustomButton("Bleu",  "#3273A4");

        // --- gestionnaire générique : on incrémente le compteur interne au bouton ---
        EventHandler<ActionEvent> gestionnaireEvenement = event -> {
            sourceOfEvent = (CustomButton) event.getSource();
            // incrément du IntegerProperty nbClics
            sourceOfEvent.setNbClics(sourceOfEvent.getNbClics() + 1);
        };

        // on attache le même handler aux 3 boutons
        vert.setOnAction(gestionnaireEvenement);
        rouge.setOnAction(gestionnaireEvenement);
        bleu.setOnAction(gestionnaireEvenement);

        // --- listener sur nbClics : met à jour l'IU dès que le compteur change ---
        ChangeListener<Number> nbClicsListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
                int n = newVal.intValue();
                // label du haut
                texteDuHaut.setText(sourceOfEvent.getText() + " choisi " + n + " fois");
                // fond du panneau
                panneau.setStyle("-fx-background-color: " + sourceOfEvent.getCouleur() + ";");
                // label du bas
                texteDuBas.setText("Le " + sourceOfEvent.getText() + " est une jolie couleur !");
                texteDuBas.setStyle("-fx-text-fill: " + sourceOfEvent.getCouleur() + ";");
            }
        };

        // on branche le listener sur les 3 propriétés nbClics
        vert.nbClicsProperty().addListener(nbClicsListener);
        rouge.nbClicsProperty().addListener(nbClicsListener);
        bleu.nbClicsProperty().addListener(nbClicsListener);

        // ajout des boutons et placement dans le BorderPane
        boutons.getChildren().addAll(vert, rouge, bleu);
        root.setTop(texteDuHaut);
        root.setCenter(panneau);
        // on place la VBox qui contient boutons + texteDuBas
        root.setBottom(bas);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Palette – Exercice 12");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
