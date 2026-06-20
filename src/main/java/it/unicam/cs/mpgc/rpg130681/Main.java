package it.unicam.cs.mpgc.rpg130681;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.gamelogic.InputManager;
import it.unicam.cs.mpgc.rpg130681.model.objects.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.objects.Ship;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // MODEL

        Ship ship = new Ship(
                5,
                new Vector2(0,0)
        );

        GameObject planet = new GameObject(
                new Vector2(500,300)
        ) {};

        // CAMERA

        Camera camera = new Camera(
                ship.getPosition()
        );

        // VIEW

        Rectangle shipView = new Rectangle(40,40);
        shipView.setFill(Color.BLACK);

        Rectangle planetView = new Rectangle(100,100);
        planetView.setFill(Color.BLUE);

        // ROOT

        Pane root = new Pane();

        root.getChildren().addAll(
                planetView,
                shipView
        );

        root.setFocusTraversable(true);

        // INPUT

        root.setOnMouseMoved(event ->

                InputManager.setMousePos(
                        new Vector2(
                                (float) event.getX(),
                                (float) event.getY()
                        )
                )

        );

        // GAME LOOP

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // mouse (screen) -> world

                Vector2 mouseWorld =
                        camera.screenToWorld(
                                InputManager.getMousePos()
                        );

                // movimento nave

                Vector2 direction =
                        mouseWorld.sub(
                                ship.getPosition()
                        );

                ship.move(direction);

                // la camera segue la nave

                camera.setPosition(
                        ship.getPosition()
                );

                // posizione nave sullo schermo

                Vector2 shipScreenPos =
                        camera.worldToScreen(
                                ship.getPosition()
                        );

                shipView.setLayoutX(
                        shipScreenPos.x()
                );

                shipView.setLayoutY(
                        shipScreenPos.y()
                );

                // posizione pianeta sullo schermo

                Vector2 planetScreenPos =
                        camera.worldToScreen(
                                planet.getPosition()
                        );

                planetView.setLayoutX(
                        planetScreenPos.x()
                );

                planetView.setLayoutY(
                        planetScreenPos.y()
                );

            }

        };

        timer.start();

        Scene scene = new Scene(
                root,
                1920,
                1080
        );

        stage.setTitle("Space RPG");

        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}*/