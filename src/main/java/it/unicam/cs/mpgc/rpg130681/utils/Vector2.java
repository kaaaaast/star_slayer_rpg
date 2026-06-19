package it.unicam.cs.mpgc.rpg130681.utils;

public record Vector2(float x, float y) {

    public Vector2 add(Vector2 o) {
        return new Vector2(this.x + o.x, this.y + o.y);
    }

    public Vector2 sub(Vector2 o) {
        return new Vector2(this.x - o.x, this.y - o.y);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2 multiply(float scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    /* normalizza il vettore a lunghezza 1 per rendere il movimento diagonale equo
     e utilizzare la velocità mantenendo correttamente la direzione */
    public Vector2 normalize() {
        if(length() == 0) {
            return new Vector2(0,0);
        }
        return new Vector2(x / length(), y / length());
    }

    public float distanceFrom(Vector2 o) {
        return (float) Math.sqrt((float) (Math.pow((o.y() - this.y()), 2)) + (float) (Math.pow((o.x() - this.x()), 2)));
    }


}
