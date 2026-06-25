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
    private int animationTick;
    private int frameDelay;
    private int currentFrame;

    public AnimatedSprite(Image spriteSheet, int frameWidth, int frameHeight, int frameCount, int frameDelay) {
        this.frame_width = frameWidth;
        this.frame_height = frameHeight;
        this.frame_count = frameCount;
        this.currentFrame = 0;
        this.frameDelay = frameDelay;
        image_view = new ImageView(spriteSheet);
        updateViewport();
    }

    private void updateViewport() {
        image_view.setViewport(new Rectangle2D(currentFrame * frame_width, 0, frame_width, frame_height));
    }


    public void nextFrame() {

        animationTick++;

        if (animationTick < frameDelay) {
            return;
        }

        animationTick = 0;
        currentFrame = (currentFrame + 1) % frame_count;
        updateViewport();
    }

    public static AnimatedSprite fromResource(Class <?> caller_class, String path, int frameWidth, int frameHeight, int frameCount, int frameDelay) {
        return new AnimatedSprite (ResourceUtils.loadImage(caller_class, path), frameWidth, frameHeight, frameCount, frameDelay);
    }

    public ImageView getImageView() {
        return image_view;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
