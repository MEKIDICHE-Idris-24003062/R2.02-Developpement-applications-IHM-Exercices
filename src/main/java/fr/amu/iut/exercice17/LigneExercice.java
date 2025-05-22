package fr.amu.iut.exercice17;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LigneExercice extends HBox {
    private final Exercice exercice;
    private final BooleanProperty correct = new SimpleBooleanProperty(false);
    private final TextField reponseField = new TextField();

    public LigneExercice(Exercice exercice) {
        this.exercice = exercice;

        Label lblEnonce = new Label(exercice.getEnonce());
        lblEnonce.setMinWidth(80);

        reponseField.setPrefColumnCount(4);
        reponseField.textProperty().addListener((obs, oldV, newV) -> {
            try {
                int val = Integer.parseInt(newV.trim());
                correct.set(val == exercice.getSolution());
            } catch (NumberFormatException ex) {
                correct.set(false);
            }
            // Feedback visuel
            if (correct.get()) {
                setStyle("-fx-background-color: lightgreen;");
            } else {
                setStyle("");
            }
        });

        setSpacing(10);
        setPadding(new Insets(5));
        getChildren().addAll(lblEnonce, reponseField);
    }

    /** Permet de récupérer l’exercice pour le tri des incorrects */
    public Exercice getExercice() {
        return exercice;
    }

    public BooleanProperty correctProperty() {
        return correct;
    }
}
