package it.unicam.cs.mpgc.rpg130681.model.stats;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe che rappresenta le statistiche della navicella, come velocità o fire rate.
 * Da notare: a differenza di {@link ResourceStat}, le statistiche non richiedono la memorizzazione
 * di un valore corrente e di un valore massimo, per cui vengono rappresentate tramite
 * una semplice mappa.
 */
public class ShipStats {

    private final Map<StatType, Float> stats;

    /**
     * Costruisce le statistiche della navicella.
     * @param stats la mappa che contiene le statistiche iniziali.
     *
     */
    public ShipStats(Map<StatType, Float> stats) {
        this.stats = new HashMap<>(stats);
    }

    /**
     * Restituisce una certa statistica della navicella.
     * @param type La statistica ricercata.
     * @return il valore relativo alla statistica {@code type}.
     * @throws IllegalArgumentException se la statistica non esiste.
     */
    public float getStat(StatType type) {
        //evita nullPointerException
        if (!stats.containsKey(type)) {
            throw new IllegalArgumentException("La statistica " + type + " non è presente.");
        }

        return stats.get(type);
    }

    /**
     * Aumenta una statistica della navicella.
     * @param type La statistica da aumentare.
     * @param amount Il valore dell'aumento della statistica.
     * @throws IllegalArgumentException se {@code amount} è minore o uguale a 0.
     */
    public void increaseStat(StatType type, float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        stats.put(type, getStat(type) + amount);
    }

    /**
     * Ritorna la mappa contenente le statistiche della nave.
     * @return la mappa delle statistiche.
     */
    public Map<StatType, Float> getStats() {
        return stats;
    }

    /**
     * Aggiorna il valore di una statistica.
     * Principalmente usato per la persistenza.
     */
    public void setStat(StatType type,float value) {
        stats.put(type, value);
    }


}