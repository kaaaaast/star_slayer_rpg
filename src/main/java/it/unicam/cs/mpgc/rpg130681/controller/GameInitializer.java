package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.model.entities.Planet;
import it.unicam.cs.mpgc.rpg130681.model.entities.Star;
import it.unicam.cs.mpgc.rpg130681.ui.views.*;
import it.unicam.cs.mpgc.rpg130681.utils.ResourceUtils;
import javafx.scene.Group;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;


public class GameInitializer {

    private GameManager gameManager;
    private Group root;
    private BackgroundView backgroundView;
    private ShipView shipView;
    private List<PlanetView> planetViews;
    private List<ProjectileView> projectileViews;
    private List<PickUpView> pickUpViews;
    private List<StarView> starViews;

    public Group createGame() {
        long seed = System.currentTimeMillis();
        WorldGenerator worldGenerator = new WorldGenerator(seed);
        gameManager = worldGenerator.generateWorld();
        root = new Group();
        backgroundView = createBackground(root);
        shipView = createShip(root, gameManager);
        planetViews = createPlanets(root, gameManager);
        starViews = createStars(root, gameManager);
        projectileViews = new ArrayList<>();
        pickUpViews = new ArrayList<>();
        return root;
    }

    public void startGame() {
        backgroundView.update(gameManager.getCamera());
        GameLoop gameLoop = new GameLoop(gameManager, backgroundView, root, shipView, planetViews, projectileViews, pickUpViews, starViews);
        AudioManager.startMusic();
        gameLoop.start();
    }


    private BackgroundView createBackground(Group root) {
        Image backgroundImage = ResourceUtils.loadImage(getClass(), "/VisualAssets/Sprites/Background/SpaceBackground.png");
        BackgroundView backgroundView = new BackgroundView(backgroundImage);
        root.getChildren().add(backgroundView.getRoot());
        return backgroundView;
    }

    private ShipView createShip(Group root, GameManager gameManager) {
        ShipView shipView = new ShipView(gameManager.getPlayerShip());
        root.getChildren().add(shipView.getRoot());
        return shipView;
    }

    private List<PlanetView> createPlanets(Group root, GameManager gameManager) {
        List<Image> planetSprites = loadPlanetSprites();
        List<PlanetView> planetViews = new ArrayList<>();

        for (Planet planet : gameManager.getPlanets()) {
            PlanetView planetView = new PlanetView(planet, planetSprites);
            planetViews.add(planetView);
            root.getChildren().add(planetView.getImage_view());
        }

        return planetViews;
    }

    private List<Image> loadPlanetSprites() {
        List<Image> sprites = new ArrayList<>();
        for (int i = 1; i <= 33; i++) {
            sprites.add(ResourceUtils.loadImage(getClass(),"/VisualAssets/Sprites/Planets/" + i + ".png"));
        }
        return sprites;
    }

    private List<StarView> createStars(Group root, GameManager gameManager) {
        Image starImage = ResourceUtils.loadImage(getClass(),"/VisualAssets/Sprites/Stars/RedStarSprite.png");
        List<StarView> starViews = new ArrayList<>();

        for (Star star : gameManager.getStars()) {
            StarView starView = new StarView(star, starImage);
            starViews.add(starView);
            root.getChildren().add(starView.getImage_view());
        }
        return starViews;
    }
}
