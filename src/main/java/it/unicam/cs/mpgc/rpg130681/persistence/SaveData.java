package it.unicam.cs.mpgc.rpg130681.persistence;

import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.model.stats.StatType;

import java.util.Map;

/**
 * Classe utile per la persistenza, contiene quali dati vanno salvati.
 */
public class SaveData {
    public long seed;
    public float shipX;
    public float shipY;
    public Map<ResourceType, Float> resources;
    public Map<DropType, Integer> inventory;
    public Map<StatType, Float> stats;
}
