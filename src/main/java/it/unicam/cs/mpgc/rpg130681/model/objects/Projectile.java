package it.unicam.cs.mpgc.rpg130681.model.objects;

import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Projectile extends GameObject{

    private GameObject target;
    private float speed;
    private float damage;

    public Projectile(float speed, float damage, Vector2 position) {
        super(position);
        this.speed = speed;
        this.damage = damage;
    }
}
