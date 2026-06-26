package it.unicam.cs.mpgc.rpg130681.ui;

import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Classe per la visualizzazione dell'interfaccia del giocatore.
 */
public class Hud {

    private final VBox root;
    private final ProgressBar healthBar;
    private final ProgressBar fuelBar;

    /**
     * Costruisce l'interfaccia. Mostra i punti vita e il carburante rimasti.
     */
    public Hud() {

        root = new VBox(10);

        root.setLayoutX(760);
        root.setLayoutY(920);

        root.setAlignment(Pos.CENTER);

        Label healthLabel = new Label("Health");
        healthBar = new ProgressBar();
        healthBar.setPrefWidth(200);

        VBox healthBox = new VBox(5, healthLabel, healthBar);
        healthBox.setAlignment(Pos.CENTER);

        Label fuelLabel = new Label("Fuel");
        fuelBar = new ProgressBar();
        fuelBar.setPrefWidth(200);

        VBox fuelBox = new VBox(5, fuelLabel, fuelBar);
        fuelBox.setAlignment(Pos.CENTER);

        HBox bars = new HBox(40, healthBox, fuelBox);
        bars.setAlignment(Pos.CENTER);

        healthLabel.setStyle("-fx-text-fill: white;");
        fuelLabel.setStyle("-fx-text-fill: white;");

        healthLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        fuelLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        healthBar.setStyle("-fx-accent: limegreen;");
        fuelBar.setStyle("-fx-accent: darkorange;");

        root.getChildren().add(bars);
    }

    /**
     * Aggiorna i valori dell'interfaccia.
     * @param ship la navicella che fornisce le statistiche da aggiornare.
     */
    public void update(Ship ship) {
        healthBar.setProgress(ship.getResource(ResourceType.HEALTH).getCurrentValue() / ship.getResource(ResourceType.HEALTH).getMaxValue());
        fuelBar.setProgress(ship.getResource(ResourceType.FUEL).getCurrentValue() / ship.getResource(ResourceType.FUEL).getMaxValue());
    }

    public VBox getRoot() {
        return root;
    }
}