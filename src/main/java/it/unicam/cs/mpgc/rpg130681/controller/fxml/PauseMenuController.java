package it.unicam.cs.mpgc.rpg130681.controller.fxml;

import it.unicam.cs.mpgc.rpg130681.controller.GameInitializer;
import it.unicam.cs.mpgc.rpg130681.controller.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class PauseMenuController {

    private SceneManager sceneManager;
    private GameInitializer gameInitializer;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setGameInitializer(GameInitializer gameInitializer) {
        this.gameInitializer = gameInitializer;
    }

    @FXML
    private void Resume() {
        gameInitializer.resumeGame();
    }

    @FXML
    private void Save() {
        gameInitializer.saveGame("save.json");
    }

    @FXML
    private void MainMenu() {
        gameInitializer.returnToMainMenu();
    }

    @FXML
    private void Exit() {
        Platform.exit();
    }
}