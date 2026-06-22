package it.unicam.cs.mpgc.rpg130681.model.entities;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceStat;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.model.stats.ShipStats;
import it.unicam.cs.mpgc.rpg130681.model.stats.StatType;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

import java.util.Map;

//classe che rappresenta la navicella del giocatore
public class Ship extends GameObject{

    private Map<ResourceType, ResourceStat> ship_resources;
    private ShipStats ship_stats;

    private static final float DEAD_ZONE = 20f;
    private static final float MAX_CURSOR_DISTANCE = 300f;
    private static final float DISTANCE_SCALE = 100f;

    public Ship(Vector2 position, Map<ResourceType, ResourceStat> resources, ShipStats stats, float diameter){
        super(position, diameter);
        this.ship_resources = resources;
        this.ship_stats = stats;
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

    public ResourceStat getResource(ResourceType type) {
        if (!ship_resources.containsKey(type)) {
            throw new IllegalArgumentException("La nave non possiede la risorsa " + type);
        }
        return ship_resources.get(type);
    }
}
