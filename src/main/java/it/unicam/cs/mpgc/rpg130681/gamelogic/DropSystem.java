package it.unicam.cs.mpgc.rpg130681.gamelogic;


import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import it.unicam.cs.mpgc.rpg130681.model.pickups.ResourceDrop;
import it.unicam.cs.mpgc.rpg130681.utils.GameUtils;

import java.util.*;

/**
 * Classe che gestisce il sistema di drop delle risorse.
 */
public class DropSystem {

    private static final Random random = new Random();

    /**
     * Genera un insieme di drop.
     *
     * @param gameObject l'oggetto che rilascia il drop.
     * @return una {@code List<ResourceDrop>} contenenti i drop generati, oppure {@link Collections#emptyList()} se l'oggetto passato non è ancora distrutto.
     */
    public List<ResourceDrop> generateDrops (GameObject gameObject) {
        if (!gameObject.shouldRemove()){
            //Collections.emptyList() per evitare null
            return Collections.emptyList();
        }
        ResourceDrop generated_drop = new ResourceDrop(gameObject.getPosition(), gameObject.getRadius()*1.5f, 20, GameUtils.generateRandomTier(), 20, generateRandomDropType());
        return List.of(generated_drop);
    }

    /**
     * Sceglie un tipo di drop di risorse casuale tra quelli disponibili in {@link DropType}, con una certa probabilità.
     * @return il tipo {@link DropType} scelto.
     *
     * Probabilità attuali:
     * Iron: 40%
     * Copper: 25%
     * Gold: 15%
     * Platinum: 10%
     * Titanium: 6%
     * Uranium: 3.5%
     * Strange Matter: 0.5%
     */
    private DropType generateRandomDropType() {
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
