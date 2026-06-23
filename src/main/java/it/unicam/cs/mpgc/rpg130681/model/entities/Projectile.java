package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

//classe per rappresentare un proiettile. impiega i generics per evitare di dover
//mettere un getposition nell'interfaccia Destroyable, oppure dover introdurre un'altra
//interfaccia Targetable, oppure dove fare cast/instanceof relativamente a Destroyable.
//semplicemente un target generico deve essere gameobject e destroyable, che è effettivamente
//una proprietà di tutti gli oggetti distruttibili del gioco.
public class Projectile <T extends GameObject & Destroyable> extends GameObject {

    private T target;
    private float speed;
    private float damage;
    private float hit_radius;
    private float remaining_lifetime;

    public Projectile(T target, Vector2 position, float speed, float damage, float hit_radius, float diameter) {
        super(position, diameter);
        this.target = target;
        this.speed = speed;
        this.damage = damage;
        this.hit_radius = hit_radius;
        remaining_lifetime = 3.0f;
    }

    public void update_projectile() {

        float update_delta = 0.02f;
        remaining_lifetime = Math.max(remaining_lifetime - update_delta, 0);

        if (remaining_lifetime == 0) {
            setShould_remove(true);
        }

        if (target.isDestroyed()) {
            setShould_remove(true);
        }

        if (!should_remove()) {
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

    public T getTarget() {
        return target;
    }

}
