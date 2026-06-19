package it.unicam.cs.mpgc.rpg130681.gamelogic;

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

    Ad esempio, se la camera si trova a (1000,1000), e un certo pianeta a (1300,900),
    sapendo che il centro dello schermo è (960,540), il pianeta verrà disegnato a coordinate
    "worldtoscreen" = (1260,440).

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
}
