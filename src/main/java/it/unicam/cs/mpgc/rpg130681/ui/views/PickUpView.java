package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.image.Image;

public class PickUpView extends GameObjectView<PickUp> {

    public PickUpView(PickUp pickup, Image image) {
        super(pickup, image);
    }

    @Override
    public void update(Camera camera) {
        Vector2 screenPos = camera.worldToScreen(object.getPosition());
        image_view.setLayoutX(screenPos.x());
        image_view.setLayoutY(screenPos.y());
    }

}
