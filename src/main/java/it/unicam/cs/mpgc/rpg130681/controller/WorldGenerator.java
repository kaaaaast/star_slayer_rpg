package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.model.entities.*;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceStat;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.model.stats.ShipStats;
import it.unicam.cs.mpgc.rpg130681.model.stats.StatType;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

import java.util.*;

public class WorldGenerator {

    private final long seed;
    private final Random random;

    public WorldGenerator(long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }

    public GameManager generateWorld() {

        Ship ship = createStartingShip();

        List<Star> stars = new ArrayList<>();
        List<Planet> planets = new ArrayList<>();
        List<Asteroid> asteroids = new ArrayList<>();
        List<PickUp> pickups = new ArrayList<>();

        generateSystems(stars, planets);

        Camera camera = new Camera(ship.getPosition());
        return new GameManager(ship, planets, asteroids,stars,pickups,camera);
    }

    private void generateSystems(List<Star> stars, List<Planet> planets) {
        int systemCount = random.nextInt(20, 51);
        for (int i = 0; i < systemCount; i++) {
            Vector2 systemPosition = randomWorldPosition();
            Star star = new Star(systemPosition, 150, 100);
            stars.add(star);
            generatePlanets(star, planets);
        }
    }

    private void generatePlanets(Star star, List<Planet> planets) {
        int planetCount = random.nextInt(2, 8);
        float orbitRadius = 400;
        for (int i = 0; i < planetCount; i++) {
            orbitRadius += random.nextFloat(200, 500);
            Planet planet = new Planet(star, orbitRadius, random.nextFloat(0.0005f, 0.003f), 100f, 100f);
            planets.add(planet);
        }
    }

    private Vector2 randomWorldPosition() {
        float x = random.nextFloat(-50000f, 50000f);
        float y = random.nextFloat(-50000f, 50000f);
        return new Vector2(x, y);
    }

    private Ship createStartingShip() {
        Map<ResourceType, ResourceStat> resources = new HashMap<>();
        resources.put(ResourceType.HEALTH, new ResourceStat(100));
        resources.put(ResourceType.FUEL, new ResourceStat(100));

        Map<StatType, Float> stats = new HashMap<>();
        stats.put(StatType.SPEED, 10f);
        stats.put(StatType.FIRE_RATE, 1f);
        stats.put(StatType.MINING_POWER, 1f);

        ShipStats shipStats = new ShipStats(stats);
        Inventory inventory = new Inventory();

        return new Ship(new Vector2(0, 0), resources, shipStats, 64, inventory);
    }

    public long getSeed() {
        return seed;
    }
}