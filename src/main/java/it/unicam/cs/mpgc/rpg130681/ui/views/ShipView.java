package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

//ShipView non estende GameObjectView perché è composta da più sprites (group con motore, particelle del motore e cannoni)

public class ShipView {

    private final Ship ship;

    private final ImageView body;

    private final ImageView engine;

    private final AnimatedSprite engineParticles;

    private final AnimatedSprite weapon;

    private final Group root;

    public ShipView(Ship ship) {

        this.ship = ship;

        body = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/VisualAssets/Sprites/Ship/Main Ship - Base - Full health.png"))));

        engine = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/VisualAssets/Sprites/Ship/Main Ship - Engines - Supercharged Engine.png"))));

        weapon = new AnimatedSprite(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/VisualAssets/Sprites/Ship/Main Ship - Weapons - Auto Cannon.png"))), 48, 48, 6);

        engineParticles = new AnimatedSprite(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/VisualAssets/Sprites/Ship/Main Ship - Engines - Supercharged Engine - Powering.png"))), 48, 48, 4);

        root = new Group(engineParticles.getImageView(), engine, body, weapon.getImageView());
    }

    public void update(Camera camera) {

        Vector2 screenPosition = camera.worldToScreen(ship.getPosition());

        root.setLayoutX(screenPosition.x());
        root.setLayoutY(screenPosition.y());

        //la nave dovrebbe puntare/ruotare sempre verso il puntatore del mouse, tempo permettendo questa funzione verrà implementata
        //root.setRotate(ship.getRotation());
    }

    public void nextFrame() {
        weapon.nextFrame();
        engineParticles.nextFrame();
    }

    public Group getRoot() {
        return root;
    }

}

