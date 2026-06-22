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

    /*
    Metodo che controlla se un certo oggetto è visibile all'interno della camera
    è utile principalmente perché i proiettili sparati dal giocatore targettano l'oggetto visibile (distruttibile) più vicino.
    Per farlo, non basta controllare la posizione dell'oggetto perché la sua dimensione potrebbe comunque permettergli di entrare nella camera
    nonostante l'oggetto sia fuori da essa. Quindi si calcolano i lati del quadrato circoscritto al cerchio generato
    dall'oggetto e si verifica l'intersezione fra il quadrato e la camera.
    In casi estremi potrebbe dire che un oggetto è visibile quando il cerchio è leggermente fuori dalla camera, ma ciò non crea particolari problemi.
    */
    public boolean isVisible(GameObject obj) {

        if (obj == null) {
            return false;
        }

        float x = obj.getPosition().x();
        float y = obj.getPosition().y();
        float radius = obj.getRadius();

        float obj_left = x - radius;
        float obj_right = x + radius;
        float obj_top = y - radius;
        float obj_bottom = y + radius;

        if (obj_right < getLeft()) {
            return false;
        }

        if (obj_left > getRight()) {
            return false;
        }

        if (obj_bottom < getTop()) {
            return false;
        }

        if (obj_top > getBottom()) {
            return false;
        }
        return true;
    }

    /*
    I due metodi sottostanti servono per tradurre le coordinate da quelle "reali"
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

    public float getLeft() {
        return position.x() - width / 2;
    }

    public float getRight() {
        return position.x() + width / 2;
    }

    public float getTop() {
        return position.y() - height / 2;
    }

    public float getBottom() {
        return position.y() + height / 2;
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
}
