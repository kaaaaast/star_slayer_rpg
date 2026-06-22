package it.unicam.cs.mpgc.rpg130681.utils;

import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;

import java.util.Collection;


public class GameUtils {


    //ricerca l'entità più vicina (alla ship), utile principalmente
    //per la meccanica del proiettile che ha traiettoria automatica verso l'entità distruttibile più vicina a esso.
    public static <T extends GameObject & Destroyable> T getClosestEntity(GameObject src, Collection<T> objects) {

        T closest_found = null;
        float min_distance = Float.MAX_VALUE;

        for (T obj : objects) {
            if (obj.equals(src)) {
                continue;
            }
            float this_distance = obj.getPosition().distanceFrom(src.getPosition());
            if (this_distance < min_distance) {
                closest_found = obj;
                min_distance = this_distance;
            }
        }
        return closest_found;
    }

    //controlla se due entità collidono, rende la classe CollisionSystem più pulita e leggibile.
    public static boolean isColliding(GameObject a, GameObject b) {
        if (a == null || b == null) {
            return false;
        }
        float distance = a.getPosition().distanceFrom(b.getPosition());
        return distance <= a.getRadius() + b.getRadius();
    }
}
