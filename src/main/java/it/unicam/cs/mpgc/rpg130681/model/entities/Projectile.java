package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

/**
 * Classe per rappresentare i proiettili.
 * @param <T> Il target del proiettile, che deve essere sia {@link GameObject} che {@link Destroyable}.
 * La classe sfrutta i generics per evitare di dover inserire un {@code getPosition()} nell'interfaccia {@link Destroyable},
 * oppure dover introdurre un'altra interfaccia {@code Targetable}, oppure dover fare cast / controlli {@code instanceof}.
 */
public class Projectile <T extends GameObject & Destroyable> extends GameObject {

    private T target;
    private float speed;
    private float damage;
    private float hitRadius;
    private float remainingLifetime;

    /**
     * Costruisce un proiettile.
     *
     * @param target Il bersaglio del proiettile.
     * @param position La posizione da cui parte il proiettile.
     * @param speed La velocità del proiettile.
     * @param damage Il danno del proiettile.
     * @param hitRadius Il raggio entro il quale il proiettile colpisce il bersaglio.
     * @param diameter Il diametro del proiettile
     * @throws IllegalArgumentException se almeno uno fra {@code speed, damage, hitRadius} è {@code <= 0},
     * oppure se {@code target} è null.
     */
    public Projectile(T target, Vector2 position, float speed, float damage, float hitRadius, float diameter) {
        if (target == null || speed <= 0 || damage <= 0 || hitRadius <= 0) {
            throw new IllegalArgumentException("Parametri di creazione del proiettile invalidi");
        }
        super(position, diameter);
        this.target = target;
        this.speed = speed;
        this.damage = damage;
        this.hitRadius = hitRadius;
        //Fattore che rappresenta quanto rimane attivo il proiettile prima di essere impostato come rimovibile.
        remainingLifetime = 3.0f;
    }

    /**
     * Aggiorna la posizione in base allo stato del {@code target} e al proprio {@code remainingLifetime}.
     * Se il bersaglio non è distrutto e il {@code remainingLifetime} è maggiore di 0, si muove verso di esso.
     */
    public void updateProjectile() {

        float update_delta = 0.02f;
        remainingLifetime = Math.max(remainingLifetime - update_delta, 0);

        if (remainingLifetime == 0) {
            setShouldRemove(true);
        }

        if (target.isDestroyed()) {
            setShouldRemove(true);
        }

        if (!shouldRemove()) {
            Vector2 direction = target.getPosition().sub(getPosition());
            setPosition(getPosition().add(direction.normalize().multiply(speed)));
        }
    }

    public float getDamage() {
        return damage;
    }

    public float getHitRadius() {
        return hitRadius;
    }

    public T getTarget() {
        return target;
    }

}