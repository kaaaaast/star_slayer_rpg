package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.utils.ResourceUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//classe che serve per sfruttare i png che contengono i frame delle animazioni (es: proiettili, motore della navicella)
public class AnimatedSprite {

    private final ImageView image_view;
    private final int frame_width;
    private final int frame_height;
    private final int frame_count;
    private int currentFrame;

    public AnimatedSprite(Image spriteSheet, int frameWidth, int frameHeight, int frameCount) {
        this.frame_width = frameWidth;
        this.frame_height = frameHeight;
        this.frame_count = frameCount;
        this.currentFrame = 0;
        image_view = new ImageView(spriteSheet);
        updateViewport();
    }

    private void updateViewport() {
        image_view.setViewport(new Rectangle2D(currentFrame * frame_width, 0, frame_width, frame_height));
    }

    public void nextFrame() {
        currentFrame++;
        if (currentFrame >= frame_count) {
            currentFrame = 0;
        }
        updateViewport();
    }

    public static AnimatedSprite fromResource(Class <?> caller_class, String path, int frameWidth, int frameHeight, int frameCount) {
        return new AnimatedSprite (ResourceUtils.loadImage(caller_class, path), frameWidth, frameHeight, frameCount);
    }

    public ImageView getImageView() {
        return image_view;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
