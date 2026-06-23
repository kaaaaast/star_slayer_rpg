package it.unicam.cs.mpgc.rpg130681;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.Planet;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.entities.Star;
import it.unicam.cs.mpgc.rpg130681.model.stats.*;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.ui.views.ShipView;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Ship ship = Ship.createTestShip();

        ShipView shipView = new ShipView(ship);

        Group root = new Group();
        root.getChildren().add(shipView.getRoot());

        // centro dello schermo
        shipView.getRoot().setLayoutX(960);
        shipView.getRoot().setLayoutY(540);

        Scene scene = new Scene(root, 1920, 1080);

        stage.setScene(scene);
        stage.setTitle("Ship test");
        stage.show();

        new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long now) {

                // cambia frame ogni 100 ms
                if (now - lastUpdate > 100_000_000) {

                    shipView.nextFrame();

                    lastUpdate = now;
                }
            }

        }.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

}