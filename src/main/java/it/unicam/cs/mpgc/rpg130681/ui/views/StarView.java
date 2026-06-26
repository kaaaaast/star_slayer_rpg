package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.Star;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.image.Image;

/**
 * Classe per la visualizzazione delle stelle.
 */
public class StarView extends GameObjectView<Star> {

    public StarView(Star star, Image image) {
        super(star, image);
    }

    @Override
    public void update(Camera camera) {
        Vector2 screenPos = camera.worldToScreen(object.getPosition());
        image_view.setPreserveRatio(true);
        image_view.setFitWidth(object.getDiameter());
        image_view.setLayoutX(screenPos.x());
        image_view.setLayoutY(screenPos.y());
    }
}
