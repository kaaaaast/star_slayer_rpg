package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.objects.Ship;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class FuelTank extends PickUp {
    public FuelTank(Vector2 position, float pickupRadius, float lifeTime, Tier tier) {
        super(position, pickupRadius, lifeTime, tier);
    }

    @Override
    public void on_pickup(Ship ship) {

    }
}
