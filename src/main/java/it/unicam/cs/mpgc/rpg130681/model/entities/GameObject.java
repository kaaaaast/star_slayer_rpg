package it.unicam.cs.mpgc.rpg130681.model.entities;


import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;

/**
 * Classe utile per rappresentare un generico oggetto del gioco.
 * Fornisce le proprietà base condivise da tutti gli  oggetti, come la loro posizione, grandezza e ID.
 */

public abstract class GameObject {

    private static int nextid = 0;
    private final float diameter;
    private Vector2 position;
    private final int id;
    private boolean shouldRemove;

    /**
     * Costruisce un nuovo GameObject.
     *
     * @param position Posizione dell'oggetto nel mondo
     * @param diameter Diametro dell'oggetto da creare
     * @throws IllegalArgumentException se {@code position} è {@code null}
     * oppure se {@code diameter} è {@code <= 0}.
     */
    public GameObject(Vector2 position, float diameter) {
        if (position == null || diameter <= 0) {
            throw new IllegalArgumentException("I parametri di creazione di un oggetto non possono essere nulli");
        }
        this.position = position;
        this.diameter = diameter;
        this.id = nextid++;
        shouldRemove = false;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition (Vector2 position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public float getDiameter() {
        return diameter;
    }

    /**
     * Ritorna il raggio di un certo oggetto.
     *
     * @return Metà del diametro dell'oggetto
     */
    public float getRadius() {
        return diameter/2;
    }

    /**
     * Indica se un certo oggetto deve essere rimosso dal mondo al prossimo ciclo di updates.
     *
     * @return {@code true} se l'oggetto deve essere rimosso.
     */
    public boolean shouldRemove() {
        return shouldRemove;
    }

    /**
     * Assegna a un certo oggetto la necessità di essere rimosso, ad esempio dopo che un {@link Planet} è stato
     * distrutto oppure dopo che un {@link PickUp} è stato raccolto
     *
     * @param shouldRemove Flag di rimozione del GameObject
     */
    public void setShouldRemove(boolean shouldRemove) {
        this.shouldRemove = shouldRemove;
    }

    // Due GameObject sono uguali se hanno lo stesso id
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof GameObject)) {
            return false;
        }

        return this.id == ((GameObject) obj).id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
