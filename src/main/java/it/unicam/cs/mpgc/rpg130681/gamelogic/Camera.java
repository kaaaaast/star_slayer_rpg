package it.unicam.cs.mpgc.rpg130681.gamelogic;

import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

public class Camera {
    private Vector2 position;
    private final float width = 1920;
    private final float height = 1080;

    public Camera (Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /* I due metodi sottostanti servono per tradurre le coordinate da quelle "reali"
    (indipendenti dalla camera) a quelle dello schermo, e viceversa.

    Questo perché la navicella viene mantenuta sempre al centro dello schermo.
    Più precisamente, la navicella e la camera rimangono fisse al centro, mentre il resto del mondo
    trasla. È possibile aggiungere innumerevoli elementi e oggetti e avere un mondo virtualmente infinito
    senza mai cambiare la logica del rendering.
    */
    public Vector2 worldToScreen (Vector2 worldPos) {
        return worldPos.sub(position).add(new Vector2(width / 2,height / 2));
    }

    public Vector2 screenToWorld (Vector2 screenPos) {
        return screenPos.add(position).sub(new Vector2(width / 2,height / 2));
    }

    //TODO finisci il metodo
    public boolean isVisible (GameObject obj) {
        if (obj == null) {
            return false;
        }
        return true;

    }
}
