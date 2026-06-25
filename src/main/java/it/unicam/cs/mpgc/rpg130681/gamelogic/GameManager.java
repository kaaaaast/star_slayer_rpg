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
            projectile.update_projectile();
        }

        for (Projectile<?> projectile : projectiles) {
            collisionSystem.check_projectile_collision(projectile);
        }

        for (PickUp pickup : pickups) {
            collisionSystem.check_pickup_collision(playerShip, pickup);
        }

        for (Planet planet : planets) {
            collisionSystem.check_player_collision(playerShip, planet);
        }

        projectiles.removeIf(Projectile::should_remove);
        pickups.removeIf(PickUp::should_remove);

        List<Planet> destroyedPlanets = planets.stream().filter(Planet::isDestroyed).toList();

        for (Planet planet : destroyedPlanets) {

            pickups.add(new ResourceDrop(planet.getPosition(), 50f, 60f, Tier.SMALL, 64f, DropType.IRON));
        }

        planets.removeIf(Planet::isDestroyed);
    }

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

    private void updateShipRotation() {
        Planet target = GameUtils.getClosestEntity(playerShip, planets);

        if (target == null) {
            return;
        }

        playerShip.lookAt(target.getPosition());
    }
    private void handlePlayerShooting() {

        if (!InputManager.isPressed(KeyCode.SPACE)) {
            return;
        }

        if (!playerShip.canShoot()) {
            return;
        }

        Planet target = GameUtils.getClosestEntity(playerShip, planets);

        if (target == null) {
            return;
        }

        AudioManager.playShoot();
        addProjectile(new Projectile<>(target, playerShip.getPosition(), 50f, 20f, 30f, 48f));
    }
}
