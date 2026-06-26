package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

/**
 * Classe che rappresenta una tanica di carburante. Essa può essere raccolta dal giocatore per rigenerare il carburante.
 */
public class Fuel extends PickUp {
    /**
     * Costruisce una tanica di carburante.
     */
    public Fuel(Vector2 position, float pickupRadius, float lifeTime, Tier tier, float diameter) {
        super(position, pickupRadius, lifeTime, tier, diameter);
    }

    /**
     * Rigenera di una certa quantità il carburante della navicella, in base al {@link Tier} dell'oggetto raccolto.
     * @param ship La navicella a cui applicare l'effetto.
     */
    @Override
    public void on_pickup(Ship ship) {
        float max_fuel = ship.getResource(ResourceType.FUEL).getMaxValue();

        switch (this.getTier()) {

            case SMALL:
                ship.getResource(ResourceType.FUEL).increaseResourceBy(max_fuel*0.2f);
                break;

            case MEDIUM:
                ship.getResource(ResourceType.FUEL).increaseResourceBy(max_fuel*0.5f);
                break;

            case LARGE:
                ship.getResource(ResourceType.FUEL).completeRefill();
                break;
        }
    }
}
