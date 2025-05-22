package fr.amu.iut.exercice13;

import fr.amu.iut.exercice14.Personne;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;

    public static void main(String[] args) {
        // Crée une liste observable pour écouter les changements sur la propriété age
        lesPersonnes = FXCollections.observableArrayList(
                personne -> new Observable[]{ personne.ageProperty() }
        );

        // Listener qui parcourt TOUS les changements groupés
        ListChangeListener<Personne> plusieursChangementsListener = change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Personne p : change.getAddedSubList()) {
                        System.out.println("Ajout : " + p.getNom());
                    }
                }
                if (change.wasRemoved()) {
                    for (Personne p : change.getRemoved()) {
                        System.out.println("Suppression : " + p.getNom());
                    }
                }
                if (change.wasUpdated()) {
                    for (int i = change.getFrom(); i < change.getTo(); i++) {
                        Personne p = lesPersonnes.get(i);
                        System.out.println(p.getNom() + " a maintenant " + p.getAge() + " ans");
                    }
                }
            }
            System.out.println("Fin de traitement des changements\n");
        };

        lesPersonnes.addListener(plusieursChangementsListener);

        // Appels pour tester chaque question
        question1();
        question2();
        question3();
        question4();
        question5();
    }

    public static void question1() {
        System.out.println("=== Question 1 ===");
        lesPersonnes.add(new Personne("Alice", 30));
    }

    public static void question2() {
        System.out.println("=== Question 2 ===");
        Personne bob = new Personne("Bob", 40);
        lesPersonnes.add(bob);
        lesPersonnes.remove(bob);
    }

    public static void question3() {
        System.out.println("=== Question 3 ===");
        Personne pierre = new Personne("Pierre", 20);
        lesPersonnes.add(pierre);
        pierre.setAge(21); // doit déclencher wasUpdated()
    }

    public static void question4() {
        System.out.println("=== Question 4 ===");
        System.out.println("⚠️ Rien à ajouter ici : la liste est déjà observable sur age via observableArrayList(...)");
    }

    public static void question5() {
        System.out.println("=== Question 5 ===");
        lesPersonnes.addAll(
                new Personne("Emma", 25),
                new Personne("Lucas", 35)
        );
        lesPersonnes.get(0).setAge(26); // Modifie Emma
        lesPersonnes.remove(1);         // Supprime Lucas
    }
}
