package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.model.entities.Inventory;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.entities.Star;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceStat;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.model.stats.ShipStats;
import it.unicam.cs.mpgc.rpg130681.model.stats.StatType;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe per la generazione della navicella del giocatore.
 */
public class ShipGenerator {

    /*
    Tutti i valori sono attualmente hardcoded. In futuro verranno trasferiti su json / classi config apposite
     */
    private final float SHIP_BASE_HEALTH = 100f;
    private final float SHIP_BASE_FUEL = 100f;

    private final float SHIP_BASE_SPEED = 4f;
    private final float SHIP_BASE_FIRE_RATE = 5f;
    private final float SHIP_BASE_MINING_POWER = 1f;

    private final float SHIP_BASE_DIAMETER = 64f;
    private final float SHIP_BASE_SHOOTCOOLDOWN = 0.2f;
    private final float SHIP_ROTATION = 0f;

    /**
     * Crea la navicella iniziale del giocatore
     * @param star stella vicino alla quale la navicella viene generata, per evitare di essere generata in blind spots.
     * @return la navicella del giocatore inizializzata
     */
    public Ship createStartingShip(Star star) {

        Map<ResourceType, ResourceStat> resources = new HashMap<>();
        resources.put(ResourceType.HEALTH, new ResourceStat(SHIP_BASE_HEALTH));
        resources.put(ResourceType.FUEL, new ResourceStat(SHIP_BASE_FUEL));

        Map<StatType, Float> stats = new HashMap<>();
        stats.put(StatType.SPEED, SHIP_BASE_SPEED);
        stats.put(StatType.FIRE_RATE, SHIP_BASE_FIRE_RATE);
        stats.put(StatType.MINING_POWER, SHIP_BASE_MINING_POWER);

        ShipStats shipStats = new ShipStats(stats);
        Inventory inventory = new Inventory();

        Vector2 spawnPosition = star.getPosition().add(new Vector2(500, 0));

        return new Ship(spawnPosition, resources, shipStats, SHIP_BASE_DIAMETER, SHIP_BASE_SHOOTCOOLDOWN, SHIP_ROTATION, inventory);
    }

}
