package it.unicam.cs.mpgc.rpg130681.utils;

import javafx.scene.image.Image;
import java.util.Objects;

/**
 * Classe per restituire delle risorse come immagini o sprite animati.
 */
public final class ResourceUtils {

    private ResourceUtils() {}

    /**
     * Restituisce un {@link Image}
     * @param sourceClass la classe sorgente.
     * @param path il path corrente dell'immagine.
     * @return l'immagine {@link Image}.
     */
    public static Image loadImage(Class<?> sourceClass, String path) {
        return new Image(Objects.requireNonNull(sourceClass.getResourceAsStream(path)));
    }
}
