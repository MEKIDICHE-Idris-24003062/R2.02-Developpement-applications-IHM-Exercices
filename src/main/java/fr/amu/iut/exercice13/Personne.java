package fr.amu.iut.exercice13;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Personne {
    private String nom;
    private IntegerProperty age;
    private StringProperty villeDeNaissance;

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = new SimpleIntegerProperty(age);
        this.villeDeNaissance = new SimpleStringProperty("Paris");
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public String getVilleDeNaissance() {
        return villeDeNaissance.get();
    }

    public void setVilleDeNaissance(String ville) {
        this.villeDeNaissance.set(ville);
    }

    public StringProperty villeDeNaissanceProperty() {
        return villeDeNaissance;
    }

    @Override
    public String toString() {
        return nom + " (" + getAge() + " ans, né à " + getVilleDeNaissance() + ")";
    }
}
