package it.unicam.cs.mpgc.rpg130681.model.stats;

/**
 * Classe che rappresenta una risorsa della navicella, come salute o carburante.
 */

public class ResourceStat {

    private float currentValue;
    private float maxValue;

    /**
     * Imposta la risorsa.
     * @param maxValue Valore massimo della risorsa.
     * @throws IllegalArgumentException se {@code maxValue} è minore o uguale di 0.
     */
    public ResourceStat (float maxValue) {
        if (maxValue <= 0) {
            throw new IllegalArgumentException("Il massimo valore di una risorsa deve essere impostato ad un valore maggiore di 0");
        }
        this.maxValue = maxValue;
        currentValue = maxValue;
    }

    /**
     * Incrementa la risorsa di un certo {@code amount}.
     * @param amount Il valore dell'aumento della risorsa.
     * @throws IllegalArgumentException se {@code amount} è minore o uguale a 0.
     */
    public void increaseResourceBy (float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("L'ammontare della risorsa da aumentare deve essere maggiore di 0");
        }
        currentValue = Math.min(currentValue + amount, maxValue);
    }

    /**
     * Decrementa la risorsa di un certo {@code amount}.
     * @param amount Il valore del decremento della risorsa.
     * @throws IllegalArgumentException se {@code amount} è minore di 0.
     */

    public void decreaseResourceBy (float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("L'ammontare della risorsa da diminuire deve essere maggiore di 0.");
        }
        currentValue = Math.max(currentValue - amount, 0);
    }

    /**
     * Imposta l'attuale valore di una risorsa al suo massimo valore consentito.
     */
    public void completeRefill (){
        currentValue = maxValue;
    }

    /**
     * Aggiorna il massimo valore consentito di una certa risorsa.
     * @param newMaxValue Il nuovo valore massimo.
     * @throws IllegalArgumentException se {@code newMaxValue} è minore o uguale di 0.
     */
    public void setNewMax(float newMaxValue) {
        if (newMaxValue <= 0) {
            throw new IllegalArgumentException("Il valore deve essere maggiore di 0.");
        }
        maxValue = newMaxValue;
        currentValue = Math.min(currentValue, maxValue);
    }

    /**
     * Controlla l'assenza di una risorsa.
     * @return {@code true} se {@code currentValue == 0}.
     */
    public boolean isEmpty() {
        return currentValue == 0;
    }

    /**
     * Controlla se il valore attuale della risorsa è al suo massimo valore consentito.
     * @return {@code true} se {@code currentValue == maxValue}.
     */
    public  boolean isFull() {
        return currentValue == maxValue;
    }

    /**
     * Cambia il valore attuale di una risorsa. Essa viene impostata automaticamente nel range {@code [0, maxValue]}
     * @param value il nuovo valore a cui impostare la risorsa.
     */
    public void setCurrentValue(float value) {
        currentValue = Math.max(0, Math.min(value, maxValue));
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public float getMaxValue() {
        return maxValue;
    }
}
