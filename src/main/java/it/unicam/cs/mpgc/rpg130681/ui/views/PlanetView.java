package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.model.entities.Planet;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.scene.image.Image;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlanetView extends GameObjectView<Planet> {

    public PlanetView(Planet planet, List<Image> planetSprites) {
        //quando viene costruita la sua vista, un pianeta prende casualmente uno dei 36 sprite disponibili.
        super(planet, planetSprites.get(ThreadLocalRandom.current().nextInt(planetSprites.size())));
        Image randomSprite = image_view.getImage();
        //centra lo sprite
        image_view.setTranslateX(-randomSprite.getWidth() / 2);
        image_view.setTranslateY(-randomSprite.getHeight() / 2);
    }

    @Override
    public void update(Camera camera) {
        Vector2 screenPosition = camera.worldToScreen(object.getPosition());
        image_view.setLayoutX(screenPosition.x());
        image_view.setLayoutY(screenPosition.y());

    }

    public Planet getPlanet() {
        return object;
    }
}