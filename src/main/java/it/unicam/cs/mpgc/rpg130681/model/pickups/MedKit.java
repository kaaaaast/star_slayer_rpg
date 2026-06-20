package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.objects.Ship;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class MedKit extends PickUp{

    public MedKit(Vector2 position, float pickupRadius, float lifeTime, Tier tier) {
        super(position, pickupRadius, lifeTime, tier);
    }

    @Override
    public void on_pickup(Ship ship) {
        switch (getTier()) {

            case SMALL:
                ship.heal_ship((int) (ship.getMax_health_points() * 0.25f));
                break;

            case MEDIUM:
                ship.heal_ship((int) (ship.getMax_health_points() * 0.50f));
                break;

            case LARGE:
                ship.refill_hp();
                break;
        }
    }
}
