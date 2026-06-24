package it.unicam.cs.mpgc.rpg130681.gamelogic;

import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.entities.Projectile;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.ui.views.AudioManager;
import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.GameUtils;


public class CollisionSystem {

    public <T extends GameObject & Destroyable> boolean check_projectile_collision(Projectile <T> projectile) {
        if (GameUtils.isColliding(projectile, projectile.getTarget())) {
            T target = projectile.getTarget();
            target.receive_damage(projectile.getDamage());
            projectile.setShould_remove(true);
            return true;
        }
        return false;
    }

    public boolean check_pickup_collision(Ship ship, PickUp pickup) {
        if (GameUtils.isColliding(ship, pickup)) {
            AudioManager.playPickup();
            pickup.on_pickup(ship);
            pickup.setShould_remove(true);
            return true;
        }
        return false;
    }

    public <T extends GameObject & Destroyable> void check_player_collision(Ship ship, T obj) {
        if (!GameUtils.isColliding(ship, obj)) {
            return;
        }
        ship.receive_collision_damage();
    }

}
