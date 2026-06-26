package it.unicam.cs.mpgc.rpg130681.controller.fxml;

import it.unicam.cs.mpgc.rpg130681.controller.SceneManager;
import javafx.fxml.FXML;

public class InfoController {

    private SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void Back() {
        sceneManager.showMainMenu();
    }
}