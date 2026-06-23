package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import it.unicam.cs.mpgc.rpg130681.model.pickups.ResourceDrop;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<DropType, Integer> inventory_resources;

    public Inventory() {
        inventory_resources = new HashMap<>();
    }

    public void addResource(DropType dropType, int amount) {
        inventory_resources.put(dropType, inventory_resources.getOrDefault(dropType, 0) + amount);
    }

    public int get_quantity(DropType dropType) {
        return inventory_resources.getOrDefault(dropType,0);
    }

    public boolean has_resource(DropType dropType, int amount) {
        return get_quantity(dropType) >= amount;
    }

    public void remove_resource(DropType dropType, int amount) {
        inventory_resources.computeIfPresent(dropType, (t, current) -> Math.max(current-amount,0));
    }

    public Map<DropType, Integer> getInventory_resources() {
        return inventory_resources;
    }
}
