package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.utils.ResourceUtils;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Classe per la visualizzazione degli oggetti {@link PickUp}.
 */
public class PickUpView extends GameObjectView<PickUp> {

    /**
     * Costruisce la visualizzazione del PickUp.
     * @param pickup il pickup da visualizzare.
     */
    public PickUpView(PickUp pickup) {
        super(pickup, ResourceUtils.loadImage(PickUpView.class, "/VisualAssets/Sprites/Drops/goldOre.png"));
    }

    @Override
    public void update(Camera camera) {
        Vector2 screenPos = camera.worldToScreen(object.getPosition());
        image_view.setLayoutX(screenPos.x());
        image_view.setLayoutY(screenPos.y());
    }

    public PickUp getPickUp() {
        return object;
    }
}