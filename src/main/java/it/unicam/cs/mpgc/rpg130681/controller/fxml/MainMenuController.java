package it.unicam.cs.mpgc.rpg130681.controller.fxml;

import it.unicam.cs.mpgc.rpg130681.controller.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenuController {

    private SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void Play() {
        sceneManager.startNewGame();
    }

    @FXML
    private void Load() {
        sceneManager.loadGame();
    }

    @FXML
    private void Info() {
        sceneManager.showInfo();
    }

    @FXML
    private void Exit() {
        Platform.exit();
    }
}