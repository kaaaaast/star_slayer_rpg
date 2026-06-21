package it.unicam.cs.mpgc.rpg130681.model.entities;


import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public abstract class GameObject {

    private static int nextid = 0;
    private float diameter;
    private Vector2 position;
    private final int id;

    public GameObject(Vector2 position, float diameter) {
        if (position == null || diameter <= 0) {
            throw new IllegalArgumentException("I parametri di creazione di un oggetto non possono essere nulli");
        }
        this.position = position;
        this.diameter = diameter;
        this.id = nextid++;
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
