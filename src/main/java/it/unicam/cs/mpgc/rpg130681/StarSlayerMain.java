package it.unicam.cs.mpgc.rpg130681;

import it.unicam.cs.mpgc.rpg130681.controller.GameInitializer;
import it.unicam.cs.mpgc.rpg130681.controller.GameLoop;
import it.unicam.cs.mpgc.rpg130681.controller.InputManager;
import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.controller.WorldGenerator;
import it.unicam.cs.mpgc.rpg130681.model.entities.Planet;
import it.unicam.cs.mpgc.rpg130681.model.entities.Star;
import it.unicam.cs.mpgc.rpg130681.ui.views.*;

import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StarSlayerMain extends Application {

    @Override
    public void start(Stage stage) {

        GameInitializer gameInitializer = new GameInitializer();

        Group root = gameInitializer.createGame();
        Scene scene = createScene(root);

        stage.setScene(scene);
        stage.setTitle("Star Slayer RPG");
        stage.show();

        gameInitializer.startGame();
    }

    private static Scene createScene(Group root) {
        Scene scene = new Scene(root, 1920, 1080);
        scene.setOnMouseMoved(e -> InputManager.setMousePos(new Vector2((float) e.getX(), (float) e.getY())));
        scene.setOnKeyPressed(e -> InputManager.keyPressed(e.getCode()));
        scene.setOnKeyReleased(e -> InputManager.keyReleased(e.getCode()));
        return scene;
    }


    public static void main(String[] args) {
        launch(args);
    }
}