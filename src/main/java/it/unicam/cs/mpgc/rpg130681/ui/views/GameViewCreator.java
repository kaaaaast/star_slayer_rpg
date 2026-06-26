package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.model.entities.Planet;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.entities.Star;
import it.unicam.cs.mpgc.rpg130681.utils.ResourceUtils;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che gestisce la creazione delle viste degli oggetti del gioco.
 */
public class GameViewCreator {

    public BackgroundView createBackground(Group root) {
        Image backgroundImage = ResourceUtils.loadImage(getClass(), "/VisualAssets/Sprites/Background/SpaceBackground.png");

        BackgroundView backgroundView = new BackgroundView(backgroundImage);
        root.getChildren().add(backgroundView.getRoot());

        return backgroundView;
    }

    public ShipView createShip(Group root, Ship ship) {
        ShipView shipView = new ShipView(ship);
        root.getChildren().add(shipView.getRoot());
        return shipView;
    }

    public List<PlanetView> createPlanets(Group root, List<Planet> planets) {
        List<Image> planetSprites = loadPlanetSprites();
        List<PlanetView> planetViews = new ArrayList<>();

        for (Planet planet : planets) {
            PlanetView planetView = new PlanetView(planet, planetSprites);
            planetViews.add(planetView);
            root.getChildren().add(planetView.getImage_view());
        }
        return planetViews;
    }

    public List<StarView> createStars(Group root, List<Star> stars) {
        Image starImage = ResourceUtils.loadImage(getClass(), "/VisualAssets/Sprites/Stars/RedStarSprite.png");

        List<StarView> starViews = new ArrayList<>();

        for (Star star : stars) {
            StarView starView = new StarView(star, starImage);
            starViews.add(starView);
            root.getChildren().add(starView.getImage_view());
        }
        return starViews;
    }

    private List<Image> loadPlanetSprites() {
        List<Image> sprites = new ArrayList<>();

        for (int i = 1; i <= 33; i++) {
            sprites.add(ResourceUtils.loadImage(getClass(), "/VisualAssets/Sprites/Planets/" + i + ".png"));
        }
        return sprites;
    }
}
