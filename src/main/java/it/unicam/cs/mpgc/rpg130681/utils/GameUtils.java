package it.unicam.cs.mpgc.rpg130681.utils;

import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.pickups.Tier;
import java.util.Collection;
import java.util.Random;

//piccola classe di utilità che offre funzioni che non hanno stato, non appartengono propriamente
//a classi specifiche, o possono essere usati da più parti nel codice

public class GameUtils {

    private static final Random random = new Random();
    //ricerca l'entità più vicina (alla ship), utile principalmente
    //per la meccanica del proiettile che ha traiettoria automatica verso l'entità distruttibile più vicina a esso.
    public static <T extends GameObject & Destroyable> T getClosestEntity(GameObject source_object, Collection<T> objects) {

        T closest_found = null;
        float min_distance = Float.MAX_VALUE;
        Vector2 source_position = source_object.getPosition();

        for (T obj : objects) {

            if (obj.equals(source_object)) {
                continue;
            }

            float this_distance = obj.getPosition().distanceFrom(source_position);

            if (this_distance < min_distance) {
                closest_found = obj;
                min_distance = this_distance;
            }
        }

        return closest_found;
    }

    //controlla se due entità collidono, principalmente rende la classe CollisionSystem più pulita.
    public static boolean isColliding(GameObject a, GameObject b) {

        if (a == null || b == null) {
            return false;
        }

        float distance = a.getPosition().distanceFrom(b.getPosition());
        return distance <= a.getRadius() + b.getRadius();
    }

    //genera un tier randomicamente con una certa probabilità
    public static Tier generate_random_tier() {

        float tier_rarity_picker = random.nextFloat();

        if (tier_rarity_picker < 0.5f) {
            return Tier.SMALL;
        }

        if (tier_rarity_picker < 0.8f) {
            return Tier.MEDIUM;
        }

        return Tier.LARGE;
    }
}
