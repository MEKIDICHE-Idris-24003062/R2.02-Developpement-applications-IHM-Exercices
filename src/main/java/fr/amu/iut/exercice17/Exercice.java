package fr.amu.iut.exercice17;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

public class Exercice {
    private final StringProperty enonce;
    private final IntegerProperty solution;

    public Exercice() {
        Random rnd = new Random();
        int a = rnd.nextInt(100) + 1;        // 1..100
        int b = rnd.nextInt(10) + 1;         // 1..10
        int op = rnd.nextInt(4);
        switch (op) {
            case 0 -> {
                enonce = new SimpleStringProperty(a + " + " + b + " = ");
                solution = new SimpleIntegerProperty(a + b);
            }
            case 1 -> {
                enonce = new SimpleStringProperty(a + " - " + b + " = ");
                solution = new SimpleIntegerProperty(a - b);
            }
            case 2 -> {
                enonce = new SimpleStringProperty(a + " * " + b + " = ");
                solution = new SimpleIntegerProperty(a * b);
            }
            default -> {
                enonce = new SimpleStringProperty(a + " / " + b + " = ");
                solution = new SimpleIntegerProperty(a / b);
            }
        }
    }

    public String getEnonce() {
        return enonce.get();
    }

    public StringProperty enonceProperty() {
        return enonce;
    }

    public int getSolution() {
        return solution.get();
    }

    public IntegerProperty solutionProperty() {
        return solution;
    }
}
