package fr.amu.iut.exercice18;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class PanneauPrincipal {

    @FXML private Slider sliderXA;
    @FXML private Slider sliderYA;
    @FXML private Button btnBxMinus, btnBxPlus;
    @FXML private TextField tfBx;
    @FXML private Button btnByMinus, btnByPlus;
    @FXML private TextField tfBy;
    @FXML private TextField tfPerimetre;
    @FXML private Pane drawingPane;

    private Rectangle rectangle;
    private Line ligneHaut, ligneBas, ligneGauche, ligneDroite;
    private static final double ratioDessin = 10;       // pixels par unité
    private static final int valeurMaxCoordonnees = 20;

    @FXML
    public void initialize() {
        rectangle = new Rectangle();
        addLines();
        bindSommetsRectangle();
        bindPerimetreTextField();
        bindHorizontal1();
        bindVertical1();
        bindHorizontal2();
        bindVertical2();

    }

    private void addLines() {
        ligneHaut    = new Line();
        ligneBas    = new Line();
        ligneGauche = new Line();
        ligneDroite = new Line();
        drawingPane.getChildren().addAll(ligneHaut, ligneBas, ligneGauche, ligneDroite);
    }

    // ---- gestion de xB ----
    @FXML
    private void decrementerBx() {
        if (rectangle.getXB() > 0) {
            rectangle.xBProperty().set(rectangle.getXB() - 1);
        }
    }

    @FXML
    private void incrementerBx() {
        if (rectangle.getXB() < valeurMaxCoordonnees) {
            rectangle.xBProperty().set(rectangle.getXB() + 1);
        }
    }

    // ---- gestion de yB ----
    @FXML
    private void setByMinusAction() {
        if (rectangle.getYB() > 0) {
            rectangle.yBProperty().set(rectangle.getYB() - 1);
        }
    }

    @FXML
    private void setByPlusAction() {
        if (rectangle.getYB() < valeurMaxCoordonnees) {
            rectangle.yBProperty().set(rectangle.getYB() + 1);
        }
    }

    // ---- liaisons entre contrôles et modèle Rectangle ----
    private void bindSommetsRectangle() {
        // A via sliders
        rectangle.xAProperty().bind(
                Bindings.createIntegerBinding(
                        () -> (int) sliderXA.getValue(),
                        sliderXA.valueProperty()
                )
        );
        rectangle.yAProperty().bind(
                Bindings.createIntegerBinding(
                        () -> (int) sliderYA.getValue(),
                        sliderYA.valueProperty()
                )
        );

        // B via TextField non éditables
        tfBx.textProperty().bind(rectangle.xBProperty().asString());
        tfBy.textProperty().bind(rectangle.yBProperty().asString());
    }

    private void bindPerimetreTextField() {
        tfPerimetre.textProperty().bind(rectangle.perimetreProperty().asString());
    }

    // ---- dessin des 2 premiers côtés ----
    private void bindHorizontal1() {
        // côté supérieur de A à B
        ligneHaut.startXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        ligneHaut.startYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));
        ligneHaut.endXProperty().bind  (rectangle.xBProperty().multiply(ratioDessin));
        ligneHaut.endYProperty().bind  (rectangle.yAProperty().multiply(ratioDessin));
    }

    private void bindVertical1() {
        // côté gauche de A à B
        ligneGauche.startXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        ligneGauche.startYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));
        ligneGauche.endXProperty().bind  (rectangle.xAProperty().multiply(ratioDessin));
        ligneGauche.endYProperty().bind  (rectangle.yBProperty().multiply(ratioDessin));
    }
    private void bindHorizontal2() {
        ligneBas.startXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        ligneBas.startYProperty().bind(rectangle.yBProperty().multiply(ratioDessin));
        ligneBas.endXProperty().bind(  rectangle.xBProperty().multiply(ratioDessin));
        ligneBas.endYProperty().bind(  rectangle.yBProperty().multiply(ratioDessin));
    }

    // côté droit : de (xB, yA) à (xB, yB)
    private void bindVertical2() {
        ligneDroite.startXProperty().bind(rectangle.xBProperty().multiply(ratioDessin));
        ligneDroite.startYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));
        ligneDroite.endXProperty().bind(  rectangle.xBProperty().multiply(ratioDessin));
        ligneDroite.endYProperty().bind(  rectangle.yBProperty().multiply(ratioDessin));
    }
}

