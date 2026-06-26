package it.unicam.cs.mpgc.rpg130681.gamelogic;

import it.unicam.cs.mpgc.rpg130681.model.entities.*;
import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.model.pickups.ResourceDrop;
import it.unicam.cs.mpgc.rpg130681.model.pickups.Tier;
import it.unicam.cs.mpgc.rpg130681.ui.views.AudioManager;
import it.unicam.cs.mpgc.rpg130681.utils.GameUtils;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import it.unicam.cs.mpgc.rpg130681.controller.InputManager;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che si occupa di gestire tutto ciò che succede attivamente durante la partita, come aggiornare le posizioni degli oggetti,
 * aggiornare le loro viste, gestire i target dei proiettili, controllare le collisioni e gli upgrade della navicella.
 */
public class GameManager {

    private Ship playerShip;

    private List<Planet> planets;
    private List<Asteroid> asteroids;
    private List<Star> stars;

    private List<Projectile<?>> projectiles;
    private List<PickUp> pickups;

    private Camera camera;
    private CollisionSystem collisionSystem;
    private UpgradeManager upgradeManager;

    /**
     * Costruisce il gestore della partita.
     * @param playerShip la navicella del giocatore.
     * @param planets i pianeti del mondo.
     * @param asteroids gli asteroidi del mondo.
     * @param stars le stelle del mondo.
     * @param pickups gli oggetti raccoglibili dal giocatore.
     * @param camera la camera.
     */
    public GameManager(Ship playerShip, List<Planet> planets, List<Asteroid> asteroids, List<Star> stars, List<PickUp> pickups, Camera camera) {
        this.playerShip = playerShip;
        this.planets = planets;
        this.asteroids = asteroids;
        this.stars = stars;
        this.pickups = pickups;
        this.projectiles = new ArrayList<>();
        this.camera = camera;
        this.collisionSystem = new CollisionSystem();
        this.upgradeManager = new UpgradeManager();
    }

    /**
     * Esegue un update dello stato della partita.
     * Aggiorna gli oggetti del mondo, gestisce le collisioni, i proiettili, la
     * raccolta degli oggetti e la rimozione degli oggetti distrutti.
     */
    public void update() {
        camera.setPosition(playerShip.getPosition());

        playerShip.update();

        updateShipRotation();

        Vector2 mouseWorldPos = camera.screenToWorld(InputManager.getMousePos());

        playerShip.move(mouseWorldPos.sub(playerShip.getPosition()));

        upgradeManager.checkForUpgrades(playerShip);

        handlePlayerShooting();

        for (Planet p : planets) {
            p.update();
        }

        for (Projectile<?> projectile : projectiles) {
            projectile.updateProjectile();
        }

        for (Projectile<?> projectile : projectiles) {
            collisionSystem.checkProjectileCollision(projectile);
        }

        for (PickUp pickup : pickups) {
            collisionSystem.checkPickupCollision(playerShip, pickup);
        }

        for (Planet planet : planets) {
            collisionSystem.checkPlayerCollision(playerShip, planet);
        }

        projectiles.removeIf(Projectile::shouldRemove);
        pickups.removeIf(PickUp::shouldRemove);

        List<Planet> destroyedPlanets = planets.stream().filter(Planet::isDestroyed).toList();

        for (Planet planet : destroyedPlanets) {

            pickups.add(new ResourceDrop(planet.getPosition(), 50f, 60f, Tier.SMALL, 64f, DropType.IRON));
        }

        planets.removeIf(Planet::isDestroyed);
    }

    /**
     * Aggiunge un proiettile
     * @param projectile il proiettile da aggiungere.
     */
    public void addProjectile(Projectile<?> projectile) {
        projectiles.add(projectile);
    }

    public Ship getPlayerShip() {
        return playerShip;
    }

    public Camera getCamera() {
        return camera;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public List<Star> getStars() {
        return stars;
    }

    public List<Projectile<?>> getProjectiles() {
        return projectiles;
    }

    public List<PickUp> getPickups() {
        return pickups;
    }

    /**
     * Aggiorna la rotazione della nave, facendola puntare verso il bersaglio più vicino, che è anche il bersaglio dei proiettili.
     */
    private void updateShipRotation() {

        Planet target = GameUtils.getClosestVisibleEntity(playerShip, planets, camera);

        if (target == null) {
            return;
        }

        playerShip.lookAt(target.getPosition());
    }

    /**
     * Gestisce la creazione dei proiettili e la selezione del loro bersaglio.
     */
    private void handlePlayerShooting() {

        if (!InputManager.isPressed(KeyCode.SPACE)) {
            return;
        }

        if (!playerShip.canShoot()) {
            return;
        }

        Planet target = GameUtils.getClosestVisibleEntity(playerShip, planets, camera);

        if (target == null) {
            return;
        }

        AudioManager.playShoot();
        addProjectile(new Projectile<>(target, playerShip.getPosition(), 50f, 20f, 30f, 48f));
    }
}
