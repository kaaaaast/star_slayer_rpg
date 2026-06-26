package it.unicam.cs.mpgc.rpg130681.controller.fxml;

import it.unicam.cs.mpgc.rpg130681.controller.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class GameOverController {

    private SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void Retry() {
        sceneManager.startNewGame();
    }

    @FXML
    private void MainMenu() {
        sceneManager.showMainMenu();
    }

    @FXML
    private void Exit() {
        Platform.exit();
    }
}