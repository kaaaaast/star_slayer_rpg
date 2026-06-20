package it.unicam.cs.mpgc.rpg130681.model.objects;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Ship  extends  GameObject{
    private float speed;
    private final int max_health_points;
    private int current_health;
    private float fuel;

    public Ship(float maxSpeed, Vector2 position, int max_health_points, float fuel_level){
        super(position);
        if (maxSpeed <= 0 || max_health_points <= 0 || fuel_level <= 0) {
            throw new IllegalArgumentException("Parametri non validi per la creazione della navicella.");
        }
        this.speed = maxSpeed;
        this.max_health_points = max_health_points;
        current_health = max_health_points;
        this.fuel = fuel_level;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getCurrent_health() {
        return current_health;
    }

    public int getMax_health_points() {
        return max_health_points;
    }

    // cura la nave di x hp (la cura è cappata alla maxhealth della nave)
    public void heal_ship (int heal_power) {
        current_health = Math.min(current_health + heal_power, max_health_points);
    }

    public void refill_hp () {
        current_health = this.getMax_health_points();
    }

    /* Per muovere la navicella, prendiamo la sua posizione attuale e la spostiamo verso
                    // la direzione con una certa velocità (normalizzando il vettore).
                    // Per scelta di design, la navicella segue il cursore del mouse (più esso è lontano
                    più la velocità aumenta) */
    public void move(Vector2 direction) {
        setPosition(getPosition().add(direction.normalize().multiply(speed)));
    }

}
