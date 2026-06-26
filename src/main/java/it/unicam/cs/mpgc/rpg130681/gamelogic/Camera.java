package it.unicam.cs.mpgc.rpg130681.gamelogic;

import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

/**
 * Classe che rappresenta la camera del gioco, ovvero ciò che viene visualizzato attualmente a schermo.
 */
public class Camera {
    private Vector2 position;
    private static final float width = 1920;
    private static final float height = 1080;

    /**
     * Costruisce la camera.
     * @param position la posizione della camera.
     */
    public Camera (Vector2 position) {
        this.position = position;
    }

    /**
     * Metodo che controlla se un certo oggetto è visibile all'interno della camera.
     * Utile principalmente perché i proiettili sparati dal giocatore bersagliano l'oggetto visibile (distruttibile) più vicino.
     *
     * Per farlo, non basta controllare la posizione dell'oggetto perché la sua dimensione potrebbe comunque permettergli di entrare nella camera
     * nonostante l'oggetto sia fuori da essa.
     *
     * Quindi si calcolano i lati del quadrato circoscritto al cerchio generato
     * dall'oggetto e si verifica l'intersezione fra il quadrato e la camera.
     *
     * In casi estremi potrebbe dire che un oggetto è visibile quando il cerchio è leggermente fuori dalla camera, ma ciò non crea particolari problemi.
     *
     * @param obj l'oggetto di cui bisogna verificare la visibilità
     * @return {@code true} se l'{@code obj} è visibile.
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

    /**
     * Converte le coordinate di una certa posizione generica nel mondo a quelle della camera.
     * @param worldPos la posizione da convertire.
     * @return la posizione convertita.
     */
    public Vector2 worldToScreen (Vector2 worldPos) {
        return worldPos.sub(position).add(new Vector2(width / 2,height / 2));
    }

    /**
     * Converte le coordinate di una certa posizione della camera a quelle generiche nel mondo.
     * @param screenPos la posizione da convertire.
     * @return la posizione convertita.
     */

    public Vector2 screenToWorld (Vector2 screenPos) {
        return screenPos.add(position).sub(new Vector2(width / 2,height / 2));
    }

    /**
     * @return il bordo sinistro della camera.
     */
    public float getLeft() {
        return position.x() - width / 2;
    }

    /**
     * @return il bordo destro della camera.
     */
    public float getRight() {
        return position.x() + width / 2;
    }

    /**
     * @return il bordo superiore della camera.
     */
    public float getTop() {
        return position.y() - height / 2;
    }

    /**
     * @return il bordo inferiore della camera.
     */
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

    /**
     * Imposta la camera in una certa posizione.
     * @param position la posizione a cui impostare la camera.
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
