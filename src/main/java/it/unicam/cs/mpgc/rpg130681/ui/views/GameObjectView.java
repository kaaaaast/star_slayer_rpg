package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import javafx.scene.image.ImageView;

public abstract class GameObjectView <T extends GameObject> {

    protected T object;
    protected ImageView image_view;

    public abstract void update(Camera camera);

    public ImageView getImage_view() {
        return image_view;
    }
}
