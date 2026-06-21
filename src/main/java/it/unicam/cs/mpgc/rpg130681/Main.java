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

public class Main extends Application {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        //---------------------------
        // Ship stats
        //---------------------------

        Map<StatType, Float> shipStatsMap = new HashMap<>();

        shipStatsMap.put(StatType.SPEED, 5f);
        shipStatsMap.put(StatType.FIRE_RATE, 1f);
        shipStatsMap.put(StatType.FUELTANK_SIZE, 100f);
        shipStatsMap.put(StatType.MINING_POWER, 1f);

        ShipStats shipStats = new ShipStats(shipStatsMap);

        //---------------------------
        // Ship resources
        //---------------------------

        Map<ResourceType, ResourceStat> resources = new HashMap<>();

        resources.put(ResourceType.HEALTH, new ResourceStat(100));
        resources.put(ResourceType.FUEL, new ResourceStat(100));

        //---------------------------
        // Ship
        //---------------------------

        Ship ship = new Ship(
                new Vector2(0,0),
                resources,
                shipStats
        );

        //---------------------------
        // Camera
        //---------------------------

        Camera camera = new Camera(ship.getPosition());

        //---------------------------
        // Test planet
        //---------------------------

        Star star = new Star(
                new Vector2(0, 0),
                500,
                1000
        );

        Planet planet = new Planet(
                star,
                100f,
                1f,
                1f,
                100f
        );

        //---------------------------
        // Graphics
        //---------------------------

        Rectangle shipView = new Rectangle(20,20);
        shipView.setFill(Color.BLACK);

        Circle planetView = new Circle(50);
        planetView.setFill(Color.BLUE);

        root.getChildren().addAll(
                planetView,
                shipView
        );

        //---------------------------
        // Mouse position
        //---------------------------

        final double[] mouseX = {WIDTH / 2.0};
        final double[] mouseY = {HEIGHT / 2.0};

        scene.setOnMouseMoved(event -> {
            mouseX[0] = event.getX();
            mouseY[0] = event.getY();
        });

        //---------------------------
        // Game loop
        //---------------------------

        new AnimationTimer() {

            @Override
            public void handle(long now) {

                //----------------------------------
                // Mouse screen -> world
                //----------------------------------

                Vector2 mouseWorld =
                        camera.screenToWorld(
                                new Vector2(
                                        (float) mouseX[0],
                                        (float) mouseY[0]
                                )
                        );

                //----------------------------------
                // Move ship
                //----------------------------------

                Vector2 direction =
                        mouseWorld.sub(ship.getPosition());

                ship.move(direction);

                //----------------------------------
                // Camera follows ship
                //----------------------------------

                camera.setPosition(ship.getPosition());

                //----------------------------------
                // Render ship
                //----------------------------------

                shipView.setX(WIDTH / 2.0 - 10);
                shipView.setY(HEIGHT / 2.0 - 10);

                //----------------------------------
                // Render planet
                //----------------------------------

                Vector2 planetScreen =
                        camera.worldToScreen(
                                planet.getPosition()
                        );

                planetView.setCenterX(
                        planetScreen.x()
                );

                planetView.setCenterY(
                        planetScreen.y()
                );

            }

        }.start();

        stage.setScene(scene);
        stage.setTitle("Space RPG Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}