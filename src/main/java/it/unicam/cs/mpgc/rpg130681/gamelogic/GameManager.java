package it.unicam.cs.mpgc.rpg130681.gamelogic;

import it.unicam.cs.mpgc.rpg130681.model.entities.*;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;

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

    public GameManager(
            Ship playerShip,
            List<Planet> planets,
            List<Asteroid> asteroids,
            List<Star> stars,
            List<PickUp> pickups,
            Camera camera) {

        this.playerShip = playerShip;
        this.planets = planets;
        this.asteroids = asteroids;
        this.stars = stars;
        this.pickups = pickups;
        this.projectiles = new ArrayList<>();
        this.camera = camera;
        this.collisionSystem = new CollisionSystem();
    }

    public void update() {
        camera.setPosition(playerShip.getPosition());

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

}
