package it.unicam.cs.mpgc.rpg130681.gamelogic;

import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

public class InputManager {

    private static Vector2 mousePos = new Vector2(0,0);

    // anche se il gioco prevede attualmente 1 tasto, c'è possibilità
    // che ne vengano utilizzati diversi in futuro, quindi preferisco un set
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

    //metodo per migliorare leggibilità nel GameLoop
    public static boolean isSpacePressed() {
        return isPressed(KeyCode.SPACE);
    }

    //utile per contesti come il pause button
    public static void clearKeys () {
        keys.clear();
    }
}
