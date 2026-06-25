package it.unicam.cs.mpgc.rpg130681.gamelogic;


import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import it.unicam.cs.mpgc.rpg130681.model.pickups.ResourceDrop;
import it.unicam.cs.mpgc.rpg130681.utils.GameUtils;

import java.util.*;


public class DropSystem {

    private static final Random random = new Random();

    public List<ResourceDrop> generate_drops (GameObject gameObject) {
        if (!gameObject.should_remove()){
            // ritorno Collections.emptyList() al posto di null per evitare errori / inconsistenze
            return Collections.emptyList();
        }
        ResourceDrop generated_drop = new ResourceDrop(gameObject.getPosition(), gameObject.getRadius()*1.5f, 20, GameUtils.generate_random_tier(), 20, generate_random_drop_type());
        return List.of(generated_drop);
    }

    // genera un drop casuale tra quelli disponibili, con una certa rarità.
    private DropType generate_random_drop_type() {
        float drop_rarity_picker = random.nextFloat();

        if (drop_rarity_picker < 0.40f) {
            return DropType.IRON;
        }

        if (drop_rarity_picker < 0.65f) {
            return DropType.COPPER;
        }

        if (drop_rarity_picker < 0.80f) {
            return DropType.GOLD;
        }

        if (drop_rarity_picker < 0.90f) {
            return DropType.PLATINUM;
        }

        if (drop_rarity_picker < 0.96f) {
            return DropType.TITANIUM;
        }

        if (drop_rarity_picker < 0.995f) {
            return DropType.URANIUM;
        }

        return DropType.STRANGE_MATTER;
    }

}
