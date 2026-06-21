package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Asteroid extends GameObject implements Destroyable {

    public Asteroid(Vector2 position, float diamater) {
        super(position, diamater);
    }
}
