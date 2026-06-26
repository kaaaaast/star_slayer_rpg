package it.unicam.cs.mpgc.rpg130681.utils;

/**
 * Classe per rappresentare una posizione nello spazio.
 * @param x coordinata delle x.
 * @param y coordinata delle y.
 */
public record Vector2(float x, float y) {

    /**
     * Addizione fra vettori.
     * @param o vettore da addizionare.
     * @return vettore risultante.
     */
    public Vector2 add(Vector2 o) {
        return new Vector2(this.x + o.x, this.y + o.y);
    }

    /**
     * Sottrazione fra vettori.
     * @param o vettore da sottrarre,
     * @return vettore risultante.
     */
    public Vector2 sub(Vector2 o) {
        return new Vector2(this.x - o.x, this.y - o.y);
    }

    /**
     * @return Lunghezza del vettore
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Moltiplicazione di un vettore per uno scalare.
     * @param scalar scalare con cui moltiplicare il vettore.
     * @return vettore risultante.
     */
    public Vector2 multiply(float scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    /**
     * Normalizza il vettore (lunghezza 1)
     * @return il vettore normalizzato
     */
    public Vector2 normalize() {
        if(length() == 0) {
            return new Vector2(0,0);
        }
        return new Vector2(x / length(), y / length());
    }

    /**
     * Calcola la distanza fra due vettori
     * @param o vettore con cui calcolare la distanza.
     * @return la distanza calcolata.
     */
    public float distanceFrom(Vector2 o) {
        return (float) Math.sqrt((float) (Math.pow((o.y() - this.y()), 2)) + (float) (Math.pow((o.x() - this.x()), 2)));
    }


}
