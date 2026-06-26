package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe che rappresenta l'inventario del giocatore.
 */

public class Inventory {

    private final Map<DropType, Integer> inventory_resources;

    /**
     * Crea un inventario vuoto.
     */
    public Inventory() {
        inventory_resources = new HashMap<>();
    }

    /**
     * Aggiunge una quantità {@code > 0} a un certo {@link DropType} che si trova nell'inventario.
     *
     * @param dropType Il {@link DropType} che si vuole incrementare.
     * @param amount La quantità da aggiungere.
     * @throws IllegalArgumentException se {@code amount} è strettamente negativo.
     */
    public void addResource(DropType dropType, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("La quantità da aggiungere deve essere strettamente positiva.");
        }
        inventory_resources.put(dropType, inventory_resources.getOrDefault(dropType, 0) + amount);
    }

    /**
     * Rimuove un {@code amount > 0} di un certo {@link DropType} che si trova nell'inventario.
     *
     * @param dropType Il {@link DropType} che si vuole decrementare.
     * @param amount La quantità da rimuovere.
     */
    public void remove_resource(DropType dropType, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Non puoi rimuovere una quantità minore o uguale di 0.");
        }
        inventory_resources.computeIfPresent(dropType, (t, current) -> Math.max(current-amount,0));
    }

    /**
     * Restituisce la quantità di un certo {@link DropType} specificato, se presente.
     *
     * @param dropType Il {@link DropType} di cui si vuole conoscere la quantità disponibile
     * @return La quantità presente nell'inventario.
     */
    public int get_quantity(DropType dropType) {
        return inventory_resources.getOrDefault(dropType,0);
    }

    /**
     * Restituisce {@code true} se nell'inventario esiste almeno un certo {@code amount} di uno specifico
     * {@link DropType}.
     *
     * @param dropType Il {@link DropType} di cui si vuole controllare la sufficiente disponibilità.
     * @param amount La quantità da controllare.
     * @return {@code true} Se esiste una quantità sufficiente di quel {@link DropType} nell'inventario.
     */
    public boolean has_resource(DropType dropType, int amount) {
        return get_quantity(dropType) >= amount;
    }

    /**
     * Imposta una qualsiasi quantità associata allo specifico {@link DropType}.
     *
     * @param dropType Il {@link DropType} che si vuole impostare.
     * @param quantity La nuova quantità della risorsa.
     */
    public void setQuantity(DropType dropType, Integer quantity) {
        inventory_resources.put(dropType,quantity);
    }

    public Map<DropType, Integer> getInventory_resources() {
        return inventory_resources;
    }
}
