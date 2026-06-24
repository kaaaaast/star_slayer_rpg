package it.unicam.cs.mpgc.rpg130681.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class SaveManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void saveGame(GameManager gameManager, long seed, String fileName) throws IOException {

        SaveData saveData = new SaveData();

        saveData.seed = seed;
        saveData.shipX = gameManager.getPlayerShip().getPosition().x();
        saveData.shipY = gameManager.getPlayerShip().getPosition().y();
        saveData.resources = new HashMap<>();
        gameManager.getPlayerShip().getShip_resources().forEach((type, stat) -> saveData.resources.put(type, stat.get_current_value()));
        saveData.inventory = new HashMap<>();

        for (DropType drop_type : DropType.values()) {
            saveData.inventory.put(drop_type, gameManager.getPlayerShip().getInventory().get_quantity(drop_type));
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            GSON.toJson(saveData, writer);
        }
    }

    public static SaveData loadGame(String fileName) throws IOException {
        try (FileReader reader = new FileReader(fileName)) {
            return GSON.fromJson(reader, SaveData.class);
        }
    }
}