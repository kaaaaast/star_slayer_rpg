package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe generica per la visualizzazione degli oggetti.
 * @param <T> il tipo dell'oggetto da visualizzare.
 */
public abstract class GameObjectView <T extends GameObject> {

    protected T object;
    protected ImageView image_view;

    /**
     * Costruisce la visualizzazione di un generico oggetto.
     * @param object l'oggetto da visualizzare.
     * @param image la sua immagine / sprite.
     */
    public GameObjectView(T object, Image image) {
        this.object = object;
        this.image_view = new ImageView(image);
        image_view.setPreserveRatio(true);
        image_view.setSmooth(false);
        image_view.setFitWidth(object.getDiameter());
        image_view.setTranslateX(-object.getDiameter() / 2);
        image_view.setTranslateY(-object.getDiameter() / 2);
    }

    public abstract void update(Camera camera);

    public ImageView getImage_view() {
        return image_view;
    }
}
