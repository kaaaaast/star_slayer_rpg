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

    public Ship(Vector2 position, Map<ResourceType, ResourceStat> resources, ShipStats stats){
        super(position);
        this.ship_resources = resources;
        this.ship_stats = stats;
    }

    /*
   Per scelta di design, la navicella segue il cursore del mouse, e più esso è lontano
   più la velocità aumenta. */
    public void move(Vector2 direction) {

        float distance = direction.length();

        //Dead-zone per non far muovere la navicella quando il mouse è molto vicino a essa
        if (distance < 20f) {
            return;
        }

        //Si imposta una distanza massima fra il cursore e la navicella, altrimenti si potrebbero ottenere velocità esagerate
        distance = Math.min(distance, 300f);

        float speed_multiplier = distance / 100f;
        setPosition(getPosition().add(direction.normalize().multiply(ship_stats.getStat(StatType.SPEED) * speed_multiplier)));
    }

    public ShipStats getShip_stats() {
        return ship_stats;
    }

    public ResourceStat getResource(ResourceType type) {
        return ship_resources.get(type);
    }
}
