package fr.amu.iut.exercice18;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Rectangle {
    private final IntegerProperty xA;
    private final IntegerProperty yA;
    private final IntegerProperty xB;
    private final IntegerProperty yB;
    private final IntegerProperty perimetre;

    public Rectangle() {
        xA = new SimpleIntegerProperty(0);
        yA = new SimpleIntegerProperty(0);
        xB = new SimpleIntegerProperty(0);
        yB = new SimpleIntegerProperty(0);
        perimetre = new SimpleIntegerProperty(0);
        createBinding();
    }

    private void createBinding() {
        // largeur = |xB - xA|
        var largeur = Bindings.createIntegerBinding(
                () -> Math.abs(xB.get() - xA.get()),
                xA, xB
        );
        // hauteur = |yB - yA|
        var hauteur = Bindings.createIntegerBinding(
                () -> Math.abs(yB.get() - yA.get()),
                yA, yB
        );
        // périmètre = 2*(largeur + hauteur)
        perimetre.bind(
                Bindings.createIntegerBinding(
                        () -> 2 * (largeur.get() + hauteur.get()),
                        largeur, hauteur
                )
        );
    }

    // getters des propriétés
    public IntegerProperty xAProperty() { return xA; }
    public IntegerProperty yAProperty() { return yA; }
    public IntegerProperty xBProperty() { return xB; }
    public IntegerProperty yBProperty() { return yB; }
    public IntegerProperty perimetreProperty() { return perimetre; }

    // getters des valeurs (facultatifs)
    public int getXA() { return xA.get(); }
    public int getYA() { return yA.get(); }
    public int getXB() { return xB.get(); }
    public int getYB() { return yB.get(); }
}

