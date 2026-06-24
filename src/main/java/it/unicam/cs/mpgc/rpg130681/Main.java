package it.unicam.cs.mpgc.rpg130681;

import it.unicam.cs.mpgc.rpg130681.controller.GameLoop;
import it.unicam.cs.mpgc.rpg130681.controller.InputManager;
import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.controller.WorldGenerator;
import it.unicam.cs.mpgc.rpg130681.model.entities.Planet;
import it.unicam.cs.mpgc.rpg130681.model.entities.Star;
import it.unicam.cs.mpgc.rpg130681.ui.views.*;

import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        long seed = System.currentTimeMillis();

        WorldGenerator worldGenerator =
                new WorldGenerator(seed);

        GameManager gameManager =
                worldGenerator.generateWorld();
        
        Group root = new Group();


        //background
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/VisualAssets/Sprites/Background/SpaceBackground.png")));
        BackgroundView backgroundView = new BackgroundView(backgroundImage);
        root.getChildren().add(backgroundView.getRoot());
        
        
        //ship
        ShipView shipView = new ShipView(gameManager.getPlayerShip());
        root.getChildren().add(shipView.getRoot());

        
        //pianeti
        List<Image> planetSprites = loadPlanetSprites();

        List<PlanetView> planetViews = new ArrayList<>();

        for (Planet planet : gameManager.getPlanets()) {
            PlanetView planetView = new PlanetView(planet, planetSprites);
            planetViews.add(planetView);
            root.getChildren().add(planetView.getImage_view());
        }

        //stars
        Image starImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/VisualAssets/Sprites/Stars/RedStarSprite.png")));
        List<StarView> starViews = new ArrayList<>();

        for (Star star : gameManager.getStars()) {
            StarView starView = new StarView(star, starImage);
            starViews.add(starView);
            root.getChildren().add(starView.getImage_view());
        }

        
        //projectiles
        List<ProjectileView> projectileViews =
                new ArrayList<>();

        //pickups
        List<PickUpView> pickUpViews = new ArrayList<>();

        Scene scene = getScene(root);

        stage.setScene(scene);
        stage.setTitle("Star Slayer RPG");
        backgroundView.update(gameManager.getCamera());
        stage.show();

        GameLoop gameLoop = new GameLoop(gameManager, backgroundView, root, shipView, planetViews, projectileViews, pickUpViews, starViews);

        AudioManager.startMusic();
        gameLoop.start();
    }

    private static Scene getScene(Group root) {
        Scene scene = new Scene(root, 1920, 1080);
        scene.setOnMouseMoved(e -> InputManager.setMousePos(new Vector2((float) e.getX(), (float) e.getY())));
        scene.setOnKeyPressed(e -> InputManager.keyPressed(e.getCode()));
        scene.setOnKeyReleased(e -> InputManager.keyReleased(e.getCode()));
        return scene;
    }

    private List<Image> loadPlanetSprites() {

        List<Image> sprites = new ArrayList<>();

        for (int i = 1; i <= 33; i++) {
            sprites.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/VisualAssets/Sprites/Planets/" + i + ".png"))));
        }

        return sprites;
    }

    public static void main(String[] args) {
        launch(args);
    }
}