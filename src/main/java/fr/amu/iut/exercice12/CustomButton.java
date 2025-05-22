package fr.amu.iut.exercice12;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

public class CustomButton extends Button {

    private String couleur;
    private IntegerProperty nbClics;

    public CustomButton(String texte, String couleur) {
        super(texte);
        this.couleur = couleur;
        // initialisation du compteur
        this.nbClics = new SimpleIntegerProperty(0);
    }

    // getters / setters pour nbClics
    public final int getNbClics() {
        return nbClics.get();
    }

    public final void setNbClics(int value) {
        nbClics.set(value);
    }

    public IntegerProperty nbClicsProperty() {
        return nbClics;
    }

    // accès à la couleur stockée
    public String getCouleur() {
        return couleur;
    }
}

