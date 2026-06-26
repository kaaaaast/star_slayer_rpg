package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.entities.Inventory;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

/**
 * Classe che rappresenta una risorsa / minerale che può essere raccolta dalla navicella del giocatore.
 */
public class ResourceDrop extends PickUp {

    private final DropType drop_type;

    /**
     * Costruisce una risorsa.
     * @param drop_type Il tipo della risorsa.
     */
    public ResourceDrop(Vector2 position, float pickupRadius, float lifeTime, Tier tier, float diameter, DropType drop_type) {
        super(position, pickupRadius, lifeTime, tier, diameter);
        this.drop_type = drop_type;
    }

    /**
     * Aggiunge all'inventario una certa quantità della risorsa raccolta, che aumenta in base al {@link Tier} dell'oggetto.
     * @param ship La navicella a cui applicare l'effetto.
     */
    @Override
    public void on_pickup(Ship ship) {
        Inventory ship_inventory = ship.getInventory();
        int drop_quantity = switch (getTier()) {
            case SMALL -> 2;
            case MEDIUM -> 5;
            case LARGE -> 10;
        };
        ship_inventory.addResource(drop_type,drop_quantity);
    }
}
