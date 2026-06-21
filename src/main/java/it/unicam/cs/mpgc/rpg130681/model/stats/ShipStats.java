package it.unicam.cs.mpgc.rpg130681.model.stats;

import java.util.HashMap;
import java.util.Map;

public class ShipStats {

    private Map<StatType, Float> stats;

    public ShipStats(Map<StatType, Float> stats) {
        this.stats = new HashMap<>(stats);
    }

    public float getStat(StatType type) {
        //evita eventuali null
        if (!stats.containsKey(type)) {
            throw new IllegalArgumentException("La statistica " + type + " non è presente.");
        }

        return stats.get(type);
    }

    public void increaseStat(StatType type, float amount) {
        stats.put(type, stats.get(type) + amount);
    }
}