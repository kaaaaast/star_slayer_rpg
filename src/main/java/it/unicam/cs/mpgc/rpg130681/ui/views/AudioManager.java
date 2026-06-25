package it.unicam.cs.mpgc.rpg130681.ui.views;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public final class AudioManager {

    private static MediaPlayer musicPlayer;

    private static final AudioClip SHOOT = new AudioClip(Objects.requireNonNull(AudioManager.class.getResource("/AudioAssets/laser_sfx.mp3")).toExternalForm());
    private static final AudioClip EXPLOSION = new AudioClip(Objects.requireNonNull(AudioManager.class.getResource("/AudioAssets/explosion_sfx.mp3")).toExternalForm());
    private static final AudioClip PICKUP = new AudioClip(Objects.requireNonNull(AudioManager.class.getResource("/AudioAssets/pickup_sfx.mp3")).toExternalForm());
    private static final AudioClip ENGINE = new AudioClip(Objects.requireNonNull(AudioManager.class.getResource("/AudioAssets/spaceship_sfx.mp3")).toExternalForm());
    private static final AudioClip LEVELUP = new AudioClip(Objects.requireNonNull(AudioManager.class.getResource("/AudioAssets/levelup_sfx.mp3")).toExternalForm());

    private AudioManager() {}

    public static void startMusic() {
        Media media = new Media(Objects.requireNonNull(AudioManager.class.getResource("/AudioAssets/background_music.mp3")).toExternalForm());
        musicPlayer = new MediaPlayer(media);
        musicPlayer.setVolume(0.25);
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.play();
    }

    public static void playShoot() {
        SHOOT.play();
    }

    public static void playExplosion() {
        EXPLOSION.play();
    }

    public static void playPickup() {
        PICKUP.play();
    }

    public static void playLevelup() {
        LEVELUP.play();
    }
    public static void startEngineLoop() {
        ENGINE.setCycleCount(AudioClip.INDEFINITE);
        ENGINE.play();
    }

    static {
        SHOOT.setVolume(0.4);
        EXPLOSION.setVolume(0.8);
        PICKUP.setVolume(0.7);
        ENGINE.setVolume(0.3);
        LEVELUP.setVolume(0.4);
    }

    public static void stopEngineLoop() {
        ENGINE.stop();
    }
}