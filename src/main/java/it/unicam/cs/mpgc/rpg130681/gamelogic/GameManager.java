package it.unicam.cs.mpgc.rpg130681.gamelogic;

import it.unicam.cs.mpgc.rpg130681.model.entities.*;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;

import java.util.List;

public class GameManager {
    private List<Planet> planets;
    private List<Projectile> projectiles;
    private List<PickUp> pickups;
    private List<Star> stars;
    private List<Asteroid> asteroids;
    private Ship ship;
    private Camera camera;

    public GameManager(List<Planet> planets, List <Projectile> projectiles, List <PickUp> pickups, List<Star> stars, Ship ship, Camera camera) {
        if (planets == null || pickups == null || stars == null || ship == null || camera == null){
            throw new IllegalArgumentException("Non puoi costruire un modo con parametri nulli.");
        }
        this.planets = planets;
        this.projectiles = projectiles;
        this.pickups = pickups;
        this.stars = stars;
        this.ship = ship;
        this.camera = camera;
    }

    //TODO completare parte del metodo dopo camera insieme a Projectile

}
