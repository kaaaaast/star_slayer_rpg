package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Fuel extends PickUp {
    public Fuel(Vector2 position, float pickupRadius, float lifeTime, Tier tier, float diameter) {
        super(position, pickupRadius, lifeTime, tier, diameter);
    }

    @Override
    public void on_pickup(Ship ship) {
        float max_fuel = ship.getResource(ResourceType.FUEL).get_current_value();

        switch (this.getTier()) {

            case SMALL:
                ship.getResource(ResourceType.FUEL).increase_resource_by(max_fuel*0.2f);
                break;

            case MEDIUM:
                ship.getResource(ResourceType.FUEL).increase_resource_by(max_fuel*0.5f);
                break;

            case LARGE:
                ship.getResource(ResourceType.FUEL).complete_refill();
                break;
        }
    }
}
