package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

/**
 * Classe che rappresenta un generico asteroide.
 */
public class Asteroid extends GameObject implements Destroyable {

    private float health_points;

    public Asteroid(Vector2 position, float diameter, float health_points) {
        super(position, diameter);
        if (health_points <= 0) {
            throw new IllegalArgumentException();
        }
        this.health_points = health_points;
    }

    @Override
    public boolean isDestroyed() {
        return health_points <= 0;
    }

    @Override
    public void receive_damage(float amount) {
        health_points = Math.max(health_points-amount, 0);
    }
}
