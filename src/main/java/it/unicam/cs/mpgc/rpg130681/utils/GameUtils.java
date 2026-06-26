package it.unicam.cs.mpgc.rpg130681.utils;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.pickups.Tier;
import java.util.Collection;
import java.util.Random;

/**
 * Classe di utilità che offre funzioni che non hanno stato, non appartengono propriamente
 * a classi specifiche, o possono essere usati da più parti nel codice
 */
public final class GameUtils {

    private static final Random random = new Random();

    /**
     * Ricerca l'entità più vicina a un certo oggetto. Principalmente utile per i proiettili che acquisiscono il
     * bersaglio automaticamente, scegliendo l'oggetto distruttibile più vicino.
     * @param sourceObject l'oggetto sorgente.
     * @param objects l'insieme degli oggetti bersaglio.
     * @return l'oggetto {@link GameObject} e {@link Destroyable} più vicino, oppure {@code null} se l'insieme bersaglio è vuoto o invalido.
     * @param <T> il tipo dell'oggetto da ritornare.
     */
    public static <T extends GameObject & Destroyable> T getClosestEntity(GameObject sourceObject, Collection<T> objects) {

        T closestFound = null;
        float minDistance = Float.MAX_VALUE;
        Vector2 sourcePosition = sourceObject.getPosition();

        for (T obj : objects) {

            if (obj.equals(sourceObject)) {
                continue;
            }

            float thisDistance = obj.getPosition().distanceFrom(sourcePosition);

            if (thisDistance < minDistance) {
                closestFound = obj;
                minDistance = thisDistance;
            }
        }

        return closestFound;
    }

    public static <T extends GameObject & Destroyable>
    T getClosestVisibleEntity(GameObject sourceObject, Collection<T> objects, Camera camera) {

        T closestFound = null;
        float minDistance = Float.MAX_VALUE;
        Vector2 sourcePosition = sourceObject.getPosition();

        for (T obj : objects) {

            if (obj.equals(sourceObject)) {
                continue;
            }

            if (!camera.isVisible(obj)) {
                continue;
            }

            float thisDistance = obj.getPosition().distanceFrom(sourcePosition);

            if (thisDistance < minDistance) {
                closestFound = obj;
                minDistance = thisDistance;
            }
        }

        return closestFound;
    }

    /**
     * Controlla se due oggetti generici collidono.
     * @param a il primo oggetto.
     * @param b il secondo oggetto.
     * @return {@code true} se collidono.
     */
    public static boolean isColliding(GameObject a, GameObject b) {

        if (a == null || b == null) {
            return false;
        }

        float distance = a.getPosition().distanceFrom(b.getPosition());
        return distance <= a.getRadius() + b.getRadius();
    }

    /**
     * Genera un tier randomicamente, con una certa probabilità.
     * @return un {@link Tier} casuale.
     */
    public static Tier generateRandomTier() {

        float tierRarityPicker = random.nextFloat();

        if (tierRarityPicker < 0.5f) {
            return Tier.SMALL;
        }

        if (tierRarityPicker < 0.8f) {
            return Tier.MEDIUM;
        }

        return Tier.LARGE;
    }
}
