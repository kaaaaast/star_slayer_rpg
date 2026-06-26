package it.unicam.cs.mpgc.rpg130681.model.entities;

import it.unicam.cs.mpgc.rpg130681.ui.views.AudioManager;
import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;

/**
 * Classe per rappresentare i pianeti che popolano lo spazio.
 */
public class Planet extends GameObject implements Destroyable {

    private final Star parent;
    private final float angularSpeed;
    private final float orbitRadius;
    private float healthPoints;
    private float angle;

    /**
     * Costruisce un pianeta.
     * @param parent La {@link Star} attorno alla quale il pianeta orbita.
     * @param diameter Il diametro del pianeta.
     * @param angularSpeed  La velocità angolare dell'orbita.
     * @param orbitRadius Il raggio dell'orbita.
     * @param healthPoints I punti vita del pianeta.
     * @throws IllegalArgumentException Se almeno uno fra {@code diameter, orbitRadius, angularSpeed, healthPoints} è negativo o 0, oppure se
     * {@code parent} è null.
     */
    public Planet (Star parent, float diameter, float angularSpeed, float orbitRadius, float healthPoints) {
        if (diameter <= 0 || orbitRadius <= 0 || angularSpeed <= 0 || healthPoints <= 0) {
            throw new IllegalArgumentException("Parametri di creazione del pianeta invalidi.");
        }

        if (parent == null) {
            throw new IllegalArgumentException("Un pianeta ha bisogno di una stella attorno a cui orbitare.");
        }

        super(parent.getPosition(), diameter);
        this.parent = parent;
        this.angularSpeed = angularSpeed;
        this.orbitRadius = orbitRadius;
        this.healthPoints = healthPoints;
        // Inizializza il pianeta con un angolo orbitale casuale.
        angle = (float)(Math.random() * 2 * Math.PI);
    }


    public void update() {
        angle += angularSpeed;
        updateOrbitalPosition();
    }

    /**
     * Aggiorna la posizione del pianeta in base ai parametri di orbita.
     */
    private void updateOrbitalPosition() {

        //La logica dell' orbita è attualmente attribuita al pianeta stesso, poiché è l'unico oggetto orbitante.
        //Tuttavia in futuro sarebbe opportuno spostare l'orbita in una classe apposita per questioni di estensibilità.

        Vector2 orbitOffset = new Vector2(orbitRadius * (float)Math.cos(angle), orbitRadius * (float)Math.sin(angle));
        setPosition(parent.getPosition().add(orbitOffset));
    }

    @Override
    public boolean isDestroyed() {
        return healthPoints == 0;
    }

    /**
     * Applica un certo danno {@code amount} al pianeta. Se dopo l'applicazione del danno il pianeta
     * ha 0 punti di vita viene distrutto, ovvero viene segnalato come rimovibile.
     * @param amount Il danno da applicare.
     * @throws IllegalArgumentException se {@code amount} è strettamente negativo.
     */
    public void receive_damage(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Il pianeta deve ricevere un danno positivo.");
        }
        healthPoints = Math.max(healthPoints -amount, 0);
        if (isDestroyed()) {
            setShouldRemove(true);
            AudioManager.playExplosion();
        }
    }
}
