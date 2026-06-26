package it.unicam.cs.mpgc.rpg130681.ui.views;

import it.unicam.cs.mpgc.rpg130681.gamelogic.Camera;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Classe per la visualizzazione dello sfondo. Esso è formato da una tile loopabile infinite volte.
 */
public class BackgroundView {

    private static final double TILE_WIDTH = 1280;
    private static final double TILE_HEIGHT = 640;

    private static final int grid_radius = 2;
    private static final int grid_size = 5;
    private static final double parallax_factor = 0.2;

    private final Group root;
    private final ImageView[] tiles;

    /**
     * Costruisce la vista dello sfondo.
     * @param backgroundImage  immagine utilizzata come tile.
     */
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

    /**
     * Aggiorna lo sfondo, permettendo di essere sempre visibile correttamente in tutto il mondo.
     * @param camera camera del gioco.
     */
    public void update(Camera camera) {

        // effetto "parallasse" che genera un lieve effetto di profondità
        double camX = camera.getPosition().x() * parallax_factor;
        double camY = camera.getPosition().y() * parallax_factor;

        double offsetX = camX % TILE_WIDTH;
        double offsetY = camY % TILE_HEIGHT;

        updateTiles(offsetX,offsetY);
    }

    /**
     * Aggiorna la posizione delle singole tile dello sfondo a partire
     * dagli offset calcolati rispetto alla camera.
     *
     * @param offsetX l'offset orizzontale.
     * @param offsetY l'offset verticale.
     */
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

    /**
     * Restituisce il nodo radice contenente le tile dello sfondo.
     *
     * @return il {@link Group} che rappresenta lo sfondo.
     */
    public Group getRoot() {
        return root;
    }
}