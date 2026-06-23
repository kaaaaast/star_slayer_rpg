package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class MedKit extends PickUp{

    public MedKit(Vector2 position, float pickupRadius, float lifeTime, Tier tier, float diameter) {
        super(position, pickupRadius, lifeTime, tier, diameter);
    }

    @Override
    public void on_pickup(Ship ship) {

        float max_hp = ship.getResource(ResourceType.HEALTH).get_max_value();

        switch (getTier()) {

            case SMALL:
                ship.getResource(ResourceType.HEALTH).increase_resource_by(max_hp*0.2f);
                break;

            case MEDIUM:
                ship.getResource(ResourceType.HEALTH).increase_resource_by(max_hp*0.5f);
                break;

            case LARGE:
                ship.getResource(ResourceType.HEALTH).complete_refill();
                break;
        }
    }
}
