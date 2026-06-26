package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.controller.fxml.GameOverController;
import it.unicam.cs.mpgc.rpg130681.controller.fxml.PauseMenuController;
import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.persistence.SaveData;
import it.unicam.cs.mpgc.rpg130681.persistence.SaveManager;
import it.unicam.cs.mpgc.rpg130681.ui.Hud;
import it.unicam.cs.mpgc.rpg130681.ui.views.*;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che gestisce l'inizializzazione di una partita, e il suo ciclo di vita.
 * Attualmente diverse responsabilità sono accentrate in questa classe. Esse andrebbero in futuro separate ulteriormente
 * in classi apposite per migliorare l'estensibilità del codice e rispettare al meglio i principi SOLID.
 * Si occupa di:
 * <ul>
 *     <li>Generare o caricare il mondo di gioco;</li>
 *     <li>Creare le viste e la scena JavaFX;</li>
 *     <li>Avviare il {@link GameLoop};</li>
 *     <li>Gestire il menu di pausa e la schermata di game over;</li>
 *     <li>Gestire il salvataggio e il caricamento della partita.</li>
 * </ul>
 */
public class GameInitializer {

    private GameManager gameManager;
    private GameViewCreator gameViewCreator;
    private Group root;
    private BackgroundView backgroundView;
    private ShipView shipView;
    private GameLoop gameLoop;
    private long seed;
    private final SceneManager sceneManager;
    private Parent pauseMenu;
    private boolean paused = false;
    private List<PlanetView> planetViews;
    private List<ProjectileView> projectileViews;
    private List<PickUpView> pickUpViews;
    private List<StarView> starViews;
    private Hud hudView;

    /**
     * Costruisce l'inizializzatore del gioco.
     *
     * @param sceneManager il gestore delle scene dell'applicazione.
     */

    public GameInitializer(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.gameViewCreator = new GameViewCreator();
    }

    /**
     * Genera il mondo e crea la scena relativa.
     * @return la scena con la nuova partita.
     */
    public Scene createGameScene() {
        seed = System.currentTimeMillis();
        WorldGenerator worldGenerator = new WorldGenerator(seed);
        GameManager gameManager = worldGenerator.generateWorld();
        return createGameScene(gameManager);
    }

    private Scene createGameScene(GameManager gameManager) {

        root = new Group();
        this.gameManager = gameManager;

        backgroundView = gameViewCreator.createBackground(root);

        shipView = gameViewCreator.createShip(root, gameManager.getPlayerShip());

        planetViews = gameViewCreator.createPlanets(root, gameManager.getPlanets());

        starViews = gameViewCreator.createStars(root, gameManager.getStars());

        hudView = new Hud();
        root.getChildren().add(hudView.getRoot());

        projectileViews = new ArrayList<>();
        pickUpViews = new ArrayList<>();

        Scene scene = createScene(root);

        backgroundView.update(gameManager.getCamera());

        gameLoop = new GameLoop(gameManager, backgroundView, root, shipView, planetViews, projectileViews, pickUpViews, starViews, this, hudView);

        AudioManager.startMusic();
        gameLoop.start();

        return scene;
    }

    /**
     * Crea la scena relativa alla partita.
     * @param root la root della scena.
     * @return la scena costruita.
     */
    private Scene createScene(Group root) {

        Scene scene = new Scene(root, 1920, 1080);

        scene.setOnMouseMoved(e -> InputManager.setMousePos(new Vector2((float)e.getX(), (float)e.getY())));

        scene.setOnKeyPressed(e -> {

            InputManager.keyPressed(e.getCode());

            if (e.getCode() == KeyCode.ESCAPE && !paused) {
                try {
                    showPauseMenu();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        scene.setOnKeyReleased(e -> InputManager.keyReleased(e.getCode()));
        return scene;
    }

    /**
     * Carica una partita salvata e ne crea la scena.
     * @param fileName nome del file del salvataggio.
     * @return la scena costruita.
     * @throws IOException in caso di errore di lettura del salvataggio.
     */
    public Scene createLoadedGameScene(String fileName) throws IOException {

        SaveData save = SaveManager.loadGame(fileName);

        seed = save.seed;

        WorldGenerator worldGenerator = new WorldGenerator(seed);

        GameManager gameManager = worldGenerator.generateWorld();

        restoreSave(gameManager, save);

        return createGameScene(gameManager);
    }

    /**
     * Salva lo stato della partita.
     * @param fileName il nome del file su cui effettuare il salvataggio.
     */
    public void saveGame(String fileName) {
        try {
            SaveManager.saveGame(gameManager, seed, fileName);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il salvataggio.", e);
        }
    }

    /**
     * Ripristina lo stato della partita
     * @param gameManager il gestore della partita.
     * @param save i dati del salvataggio da ripristinare.
     */
    private void restoreSave(GameManager gameManager, SaveData save) {
        Ship ship = gameManager.getPlayerShip();
        ship.setPosition(new Vector2(save.shipX, save.shipY));
        save.resources.forEach((type, value) -> ship.getResource(type).setCurrentValue(value));
        save.inventory.forEach((dropType, quantity) -> ship.getInventory().setQuantity(dropType, quantity));
        save.stats.forEach((type, value) -> ship.getShip_stats().setStat(type, value));
    }

    /**
     * Mostra il menù di pausa.
     * @throws IOException in caso di errore di caricamento del file FXML.
     */
    private void showPauseMenu() throws IOException {

        if (paused) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PauseMenu.fxml"));
        pauseMenu = loader.load();

        PauseMenuController controller = loader.getController();

        controller.setSceneManager(sceneManager);
        controller.setGameInitializer(this);

        root.getChildren().add(pauseMenu);

        paused = true;
        gameLoop.pause();
    }

    /**
     * Chiude il menù pausa e riprende la partita.
     */
    public void resumeGame() {
        root.getChildren().remove(pauseMenu);
        pauseMenu = null;
        paused = false;
        gameLoop.resume();
    }

    /**
     * Interrompe il GameLoop (partita corrente) e ritorna al menù principale.
     */
    public void returnToMainMenu() {
        if (pauseMenu != null) {
            root.getChildren().remove(pauseMenu);
            pauseMenu = null;
        }
        paused = false;
        gameLoop.stop();
        AudioManager.stopMusic();
        sceneManager.showMainMenu();
    }

    /**
     * Mostra la scena del game over in caso di sconfitta.
     */
    public void showGameOver() {

        AudioManager.stopMusic();

        try {FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameOver.fxml"));
            Parent root = loader.load();
            GameOverController controller = loader.getController();
            controller.setSceneManager(sceneManager);
            this.root.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public Group getRoot() {
        return root;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public long getSeed() {
        return seed;
    }

    public boolean isPaused() {
        return paused;
    }
}
