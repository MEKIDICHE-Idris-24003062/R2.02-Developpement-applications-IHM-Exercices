package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Panneaux pour Celsius et Fahrenheit
        VBox panneauCelsius = new VBox(10);
        VBox panneauFahrenheit = new VBox(10);

        // Composants Celsius
        Label labelC = new Label("°C");
        Slider sliderC = new Slider(0, 100, 0);
        sliderC.setOrientation(Orientation.VERTICAL);
        sliderC.setPrefHeight(300);
        sliderC.setShowTickMarks(true);
        sliderC.setShowTickLabels(true);
        sliderC.setMajorTickUnit(10);
        sliderC.setMinorTickCount(0);
        TextField textC = new TextField();
        textC.setPrefWidth(60);

        // Composants Fahrenheit
        Label labelF = new Label("°F");
        Slider sliderF = new Slider(0, 212, 32);
        sliderF.setOrientation(Orientation.VERTICAL);
        sliderF.setPrefHeight(300);
        sliderF.setShowTickMarks(true);
        sliderF.setShowTickLabels(true);
        sliderF.setMajorTickUnit(26.5); // approx for 212/8
        sliderF.setMinorTickCount(0);
        TextField textF = new TextField();
        textF.setPrefWidth(60);

        // Liaison bidirectionnelle entre textFields et sliders
        Bindings.bindBidirectional(textC.textProperty(), sliderC.valueProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(textF.textProperty(), sliderF.valueProperty(), new NumberStringConverter());

        // Mise à jour mutuelle des sliders (Celsius ↔ Fahrenheit)
        sliderC.valueProperty().addListener((obs, oldVal, newVal) ->
                sliderF.setValue(newVal.doubleValue() * 9 / 5 + 32)
        );
        sliderF.valueProperty().addListener((obs, oldVal, newVal) ->
                sliderC.setValue((newVal.doubleValue() - 32) * 5 / 9)
        );

        // Assemblage de l'interface
        panneauCelsius.getChildren().addAll(labelC, sliderC, textC);
        panneauFahrenheit.getChildren().addAll(labelF, sliderF, textF);

        HBox root = new HBox(20, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-alignment: bottom-center;");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Convertisseur de températures");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
