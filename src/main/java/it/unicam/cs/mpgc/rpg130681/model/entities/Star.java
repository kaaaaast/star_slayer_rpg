package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Star extends GameObject implements Destroyable {

    private int health_points;
    private float diameter;

    public Star(Vector2 position, int health_points, float diameter) {
        super(position, diameter/2);
        this.health_points = health_points;
        this.diameter = diameter;
    }



}
