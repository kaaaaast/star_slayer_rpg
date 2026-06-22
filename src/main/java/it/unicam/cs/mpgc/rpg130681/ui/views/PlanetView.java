package it.unicam.cs.mpgc.rpg130681.ui.views;
import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.Planet;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlanetView extends GameObjectView<Planet> {

    public PlanetView(Planet planet, List<Image> planetSprites) {
        this.object = planet;

        // supporto dall'AI per questa pezzo di codice (per ogni pianeta visualizzato viene scelto casualmente
        // uno dei 36 sprite tra quelli disponibili).
        Image randomSprite = planetSprites.get(ThreadLocalRandom.current().nextInt(planetSprites.size()));
        this.image_view = new ImageView(randomSprite);
        image_view.setTranslateX(-randomSprite.getWidth() / 2);
        image_view.setTranslateY(-randomSprite.getHeight() / 2);
    }

    @Override
    public void update(Camera camera) {
        Vector2 screenPosition = camera.worldToScreen(object.getPosition());
        image_view.setLayoutX(screenPosition.x());
        image_view.setLayoutY(screenPosition.y());

    }
}