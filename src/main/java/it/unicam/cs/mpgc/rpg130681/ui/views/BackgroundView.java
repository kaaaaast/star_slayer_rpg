package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BackgroundView {

    private static final double TILE_WIDTH = 1280;
    private static final double TILE_HEIGHT = 640;
    private static final int GRID_RADIUS = 2;
    private static final int GRID_SIZE = 5;
    private static final double PARALLAX_FACTOR = 0.2;

    private final Group root;
    private final ImageView[] tiles;

    public BackgroundView(Image backgroundImage) {

        root = new Group();
        tiles = new ImageView[GRID_SIZE * GRID_SIZE];

        for (int i = 0; i < tiles.length; i++) {
            ImageView tile = new ImageView(backgroundImage);
            tile.setPreserveRatio(false);
            tile.setSmooth(false);
            tile.setFitWidth(TILE_WIDTH);
            tile.setFitHeight(TILE_HEIGHT);
            tiles[i] = tile;
        }

        root.getChildren().addAll(tiles);
    }

    public void update(Camera camera) {

        //effetto "parallasse"
        double camX = camera.getPosition().x() * PARALLAX_FACTOR;
        double camY = camera.getPosition().y() * PARALLAX_FACTOR;

        double offsetX = camX % TILE_WIDTH;
        double offsetY = camY % TILE_HEIGHT;

        updateTiles(offsetX,offsetY);
    }

    private void updateTiles(double offsetX, double offsetY) {
        int index = 0;

        for (int row = -GRID_RADIUS; row <= GRID_RADIUS; row++) {
            for (int col = -GRID_RADIUS; col <= GRID_RADIUS; col++) {
                ImageView tile = tiles[index++];
                tile.setLayoutX((col + 1) * TILE_WIDTH - offsetX);
                tile.setLayoutY((row + 1) * TILE_HEIGHT - offsetY);
            }
        }

    }

    public Group getRoot() {
        return root;
    }
}