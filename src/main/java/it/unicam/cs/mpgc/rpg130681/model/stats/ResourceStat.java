package it.unicam.cs.mpgc.rpg130681.model.stats;

/* Questa classe rappresenta le risorse della nave (vita, carburante etc...),
   creata per evitare di avere metodi di gestione delle risorse nella classe ship, questo
   in previsione di avere n risorse di svariato tipo in futuro.
 */

public class ResourceStat {

    private float current_value;
    private float max_value;

    public ResourceStat (int max_value) {
        if (max_value <= 0) {
            throw new IllegalArgumentException("Il massimo valore di una risorsa deve essere impostato ad un valore maggiore di 0");
        }
        this.max_value = max_value;
        current_value = max_value;
    }

    public float get_current_value() {
        return current_value;
    }

    public float get_max_value() {
        return max_value;
    }

    public void increase_resource_by (float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        current_value = Math.min(current_value + amount, max_value);
    }

    public void decrease_resource_by (float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        current_value = Math.max(current_value - amount, 0);
    }

    public void complete_refill(){
        current_value = max_value;
    }

    public void set_new_max (int new_max_value) {
        max_value = new_max_value;
    }

    public boolean isEmpty() {
        return current_value == 0;
    }

    public  boolean isFull() {
        return current_value == max_value;
    }
}
