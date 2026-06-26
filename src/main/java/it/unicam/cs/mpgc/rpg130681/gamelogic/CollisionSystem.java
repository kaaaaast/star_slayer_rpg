package it.unicam.cs.mpgc.rpg130681.gamelogic;

import it.unicam.cs.mpgc.rpg130681.model.entities.GameObject;
import it.unicam.cs.mpgc.rpg130681.model.entities.Projectile;
import it.unicam.cs.mpgc.rpg130681.model.entities.Ship;
import it.unicam.cs.mpgc.rpg130681.model.pickups.PickUp;
import it.unicam.cs.mpgc.rpg130681.ui.views.AudioManager;
import it.unicam.cs.mpgc.rpg130681.utils.Destroyable;
import it.unicam.cs.mpgc.rpg130681.utils.GameUtils;

/**
 * Classe che gestisce le collisioni fra oggetti del mondo.
 */

public class CollisionSystem {

    /**
     * Controlla se un proiettile è in collisione con un oggetto distruttibile. In caso positivo, applica il danno opportuno.
     *
     * @param projectile il proiettile da controllare.
     * @return true se l'oggetto {@link GameObject} e {@link Destroyable} è in collisione col {@code projectile}.
     * @param <T> il tipo del bersaglio.
     */
    public <T extends GameObject & Destroyable> boolean checkProjectileCollision(Projectile <T> projectile) {
        if (GameUtils.isColliding(projectile, projectile.getTarget())) {
            T target = projectile.getTarget();
            target.receive_damage(projectile.getDamage());
            projectile.setShouldRemove(true);
            return true;
        }
        return false;
    }

    /**
     * Controlla se la navicella del giocatore è in collisione con un {@link PickUp}.
     * @param ship la navicella del giocatore.
     * @param pickup l'oggetto da raccogliere.
     * @return {@code true} se la navicella è in collisione con l'oggetto (e quindi raccoglie l'oggetto con opportuni effetti relativi).
     */
    public boolean checkPickupCollision(Ship ship, PickUp pickup) {
        if (GameUtils.isColliding(ship, pickup)) {
            AudioManager.playPickup();
            pickup.on_pickup(ship);
            pickup.setShouldRemove(true);
            return true;
        }
        return false;
    }

    /**
     * Controlla se la navicella del giocatore è in collisione con un oggetto {@link GameObject} e {@link Destroyable}.
     * In caso positivo, applica il danno da collisione e relativa invulnerabilità temporanea.
     * @param ship la navicella del giocatore.
     * @param obj l'oggetto in eventuale collisione.
     * @param <T> il tipo dell'oggetto da verificare.
     */
    public <T extends GameObject & Destroyable> void checkPlayerCollision(Ship ship, T obj) {
        if (!GameUtils.isColliding(ship, obj)) {
            return;
        }
        ship.receive_collision_damage();
    }

}
