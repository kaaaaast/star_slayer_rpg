package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.utils.ResourceUtils;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.Group;
import javafx.scene.image.ImageView;


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
        body = new ImageView(ResourceUtils.loadImage(getClass(), "/VisualAssets/Sprites/Ship/Main Ship - Base - Full health.png"));
        engine = new ImageView(ResourceUtils.loadImage(getClass(),"/VisualAssets/Sprites/Ship/Main Ship - Engines - Supercharged Engine.png"));
        weapon = AnimatedSprite.fromResource(getClass(), "/VisualAssets/Sprites/Ship/Main Ship - Weapons - Auto Cannon.png", 48, 48, 6, 3);
        engineParticles = AnimatedSprite.fromResource(getClass(),"/VisualAssets/Sprites/Ship/Main Ship - Engines - Supercharged Engine - Powering.png", 48, 48, 4, 3);

        float size = ship.getDiameter();

        configureSprite(body, size);
        configureSprite(engine, size);
        configureSprite(weapon.getImageView(), size);
        configureSprite(engineParticles.getImageView(), size);

        root = new Group(engineParticles.getImageView(), engine, body, weapon.getImageView());
    }

    public void update(Camera camera) {

        Vector2 screenPosition = camera.worldToScreen(ship.getPosition());

        root.setLayoutX(screenPosition.x());
        root.setLayoutY(screenPosition.y());

        root.setRotate(ship.getRotation() + 60);
    }

    public void nextFrame() {
        weapon.nextFrame();
        engineParticles.nextFrame();
    }

    private void configureSprite(ImageView imageView, float size) {
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        float halfSize = size / 2f;
        imageView.setTranslateX(-halfSize);
        imageView.setTranslateY(-halfSize);
    }

    public Group getRoot() {
        return root;
    }

}

