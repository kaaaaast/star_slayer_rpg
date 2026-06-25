package it.unicam.cs.mpgc.rpg130681.utils;

import javafx.scene.image.Image;
import java.util.Objects;

public final class ResourceUtils {

    private ResourceUtils() {}

    public static Image loadImage(Class<?> sourceClass, String path) {
        return new Image(Objects.requireNonNull(sourceClass.getResourceAsStream(path)));
    }
}
