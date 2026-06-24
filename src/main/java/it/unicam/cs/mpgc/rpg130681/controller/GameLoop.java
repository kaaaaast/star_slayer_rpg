package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.model.entities.Projectile;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.ui.views.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;

import java.util.List;

public class GameLoop extends AnimationTimer {

    private GameManager gameManager;
    private ShipView shipView;
    private BackgroundView backgroundView;
    private Group root;
    private List<PlanetView> planetViews;
    private List<ProjectileView> projectileViews;
    private List<PickUpView> pickUpViews;
    private List<StarView> starViews;

    public GameLoop(GameManager gameManager, BackgroundView backgroundView, Group root, ShipView shipView, List<PlanetView> planetViews, List<ProjectileView> projectileViews, List<PickUpView> pickUpViews, List<StarView> starViews) {
        this.gameManager = gameManager;
        this.backgroundView = backgroundView;
        this.root = root;
        this.shipView = shipView;
        this.planetViews = planetViews;
        this.projectileViews = projectileViews;
        this.pickUpViews = pickUpViews;
        this.starViews = starViews;
    }

    @Override
    public void handle(long now) {
        gameManager.update();
        backgroundView.update(gameManager.getCamera());
        shipView.update(gameManager.getCamera());
        planetViews.forEach(p -> p.update(gameManager.getCamera()));
        planetViews.removeIf(view -> {

            if (view.getPlanet().isDestroyed()) {
                AudioManager.playExplosion();
                root.getChildren().remove(view.getImage_view());
                return true;
            }

            return false;

        });
        for (Projectile<?> projectile : gameManager.getProjectiles()) {

            boolean exists = projectileViews.stream().anyMatch(v -> v.getProjectile() == projectile);

            if (!exists) {
                ProjectileView projectileView = new ProjectileView(projectile);
                projectileViews.add(projectileView);
                root.getChildren().add(projectileView.getImageView());
            }
        }

        projectileViews.forEach(p -> {p.update(gameManager.getCamera());p.nextFrame();});
        projectileViews.removeIf(view -> {

            if (view.getProjectile().should_remove()) {
                root.getChildren().remove(view.getImageView());
                return true;
            }

            return false;
        });

        pickUpViews.forEach(p -> p.update(gameManager.getCamera()));

        for (PickUp pickup : gameManager.getPickups()) {
            boolean exists = pickUpViews.stream().anyMatch(v -> v.getPickUp() == pickup);
            if (!exists) {
                PickUpView view = new PickUpView(pickup);
                pickUpViews.add(view);
                root.getChildren().add(view.getImage_view());
            }
        }

        pickUpViews.removeIf(view -> {

            if (view.getPickUp().should_remove()) {
                root.getChildren().remove(view.getImage_view());
                return true;
            }

            return false;
        });

        starViews.forEach(s -> s.update(gameManager.getCamera()));
    }
}
