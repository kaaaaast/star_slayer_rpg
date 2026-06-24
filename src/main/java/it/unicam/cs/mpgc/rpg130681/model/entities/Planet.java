package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Planet extends GameObject implements Destroyable {

    // la logica dell'orbita è attualmente attribuita al pianeta stesso
    // più avanti è probabile che vada creata una classe orbita apposita

    private Star parent;
    private float diameter;
    private float angular_speed;
    private float orbit_radius;
    private float health_points;
    private float angle;

    public Planet (Star parent, float diameter, float angularSpeed, float radius, float health_points) {
        if (diameter <= 0 || radius <= 0 || angularSpeed <= 0 || health_points <= 0) {
            throw new IllegalArgumentException("Parametri di creazione del pianeta invalidi.");
        }

        if (parent == null) {
            throw new IllegalArgumentException("Un pianeta ha bisogno di una stella attorno a cui orbitare.");
        }

        super(parent.getPosition(), diameter);
        this.diameter = diameter;
        this.parent = parent;
        this.angular_speed = angularSpeed;
        this.orbit_radius = radius;
        this.health_points = health_points;
        //randomizza l'angolo dell'orbita
        angle = (float)(Math.random() * 2 * Math.PI);
    }

    public void update() {
        angle += angular_speed;
        updateOrbitalPosition();
    }

    @Override
    public boolean isDestroyed() {
        return health_points == 0;
    }

    public void receive_damage(float amount) {
        health_points = Math.max(health_points-amount, 0);
    }

    private void updateOrbitalPosition() {
        Vector2 orbitOffset = new Vector2(orbit_radius * (float)Math.cos(angle), orbit_radius * (float)Math.sin(angle));
        setPosition(parent.getPosition().add(orbitOffset));
    }
}
