package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.model.entities.Projectile;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.ui.views.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;

import java.util.List;

public class GameLoop extends AnimationTimer {

    private final GameManager gameManager;
    private final ShipView shipView;
    private final BackgroundView backgroundView;
    private final Group root;
    private final List<PlanetView> planetViews;
    private final List<ProjectileView> projectileViews;
    private final List<PickUpView> pickUpViews;
    private final List<StarView> starViews;

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

        Camera camera = gameManager.getCamera();

        backgroundView.update(camera);
        shipView.update(camera);
        shipView.nextFrame();
        starViews.forEach(s -> s.update(camera));

        updatePlanets(camera);
        updateProjectiles(camera);
        updatePickups(camera);
    }

    private void updatePlanets(Camera camera) {
        planetViews.forEach(p -> p.update(camera));
        planetViews.removeIf(view -> {

            if (view.getPlanet().isDestroyed()) {
                root.getChildren().remove(view.getImage_view());
                return true;
            }

            return false;

        });
    }

    private void updateProjectiles(Camera camera){
        showProjectileViews();
        projectileViews.forEach(p -> {p.update(camera); p.nextFrame();});
        removeProjectileViews();
    }

    private void updatePickups(Camera camera) {
        showPickupsViews();
        pickUpViews.forEach(p -> p.update(camera));
        removePickupsViews();
    }

    private void showProjectileViews() {
        for (Projectile<?> projectile : gameManager.getProjectiles()) {
            boolean exists = projectileViews.stream().anyMatch(v -> v.getProjectile() == projectile);
            if (!exists) {
                ProjectileView projectileView = new ProjectileView(projectile);
                projectileViews.add(projectileView);
                root.getChildren().add(projectileView.getImageView());
            }
        }
    }

    private void removeProjectileViews() {
        projectileViews.removeIf(view -> {

            if (view.getProjectile().should_remove()) {
                root.getChildren().remove(view.getImageView());
                return true;
            }
            return false;
        }
        );
    }

    private void showPickupsViews() {
        for (PickUp pickup : gameManager.getPickups()) {
            boolean exists = pickUpViews.stream().anyMatch(v -> v.getPickUp() == pickup);
            if (!exists) {
                PickUpView view = new PickUpView(pickup);
                pickUpViews.add(view);
                root.getChildren().add(view.getImage_view());
            }
        }
    }

    private void removePickupsViews() {
        pickUpViews.removeIf(view -> {
            if (view.getPickUp().should_remove()) {
                root.getChildren().remove(view.getImage_view());
                return true;
            }

            return false;
        });
    }

}
