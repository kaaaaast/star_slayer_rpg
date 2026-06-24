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
    private static final float MIN_SYSTEM_DISTANCE = 2500f;

    public WorldGenerator(long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }

    public GameManager generateWorld() {


        List<Star> stars = new ArrayList<>();
        List<Planet> planets = new ArrayList<>();
        List<Asteroid> asteroids = new ArrayList<>();
        List<PickUp> pickups = new ArrayList<>();

        generateSystems(stars, planets);

        Ship ship = createStartingShip(stars);

        Camera camera = new Camera(ship.getPosition());
        return new GameManager(ship, planets, asteroids,stars,pickups,camera);
    }

    private void generateSystems(List<Star> stars, List<Planet> planets) {

        int systemCount = random.nextInt(20, 51);
        for (int i = 0; i < systemCount; i++) {
            Vector2 systemPosition;

            do {
                systemPosition = randomWorldPosition();
            } while (!isValidSystemPosition(systemPosition, stars));

            Star star = new Star(systemPosition, 150, 2000);
            stars.add(star);
            generatePlanets(star, planets);
        }
    }

    private boolean isValidSystemPosition(
            Vector2 position,
            List<Star> stars) {

        for (Star star : stars) {

            float distance =
                    position.distanceFrom(star.getPosition());

            if (distance < MIN_SYSTEM_DISTANCE) {
                return false;
            }
        }

        return true;
    }

    private void generatePlanets(Star star, List<Planet> planets) {
        int planetCount = random.nextInt(2, 8);
        // evita che i pianeti orbitino troppo vicino alla stella
        float orbitRadius = star.getDiameter()/2 + 200;
        for (int i = 0; i < planetCount; i++) {
            orbitRadius += random.nextFloat(100, 300);
            Planet planet = new Planet(star, 100, random.nextFloat(0.0005f, 0.010f), orbitRadius, 100f);
            planets.add(planet);
        }
    }

    private Vector2 randomWorldPosition() {
        float x = random.nextFloat(-10000f, 10000f);
        float y = random.nextFloat(-10000f, 10000f);
        return new Vector2(x, y);
    }

    private Ship createStartingShip(List<Star> stars) {

        Star startingstar = stars.getFirst();

        Map<ResourceType, ResourceStat> resources = new HashMap<>();
        resources.put(ResourceType.HEALTH, new ResourceStat(100));
        resources.put(ResourceType.FUEL, new ResourceStat(100));

        Map<StatType, Float> stats = new HashMap<>();
        stats.put(StatType.SPEED, 7f);
        stats.put(StatType.FIRE_RATE, 5f);
        stats.put(StatType.MINING_POWER, 1f);

        ShipStats shipStats = new ShipStats(stats);
        Inventory inventory = new Inventory();
        Vector2 spawnPosition = startingstar.getPosition().add(new Vector2(500, 0));

        return new Ship(spawnPosition, resources, shipStats, 64, 0.2f, inventory);
    }

    public long getSeed() {
        return seed;
    }
}