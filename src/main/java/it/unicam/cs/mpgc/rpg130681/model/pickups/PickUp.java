package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.objects.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.objects.Ship;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public abstract class PickUp extends GameObject {

    private float pickupRadius;
    private float lifeTime;
    private final Tier tier;

    public PickUp (Vector2 position, float pickupRadius, float lifeTime, Tier tier) {
        if (lifeTime <= 0) {
            throw new IllegalArgumentException("I pickups devono rimanere disponibili per qualche secondo.");
        }
        super(position);
        this.pickupRadius = pickupRadius;
        this.lifeTime = lifeTime;
        this.tier = tier;
    }

    public abstract void on_pickup (Ship ship);

    public float getLifeTime() {
        return lifeTime;
    }

    public float getPickupRadius() {
        return pickupRadius;
    }

    public Tier getTier() {
        return tier;
    }

    @Override
    public Vector2 getPosition() {
        return super.getPosition();
    }
}
