package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BackgroundView {

    private static final double TILE_WIDTH = 1280;
    private static final double TILE_HEIGHT = 640;

    private final Group root;
    private final ImageView[] tiles;

    public BackgroundView(Image backgroundImage) {

        root = new Group();

        tiles = new ImageView[25];

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

        //effetto parallasse, per far muovere leggermente lo sfondo durante il movimento
        double camX = camera.getPosition().x() * 0.2;
        double camY = camera.getPosition().y() * 0.2;

        //update tile infinite background
        double offsetX = camX % TILE_WIDTH;

        double offsetY = camY % TILE_HEIGHT;

        int index = 0;

        for (int row = -2; row <= 2; row++) {
            for (int col = -2; col <= 2; col++) {
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