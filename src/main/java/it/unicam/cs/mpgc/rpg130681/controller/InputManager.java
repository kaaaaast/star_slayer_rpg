package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe per la gestione degli input di mouse e tastiera.
 */
public class InputManager {

    private static Vector2 mousePos = new Vector2(0,0);

    /*
    Utilizza direttamente un set invece di hardcodare tutti i tasti necessari.
     */
    private static final Set<KeyCode> keys = new HashSet<>();

    public static Vector2 getMousePos() {
        return mousePos;
    }

    public static void setMousePos(Vector2 mousePos) {
        InputManager.mousePos = mousePos;
    }

    public static void keyPressed(KeyCode key) {
        keys.add(key);
    }

    public static boolean isPressed(KeyCode key) {
        return keys.contains(key);
    }

    public static void keyReleased(KeyCode key) {
        keys.remove(key);
    }

}
