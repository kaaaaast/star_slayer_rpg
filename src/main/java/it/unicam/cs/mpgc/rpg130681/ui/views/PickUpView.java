package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.image.Image;

import java.util.Objects;

public class PickUpView extends GameObjectView<PickUp> {

    public PickUpView(PickUp pickup) {
        super(pickup, new Image(Objects.requireNonNull(PickUpView.class.getResourceAsStream("/VisualAssets/Sprites/Drops/goldOre.png"))));
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