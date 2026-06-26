package it.unicam.cs.mpgc.rpg130681.controller;


import it.unicam.cs.mpgc.rpg130681.controller.fxml.InfoController;
import it.unicam.cs.mpgc.rpg130681.controller.fxml.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *Classe per la gestione delle scene e del passaggio tra i menù e le varie scene di gioco.
 */
public class SceneManager {

    private final Stage stage;

    /**
     * Costruisce un gestore di scene.
     * @param stage lo stage principale su cui costruire la scena.
     */
    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    /**
     * Avvia una partita.
     */
    public void startNewGame() {
        GameInitializer initializer = new GameInitializer(this);
        stage.setScene(initializer.createGameScene());
        stage.setMaximized(true);
    }

    /**
     * Scena del menù principale.
     */
    public void showMainMenu() {
        MainMenuController controller = loadScene("/fxml/MainMenu.fxml");
        controller.setSceneManager(this);
    }

    /**
     * Scena delle informazioni.
     */
    public void showInfo() {
        InfoController controller = loadScene("/fxml/Info.fxml");
        controller.setSceneManager(this);
    }

    /**
     * Scena del caricamento di una partita
     */
    public void loadGame() {
        try {
            GameInitializer initializer = new GameInitializer(this);
            stage.setScene(initializer.createLoadedGameScene("save.json"));
            stage.setMaximized(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Carica una scena.
     * @param fxml il percorso dell'FXML da caricare.
     * @return il controller della scena caricata.
     * @param <T> il tipo del controller.
     */
    private <T> T loadScene(String fxml) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setMaximized(true);
            return loader.getController();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
