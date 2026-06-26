package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.Asteroid;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.image.Image;

/**
 * Classe per le visualizzare gli asteroidi.
 */
public class AsteroidView extends GameObjectView<Asteroid> {

    public AsteroidView(Asteroid asteroid, Image image) {
        super(asteroid, image);
    }

    @Override
    public void update(Camera camera) {
        Vector2 screenPos = camera.worldToScreen(object.getPosition());
        image_view.setLayoutX(screenPos.x());
        image_view.setLayoutY(screenPos.y());
    }
}
