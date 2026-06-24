package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.Projectile;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ProjectileView {

    private final Projectile<?> projectile;
    private final AnimatedSprite sprite;

    public ProjectileView(Projectile<?> projectile) {

        this.projectile = projectile;

        Image projectileImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/VisualAssets/Sprites/Ship/Main ship weapon - Projectile - Auto cannon bullet.png")));
        sprite = new AnimatedSprite(projectileImage, 32, 32, 4);
        //centra lo sprite, altrimenti sarebbe in alto a sinistra
        sprite.getImageView().setTranslateX(-16);
        sprite.getImageView().setTranslateY(-16);
        sprite.getImageView().setFitWidth(projectile.getDiameter());

        sprite.getImageView()
                .setPreserveRatio(true);
    }

    public void update(Camera camera) {
        Vector2 screenPosition = camera.worldToScreen(projectile.getPosition());
        ImageView image_view = sprite.getImageView();
        image_view.setLayoutX(screenPosition.x());
        image_view.setLayoutY(screenPosition.y());
    }

    public Projectile<?> getProjectile() {
        return projectile;
    }

    public void nextFrame() {
        sprite.nextFrame();
    }

    public ImageView getImageView() {
        return sprite.getImageView();
    }
}
