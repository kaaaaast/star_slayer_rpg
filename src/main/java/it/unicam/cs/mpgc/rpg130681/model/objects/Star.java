package it.unicam.cs.mpgc.rpg130681.model.objects;

import it.unicam.cs.mpgc.rpg130681.utils.Targetable;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Star extends GameObject implements Targetable {

    private int health_points;
    private float diameter;

    public Star(Vector2 position, int health_points, float diameter) {
        super(position);
        this.health_points = health_points;
        this.diameter = diameter;
    }



}
