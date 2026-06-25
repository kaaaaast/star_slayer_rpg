package it.unicam.cs.mpgc.rpg130681.gamelogic;

import it.unicam.cs.mpgc.rpg130681.model.entities.Inventory;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.pickups.DropType;
import it.unicam.cs.mpgc.rpg130681.model.stats.StatType;
import it.unicam.cs.mpgc.rpg130681.ui.views.AudioManager;

public class UpgradeManager {

    public boolean upgrade_stat(Ship ship, StatType stat) {

        Inventory inventory = ship.getInventory();

        switch (stat) {
            case SPEED:

                if (!inventory.has_resource(DropType.IRON, 20)) {
                    return false;
                }
                AudioManager.playLevelup();
                inventory.remove_resource(DropType.IRON, 20);
                ship.getShip_stats().increaseStat(stat, 1f);
                return true;

            case FIRE_RATE:

                if (!inventory.has_resource(DropType.GOLD, 10)) {
                    return false;
                }
                AudioManager.playLevelup();
                inventory.remove_resource(DropType.GOLD, 10);
                ship.getShip_stats().increaseStat(stat, 1f);
                return true;

            case MINING_POWER:

                if (!inventory.has_resource(DropType.TITANIUM, 15)) {
                    return false;
                }
                AudioManager.playLevelup();
                inventory.remove_resource(DropType.TITANIUM, 15);
                ship.getShip_stats().increaseStat(stat, 1f);
                return true;
        }
        return false;
    }

    public void checkForUpgrades(Ship ship) {
        upgrade_stat(ship, StatType.SPEED);
        upgrade_stat(ship, StatType.FIRE_RATE);
        upgrade_stat(ship, StatType.MINING_POWER);
    }

}
