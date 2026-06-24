package it.unicam.cs.mpgc.rpg130681.persistence;

import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;

import java.util.Map;

//salvo solo la posizione della Ship, il seed, e le risorse disponibili. rigenero il resto del mondo a partire dal seed
public class SaveData {

    public long seed;
    public float shipX;
    public float shipY;
    public Map<ResourceType, Float> resources;
    public Map<DropType, Integer> inventory;

}
