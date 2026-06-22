package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
public class Projectile <T extends GameObject & Destroyable> extends GameObject {

    private T target;
    private float speed;
    private float damage;
    private float hit_radius;
    private float remaining_lifetime;
    private boolean remove_self;

    public Projectile(T target, Vector2 position, float speed, float damage, float hit_radius, float diameter) {
        super(position, diameter/2);
        this.target = target;
        this.speed = speed;
        this.damage = damage;
        this.hit_radius = hit_radius;
        remaining_lifetime = 3.0f;
        remove_self = false;
    }

    public void update_projectile() {

        float update_delta = 0.02f;
        remaining_lifetime = Math.max(remaining_lifetime - update_delta, 0);

        if (remaining_lifetime == 0) {
            setRemove_self(true);
        }

        if (target.isDestroyed()) {
            setRemove_self(true);
        }

        if (!remove_self) {
            Vector2 direction = target.getPosition().sub(getPosition());
            setPosition(getPosition().add(direction.normalize().multiply(speed)));
        }
    }

    public float getDamage() {
        return damage;
    }

    public float getHit_radius() {
        return hit_radius;
    }

    public boolean isRemove_self() {
        return remove_self;
    }

    public void setRemove_self(boolean destroy_self) {
        this.remove_self = destroy_self;
    }
}
