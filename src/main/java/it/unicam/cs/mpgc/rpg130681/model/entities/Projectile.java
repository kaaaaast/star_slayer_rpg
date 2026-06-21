package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.utils.GameUtils;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Projectile extends GameObject{

    private GameObject target;
    private float speed;
    private float damage;
    private float hit_radius;

    public Projectile(GameObject target, Vector2 position, float speed, float damage, float hit_radius, float diameter) {
        super(position, diameter);
        //this.target = GameUtils.getClosestEntity(this, )//
        this.speed = speed;
        this.damage = damage;
        this.hit_radius = hit_radius;
    }

    //TODO completare la classe insieme a Gamemanager, camera, collisionsystem



}
