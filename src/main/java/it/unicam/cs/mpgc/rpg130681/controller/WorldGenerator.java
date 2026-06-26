package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.model.entities.*;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

import java.util.*;

/**
 * Classe che si occupa della generazione del mondo.
 */
public class WorldGenerator {

    private final long seed;
    private final Random random;
    private final ShipGenerator shipGenerator = new ShipGenerator();

    //in futuro, si sposteranno i dati in json / classe di configurazione appositi.
    private static final float MIN_ORBIT_DISTANCE = 200f;
    private static final float MIN_SYSTEM_DISTANCE = 2500f;
    private static final float STAR_BASE_HEALTHPOINTS = 150f;
    private static final float STAR_BASE_DIAMETER = 1000f;

    private static final float PLANET_BASE_HEALTHPOINTS = 100f;
    private static final float PLANET_BASE_DIAMETER = 100f;


    /**
     * Costruisce il generatore del mondo
     * @param seed seed del mondo.
     */
    public WorldGenerator(long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }

    /**
     * Genera il mondo attraverso la creazione di diversi sistemi.
     * @return GameManager inizializzato con il mondo appena generato.
     */
    public GameManager generateWorld() {
        List<Star> stars = new ArrayList<>();
        List<Planet> planets = new ArrayList<>();
        List<Asteroid> asteroids = new ArrayList<>();
        List<PickUp> pickups = new ArrayList<>();

        generateSystems(stars, planets);
        Ship ship = shipGenerator.createStartingShip(stars.getFirst());

        Camera camera = new Camera(ship.getPosition());
        return new GameManager(ship, planets, asteroids,stars,pickups,camera);
    }

    /**
     * Genera i sistemi di stelle e pianeti nel mondo.
     * @param stars le stelle da posizionare
     * @param planets i pianeti da far orbitare attorno alle stelle
     */
    private void generateSystems(List<Star> stars, List<Planet> planets) {

        //sceglie un numero casuale di sistemi, poi per ogni sistema cerca
        //una posizione valida di generazione che non lo faccia collidere con altri sistemi troppo vicini

        int systemCount = random.nextInt(20, 50);
        for (int i = 0; i < systemCount; i++) {
            Vector2 systemPosition;

            do {
                systemPosition = randomWorldPosition();
            } while (!isValidSystemPosition(systemPosition, stars));

            Star star = new Star(systemPosition, STAR_BASE_HEALTHPOINTS, STAR_BASE_DIAMETER);
            stars.add(star);
            generateOrbitingPlanets(star, planets);
        }
    }

    /**
     * Controlla che una posizione di generazione del sistema sia valida,
     * ovvero che il sistema si trovi a una certa distanza da tutti gli altri sistemi.
     * @param position la posizione da validare.
     * @param stars le stelle del sistema.
     * @return {@code true} se la posizione è valida.
     */
    private boolean isValidSystemPosition(Vector2 position, List<Star> stars) {

        for (Star star : stars) {
            float distance = position.distanceFrom(star.getPosition());
            if (distance < MIN_SYSTEM_DISTANCE) {
                return false;
            }
        }

        return true;
    }

    /**
     * Genera tutti i pianeti che orbitano attorno a una certa stella {@code parentStar},
     * @param parentStar la stella del sistema attorno alla quale orbitano i pianeti.
     * @param planets i pianeti da far orbitare.
     */
    private void generateOrbitingPlanets(Star parentStar, List<Planet> planets) {

        int orbitingPlanetCount = random.nextInt(2, 8);
        // evita che i pianeti orbitino troppo vicino alla stella
        float orbitRadius = parentStar.getRadius() + MIN_ORBIT_DISTANCE;

        for (int i = 0; i < orbitingPlanetCount; i++) {
            orbitRadius += random.nextFloat(100, 300);
            Planet planet = new Planet(parentStar, PLANET_BASE_DIAMETER, random.nextFloat(0.0005f, 0.010f), orbitRadius, PLANET_BASE_HEALTHPOINTS);
            planets.add(planet);
        }
    }

    private Vector2 randomWorldPosition() {
        float x = random.nextFloat(-10000f, 10000f);
        float y = random.nextFloat(-10000f, 10000f);
        return new Vector2(x, y);
    }

    public long getSeed() {
        return seed;
    }
}