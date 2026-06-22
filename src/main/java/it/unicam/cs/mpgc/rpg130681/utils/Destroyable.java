package it.unicam.cs.mpgc.rpg130681.utils;

public interface Destroyable {
    boolean isDestroyed();
    void receive_damage(float amount);
}
