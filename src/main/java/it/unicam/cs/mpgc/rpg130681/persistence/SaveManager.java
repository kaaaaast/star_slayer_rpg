package it.unicam.cs.mpgc.rpg130681.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg130681.gamelogic.GameManager;
import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import it.unicam.cs.mpgc.rpg130681.model.stats.StatType;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Classe per la persistenza che gestisce i salvataggi. Si occupa di salvare una partita memorizzando i dati
 * e di caricare una partita a partire dai dati salvati.
 */
public class SaveManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Salva lo stato corrente della partita in un file JSON
     * @param gameManager il gestore della partita.
     * @param seed il seed utile per la generazione del mondo.
     * @param fileName il nome del file di destinazione del salvataggio.
     * @throws IOException se si verificano errori in scrittura.
     */
    public static  void saveGame(GameManager gameManager, long seed, String fileName) throws IOException {

        SaveData saveData = new SaveData();
        saveData.seed = seed;
        saveData.shipX = gameManager.getPlayerShip().getPosition().x();
        saveData.shipY = gameManager.getPlayerShip().getPosition().y();
        saveData.resources = new HashMap<>();
        saveData.stats = new HashMap<>();
        gameManager.getPlayerShip().getShip_resources().forEach((type, stat) -> saveData.resources.put(type, stat.getCurrentValue()));
        saveData.inventory = new HashMap<>();

        for (DropType drop_type : DropType.values()) {
            saveData.inventory.put(drop_type, gameManager.getPlayerShip().getInventory().get_quantity(drop_type));
        }

        for (StatType type : StatType.values()) {
            saveData.stats.put(type, gameManager.getPlayerShip().getShip_stats().getStat(type));
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            GSON.toJson(saveData, writer);
        }
    }

    /**
     * Carica una partita a partire da un salvataggio
     * @param fileName il nome del file del salvataggio
     * @return un SaveData contenente i dati della partita caricata.
     * @throws IOException se si verificano errori in lettura.
     */
    public static SaveData loadGame(String fileName) throws IOException {
        try (FileReader reader = new FileReader(fileName)) {
            return GSON.fromJson(reader, SaveData.class);
        }
    }
}