package it.unicam.cs.mpgc.rpg130681.model.entities;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Ship  extends  GameObject{
    private float speed;

    public Ship(float maxSpeed, Vector2 position){

        if (maxSpeed < 0) {
            throw new IllegalArgumentException();
        }
        super(position);
        this.speed = maxSpeed;

    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /* Per muovere la navicella, prendiamo la sua posizione attuale e la spostiamo verso
    // la direzione con una certa velocità (normalizzando il vettore).
    // Per scelta di design, la navicella segue il cursore del mouse (più esso è lontano
    più la velocità aumenta) */
    public void move(Vector2 direction) {
        setPosition(getPosition().add(direction.normalize().multiply(speed)));
    }

}
