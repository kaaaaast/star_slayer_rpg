package it.unicam.cs.mpgc.rpg130681.ui.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class ShipView {

    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/VisualAssets/Sprites/Ship/Main Ship - Base - Full health.png")));
    ImageView ship_view = new ImageView(image);



}

