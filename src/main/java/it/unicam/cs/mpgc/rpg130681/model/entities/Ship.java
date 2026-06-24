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
    private float shootCooldown;
    private static final float DEAD_ZONE = 20f;
    private static final float MAX_CURSOR_DISTANCE = 300f;
    private static final float DISTANCE_SCALE = 100f;

    public Ship(Vector2 position, Map<ResourceType, ResourceStat> resources, ShipStats stats, float diameter, float shootCooldown, Inventory inventory){
        super(position, diameter);
        this.ship_resources = resources;
        this.ship_stats = stats;
        this.shootCooldown = shootCooldown;
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

        //Imposta una distanza massima fra il cursore e la navicella (evita velocità esagerate)
        distance = Math.min(distance, MAX_CURSOR_DISTANCE);

        float speed_multiplier = distance / DISTANCE_SCALE;
        setPosition(getPosition().add(direction.normalize().multiply(ship_stats.getStat(StatType.SPEED) * speed_multiplier)));
    }

    public void update() {
        update_shoot_cooldown();
        update_invulnerability();
    }

    public boolean canShoot() {
        if (shootCooldown > 0) {
            return false;
        }
        shootCooldown = 1f / ship_stats.getStat(StatType.FIRE_RATE);
        return true;
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
        ResourceStat ship_current_health = getResource(ResourceType.HEALTH);
        ship_current_health.decrease_resource_by(ship_current_health.get_current_value()*0.3f);
        invulnerable = true;
        invulnerability_time = 3.0f;
    }

    private void update_shoot_cooldown() {
        shootCooldown = Math.max(shootCooldown-0.02f,0);
    }

    private void update_invulnerability() {

        if (!invulnerable) {
            return;
        }

        invulnerability_time = Math.max(invulnerability_time - 0.02f, 0);
        if (invulnerability_time == 0) {
            invulnerable = false;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

}
