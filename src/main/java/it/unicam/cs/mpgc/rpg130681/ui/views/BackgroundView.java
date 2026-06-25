package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BackgroundView {

    private static final double TILE_WIDTH = 1280;
    private static final double TILE_HEIGHT = 640;

    private static final int grid_radius = 2;
    private static final int grid_size = 5;
    private static final double parallax_factor = 0.2;

    private final Group root;
    private final ImageView[] tiles;

    public BackgroundView(Image backgroundImage) {

        root = new Group();
        tiles = new ImageView[grid_size * grid_size];

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
        double camX = camera.getPosition().x() * parallax_factor;
        double camY = camera.getPosition().y() * parallax_factor;

        double offsetX = camX % TILE_WIDTH;
        double offsetY = camY % TILE_HEIGHT;

        updateTiles(offsetX,offsetY);
    }

    private void updateTiles(double offsetX, double offsetY) {
        int index = 0;

        for (int row = -grid_radius; row <= grid_radius; row++) {
            for (int col = -grid_radius; col <= grid_radius; col++) {
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