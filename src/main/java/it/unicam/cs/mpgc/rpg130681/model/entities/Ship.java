package it.unicam.cs.mpgc.rpg130681.model.entities;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceStat;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.model.stats.ShipStats;
import it.unicam.cs.mpgc.rpg130681.model.stats.StatType;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

import java.util.HashMap;
import java.util.Map;

//classe che rappresenta la navicella del giocatore
public class Ship extends GameObject {

    private Map<ResourceType, ResourceStat> ship_resources;
    private ShipStats ship_stats;
    private Inventory inventory;
    private boolean invulnerable;
    private float invulnerability_time;
    private static final float DEAD_ZONE = 20f;
    private static final float MAX_CURSOR_DISTANCE = 300f;
    private static final float DISTANCE_SCALE = 100f;

    public Ship(Vector2 position, Map<ResourceType, ResourceStat> resources, ShipStats stats, float diameter, Inventory inventory){
        super(position, diameter);
        this.ship_resources = resources;
        this.ship_stats = stats;
        this.inventory = inventory;
    }

    /*
   Per scelta di design, la navicella segue il cursore del mouse, e più esso è lontano
   più la velocità aumenta (fino a un certo massimo). */
    public void move(Vector2 direction) {

        float distance = direction.length();

        //Dead-zone per non far muovere la navicella quando il mouse è molto vicino a essa.
        if (distance < DEAD_ZONE) {
            return;
        }

        //Si imposta una distanza massima fra il cursore e la navicella, altrimenti si potrebbero ottenere velocità esagerate.
        distance = Math.min(distance, MAX_CURSOR_DISTANCE);

        float speed_multiplier = distance / DISTANCE_SCALE;
        setPosition(getPosition().add(direction.normalize().multiply(ship_stats.getStat(StatType.SPEED) * speed_multiplier)));
    }

    public ShipStats getShip_stats() {
        return ship_stats;
    }

    public Map<ResourceType, ResourceStat> getShip_resources() {
        return ship_resources;
    }

    public ResourceStat getResource(ResourceType type) {
        if (!ship_resources.containsKey(type)) {
            throw new IllegalArgumentException("La nave non possiede la risorsa " + type);
        }
        return ship_resources.get(type);
    }

    public void receive_collision_damage() {
        if (invulnerable) {
            return;
        }
        float ship_current_health = this.getShip_resources().get(ResourceType.HEALTH).get_current_value();
        ship_resources.get(ResourceType.HEALTH).decrease_resource_by(ship_current_health*0.3f);
        invulnerable = true;
        invulnerability_time = 3.0f;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public static Ship createTestShip() {

        Map<ResourceType, ResourceStat> resources = new HashMap<>();

        resources.put(ResourceType.HEALTH, new ResourceStat(100));
        resources.put(ResourceType.FUEL, new ResourceStat(100));

        Map<StatType, Float> stats = new HashMap<>();

        stats.put(StatType.SPEED, 10f);
        stats.put(StatType.FIRE_RATE, 1f);
        stats.put(StatType.FUELTANK_SIZE, 100f);
        stats.put(StatType.MINING_POWER, 1f);

        return new Ship(
                new Vector2(0, 0),
                resources,
                new ShipStats(stats),
                48,
                null
        );
    }
}
