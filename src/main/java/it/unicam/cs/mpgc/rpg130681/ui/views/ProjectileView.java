package it.unicam.cs.mpgc.rpg130681.ui.views;

import javafx.scene.image.Image;

import java.util.Objects;

public class ProjectileView {
    Image projectile_image = new Image(Objects.requireNonNull(getClass().
            getResourceAsStream("resources/VisualAssets/Sprites/Ship/Main ship weapon - Projectile - Auto cannon bullet.png")));

    AnimatedSprite projectile = new AnimatedSprite(projectile_image, 32, 32, 4);
}
