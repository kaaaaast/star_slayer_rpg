package it.unicam.cs.mpgc.rpg130681.utils;

import it.unicam.cs.mpgc.rpg130681.model.objects.GameObject;

import java.util.Collection;


public class GameUtils {


    //ricerca l'entità più vicina (alla ship), utile principalmente
    //per la meccanica del proiettile che ha traiettoria automatica verso l'entità tarketable più vicina a esso.
    public static <T extends GameObject & Targetable> T getClosestEntity(GameObject src, Collection<T> objects) {

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
}
