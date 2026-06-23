package it.unicam.cs.mpgc.rpg130681.controller;

import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.ui.views.PickUpView;
import it.unicam.cs.mpgc.rpg130681.ui.views.PlanetView;
import it.unicam.cs.mpgc.rpg130681.ui.views.ProjectileView;
import it.unicam.cs.mpgc.rpg130681.ui.views.ShipView;
import javafx.animation.AnimationTimer;

import java.util.List;

public class GameLoop extends AnimationTimer {

    private GameManager gameManager;
    private ShipView shipView;
    private List<PlanetView> planetViews;
    private List<ProjectileView> projectileViews;
    private List<PickUpView> pickUpViews;

    public GameLoop(GameManager gameManager, ShipView shipView, List<PlanetView> planetViews, List<ProjectileView> projectileViews, List<PickUpView> pickUpViews) {
        this.gameManager = gameManager;
        this.shipView = shipView;
        this.planetViews = planetViews;
        this.projectileViews = projectileViews;
        this.pickUpViews = pickUpViews;
    }

    @Override
    public void handle(long now) {
        gameManager.update();
        shipView.update(gameManager.getCamera());
        planetViews.forEach(p -> p.update(gameManager.getCamera()));
        projectileViews.forEach(p -> p.update(gameManager.getCamera()));
        pickUpViews.forEach(p -> p.update(gameManager.getCamera()));
    }
}
