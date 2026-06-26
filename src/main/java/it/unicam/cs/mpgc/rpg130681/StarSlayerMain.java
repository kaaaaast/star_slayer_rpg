package it.unicam.cs.mpgc.rpg130681;

import it.unicam.cs.mpgc.rpg130681.controller.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class StarSlayerMain extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.showMainMenu();
        stage.setTitle("Star Slayer");
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}