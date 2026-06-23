package it.unicam.cs.mpgc.rpg130681.model.pickups;

import it.unicam.cs.mpgc.rpg130681.model.entities.Inventory;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class ResourceDrop extends PickUp {

    private final DropType drop_type;

    public ResourceDrop(Vector2 position, float pickupRadius, float lifeTime, Tier tier, float diameter, DropType drop_type) {
        super(position, pickupRadius, lifeTime, tier, diameter);
        this.drop_type = drop_type;
    }

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
