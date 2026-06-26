package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

/**
 * Classe che rappresenta un generico oggetto che può essere raccolto.
 */
public abstract class PickUp extends GameObject {

    private final float pickupRadius;
    private final float lifeTime;
    private final Tier tier;

    /**
     * Costruisce l'oggetto da raccogliere.
     * @param position La posizione dell'oggetto.
     * @param pickupRadius Il raggio entro il quale l'oggetto viene raccolto.
     * @param lifeTime Quanto tempo l'oggetto rimane disponibile per essere raccolto.
     * @param tier La rarità dell'oggetto.
     * @param diameter Il diametro dell'oggetto.
     * @throws IllegalArgumentException se {@code lifeTime} o {@code pickupRadius} sono minori o uguali a 0.
     */
    public PickUp (Vector2 position, float pickupRadius, float lifeTime, Tier tier, float diameter) {
        if (lifeTime <= 0 || pickupRadius <= 0) {
            throw new IllegalArgumentException("I pickups devono rimanere disponibili per un certo periodo di tempo maggiore di 0.");
        }
        super(position, diameter);
        this.pickupRadius = pickupRadius;
        this.lifeTime = lifeTime;
        this.tier = tier;
    }

    /**
     * Applica l'effetto dell'oggetto raccolto alla navicella.
     * @param ship La navicella a cui applicare l'effetto.
     */
    public abstract void on_pickup (Ship ship);

    public float getLifeTime() {
        return lifeTime;
    }

    /**
     * @return il raggio entro il quale l'oggetto viene raccolto.
     */
    @Override
    public float getRadius() {
        return pickupRadius;
    }

    public Tier getTier() {
        return tier;
    }
}
