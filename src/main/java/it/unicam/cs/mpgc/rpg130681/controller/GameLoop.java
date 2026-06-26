package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.model.entities.Projectile;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.ui.Hud;
import it.unicam.cs.mpgc.rpg130681.ui.views.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;

import java.util.List;

/**
 * Classe che rappresenta il ciclo del gioco.
 * Ad ogni frame aggiorna la logica di gioco, le viste degli oggetti,
 * l'interfaccia del giocatore e gestisce la comparsa e la rimozione delle entità visualizzate.
 */

/*
   GameLoop aggiorna solo le viste e gestisce il rapporto fra modello e visualizzazione. La logica
   è delegata direttamente al GameManager.
 */
public class GameLoop extends AnimationTimer {

    private final GameManager gameManager;
    private final GameInitializer gameInitializer;
    private final ShipView shipView;
    private final BackgroundView backgroundView;
    private final Group root;
    private final List<PlanetView> planetViews;
    private final List<ProjectileView> projectileViews;
    private final List<PickUpView> pickUpViews;
    private final List<StarView> starViews;
    private final Hud hudView;

    /**
     * Costruisce il ciclo principale del gioco.
     *
     * @param gameManager il gestore della logica di gioco.
     * @param backgroundView la vista dello sfondo.
     * @param root il nodo radice contenente le viste del gioco.
     * @param shipView la vista della navicella del giocatore.
     * @param planetViews le viste dei pianeti.
     * @param projectileViews le viste dei proiettili.
     * @param pickUpViews le viste degli oggetti raccoglibili.
     * @param starViews le viste delle stelle.
     * @param gameInitializer l'inizializzatore della partita.
     * @param hudView la vista dell'interfaccia.
     */
    public GameLoop(GameManager gameManager, BackgroundView backgroundView, Group root, ShipView shipView, List<PlanetView> planetViews, List<ProjectileView> projectileViews, List<PickUpView> pickUpViews, List<StarView> starViews, GameInitializer gameInitializer, Hud hudView) {
        this.gameManager = gameManager;
        this.backgroundView = backgroundView;
        this.root = root;
        this.shipView = shipView;
        this.planetViews = planetViews;
        this.projectileViews = projectileViews;
        this.pickUpViews = pickUpViews;
        this.starViews = starViews;
        this.gameInitializer = gameInitializer;
        this.hudView = hudView;
    }

    /**
     * Aggiorna lo stato del gioco e le relative viste.
     *
     * @param now il timestamp corrente
     */
    @Override
    public void handle(long now) {
        gameManager.update();
        hudView.update(gameManager.getPlayerShip());
        if (gameManager.getPlayerShip().isDestroyed()) {
            gameInitializer.showGameOver();
            stop();
            return;
        }

        Camera camera = gameManager.getCamera();

        backgroundView.update(camera);
        shipView.update(camera);
        shipView.nextFrame();
        starViews.forEach(s -> s.update(camera));

        updatePlanets(camera);
        updateProjectiles(camera);
        updatePickups(camera);
    }

    /**
     * Aggiorna le viste dei pianeti e rimuove quelle dei pianeti distrutti.
     *
     * @param camera la camera del gioco.
     */

    private void updatePlanets(Camera camera) {
        planetViews.forEach(p -> p.update(camera));
        planetViews.removeIf(view -> {

            if (view.getPlanet().isDestroyed()) {
                root.getChildren().remove(view.getImage_view());
                return true;
            }

            return false;

        });
    }

    /**
     * Aggiorna le viste dei proiettili, creando quelle mancanti
     * e rimuovendo quelle non necessarie.
     *
     * @param camera la camera del gioco.
     */
    private void updateProjectiles(Camera camera){
        showProjectileViews();
        projectileViews.forEach(p -> {p.update(camera); p.nextFrame();});
        removeProjectileViews();
    }

    /**
     * Aggiorna le viste degli oggetti raccoglibili, creando quelle mancanti
     * e rimuovendo quelle non necessarie.
     *
     * @param camera la camera del gioco.
     */

    private void updatePickups(Camera camera) {
        showPickupsViews();
        pickUpViews.forEach(p -> p.update(camera));
        removePickupsViews();
    }

    /**
     * Crea le viste dei nuovi proiettili presenti nel gioco.
     */

    private void showProjectileViews() {
        for (Projectile<?> projectile : gameManager.getProjectiles()) {
            boolean exists = projectileViews.stream().anyMatch(v -> v.getProjectile() == projectile);
            if (!exists) {
                ProjectileView projectileView = new ProjectileView(projectile);
                projectileViews.add(projectileView);
                root.getChildren().add(projectileView.getImageView());
            }
        }
    }

    /**
     * Rimuove le viste dei proiettili che non sono più presenti nel gioco.
     */
    private void removeProjectileViews() {
        projectileViews.removeIf(view -> {

            if (view.getProjectile().shouldRemove()) {
                root.getChildren().remove(view.getImageView());
                return true;
            }
            return false;
        }
        );
    }

    /**
     * Crea le viste dei nuovi oggetti raccoglibili presenti nel mondo di gioco.
     */
    private void showPickupsViews() {
        for (PickUp pickup : gameManager.getPickups()) {
            boolean exists = pickUpViews.stream().anyMatch(v -> v.getPickUp() == pickup);
            if (!exists) {
                PickUpView view = new PickUpView(pickup);
                pickUpViews.add(view);
                root.getChildren().add(view.getImage_view());
            }
        }
    }

    /**
     * Rimuove le viste degli oggetti raccoglibili che non sono più presenti nel mondo di gioco.
     */
    private void removePickupsViews() {
        pickUpViews.removeIf(view -> {
            if (view.getPickUp().shouldRemove()) {
                root.getChildren().remove(view.getImage_view());
                return true;
            }

            return false;
        });
    }

    public void pause() {
        stop();
    }

    public void resume() {
        start();
    }

}
