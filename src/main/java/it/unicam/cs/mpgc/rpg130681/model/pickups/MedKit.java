package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

/**
 * Classe che rappresenta un kit medico, che può essere raccolto per rigenerare la vita della navicella.
 */
public class MedKit extends PickUp{

    /**
     * Costruisce un kit medico.
     */
    public MedKit(Vector2 position, float pickupRadius, float lifeTime, Tier tier, float diameter) {
        super(position, pickupRadius, lifeTime, tier, diameter);
    }

    /**
     * Rigenera i punti vita della navicella di una certa quantità, in base al {@link Tier} del kit medico raccolto.
     * @param ship La navicella a cui applicare l'effetto.
     */
    @Override
    public void on_pickup(Ship ship) {

        float max_hp = ship.getResource(ResourceType.HEALTH).getMaxValue();

        switch (getTier()) {

            case SMALL:
                ship.getResource(ResourceType.HEALTH).increaseResourceBy(max_hp*0.2f);
                break;

            case MEDIUM:
                ship.getResource(ResourceType.HEALTH).increaseResourceBy(max_hp*0.5f);
                break;

            case LARGE:
                ship.getResource(ResourceType.HEALTH).completeRefill();
                break;
        }
    }
}
