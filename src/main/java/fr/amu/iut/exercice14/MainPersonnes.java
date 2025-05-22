package fr.amu.iut.exercice14;

import javafx.beans.Observable;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;
    private static DoubleProperty ageMoyen    = new SimpleDoubleProperty();
    private static IntegerProperty nbParisiens = new SimpleIntegerProperty();

    public static void main(String[] args) {
        // 1) Liste observable qui écoute aussi les changements internes via ageProperty() et villeDeNaissanceProperty()
        lesPersonnes = FXCollections.observableArrayList(
                p -> new Observable[]{
                        p.ageProperty(),
                        p.villeDeNaissanceProperty()
                }
        );

        // 2) Binding bas-niveau pour l’âge moyen
        DoubleBinding calculAgeMoyen = new DoubleBinding() {
            { bind(lesPersonnes); }

            @Override
            protected double computeValue() {
                if (lesPersonnes.isEmpty()) return 0.0;
                double somme = 0;
                for (Personne p : lesPersonnes) {
                    somme += p.getAge();
                }
                return somme / lesPersonnes.size();
            }
        };
        ageMoyen.bind(calculAgeMoyen);

        // 3) Binding bas-niveau pour le nombre de Parisiens
        IntegerBinding calculNbParisiens = new IntegerBinding() {
            { bind(lesPersonnes); }

            @Override
            protected int computeValue() {
                int c = 0;
                for (Personne p : lesPersonnes) {
                    if ("Paris".equals(p.getVilleDeNaissance())) {
                        c++;
                    }
                }
                return c;
            }
        };
        nbParisiens.bind(calculNbParisiens);

        // 4) Tests
        question1();
        question2();
    }

    /** Teste le recalcul automatique de l’âge moyen */
    public static void question1() {
        System.out.println("=== Question 1 : âge moyen ===");

        Personne alice = new Personne("Alice", 30);
        lesPersonnes.add(alice);
        System.out.println("Après ajout d’Alice (30 ans) → ageMoyen = " + ageMoyen.get());

        Personne bob = new Personne("Bob", 40);
        lesPersonnes.add(bob);
        System.out.println("Après ajout de Bob (40 ans)   → ageMoyen = " + ageMoyen.get());

        // modification de l’âge d’Alice
        alice.setAge(35);
        System.out.println("Après modif. d’Alice à 35 ans → ageMoyen = " + ageMoyen.get());
    }

    /** Teste le recalcul automatique du nombre de Parisiens */
    public static void question2() {
        System.out.println("\n=== Question 2 : nb de Parisiens ===");

        Personne pierre = new Personne("Pierre", 20);
        pierre.setVilleDeNaissance("Lyon");
        lesPersonnes.add(pierre);
        System.out.println("Après ajout de Pierre (Lyon)  → nbParisiens = " + nbParisiens.get());

        Personne marie = new Personne("Marie", 22);
        marie.setVilleDeNaissance("Paris");
        lesPersonnes.add(marie);
        System.out.println("Après ajout de Marie (Paris)  → nbParisiens = " + nbParisiens.get());

        // suppression de Marie
        lesPersonnes.remove(marie);
        System.out.println("Après suppression de Marie    → nbParisiens = " + nbParisiens.get());
    }
}
