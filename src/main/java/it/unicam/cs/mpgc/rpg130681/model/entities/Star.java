package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

/**
 * Classe che rappresenta una generica stella
 */
public class Star extends GameObject{

    private float healthPoints;

    public Star(Vector2 position, float healthPoints, float diameter) {
        super(position, diameter);
        this.healthPoints = healthPoints;
    }

    public float getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(float healthPoints) {
        this.healthPoints = healthPoints;
    }
}
