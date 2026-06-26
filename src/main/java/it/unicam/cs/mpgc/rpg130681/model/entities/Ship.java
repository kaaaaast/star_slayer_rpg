package it.unicam.cs.mpgc.rpg130681.model.entities;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceStat;
import it.unicam.cs.mpgc.rpg130681.model.stats.ResourceType;
import it.unicam.cs.mpgc.rpg130681.model.stats.ShipStats;
import it.unicam.cs.mpgc.rpg130681.model.stats.StatType;
import it.unicam.cs.mpgc.rpg130681.ui.views.AudioManager;
import it.unicam.cs.mpgc.rpg130681.utils.Vector2;
import java.util.Map;

/**
 * Classe che rappresenta la navicella del giocatore.
 */
public class Ship extends GameObject {

    private Map<ResourceType, ResourceStat> ship_resources;
    private ShipStats ship_stats;
    private Inventory inventory;
    private boolean invulnerable;
    private float invulnerability_time;
    private float shootCooldown;
    private float rotation;
    private static final float DEAD_ZONE = 20f;
    private static final float MAX_CURSOR_DISTANCE = 300f;
    private static final float DISTANCE_SCALE = 100f;

    /**
     * Costruisce la navicella.
     * @param position La posizione in cui spawna la navicella.
     * @param resources Le risorse della navicella, come vita e carburante.
     * @param stats Le statistiche della navicella, come la velocità e il firerate.
     * @param diameter Il diametro della nave.
     * @param shootCooldown Parametro per calcolare il firerate della navicella.
     * @param rotation La rotazione della navicella, permette di orientarla nello spazio.
     * @param inventory L'inventario della navicella.
     */
    public Ship(Vector2 position, Map<ResourceType, ResourceStat> resources, ShipStats stats, float diameter, float shootCooldown,float rotation, Inventory inventory){
        super(position, diameter);
        this.ship_resources = resources;
        this.ship_stats = stats;
        this.shootCooldown = shootCooldown;
        this.rotation = rotation;
        this.inventory = inventory;
    }

    /**
     * Muove la navicella verso una direzione, sfruttando il cursore del mouse e la statistica Speed della nave.
     * Più il cursore è lontano dalla navicella, più la velocità aumenta, con un cap a {@code MAX_CURSOR_DISTANCE}.
     * @param direction La direzione verso la quale la navicella deve muoversi.
     */
    public void move(Vector2 direction) {

        if (!hasFuel()) {
            return;
        }

        float distance = direction.length();

        //Dead-zone per non far muovere la navicella quando il mouse è molto vicino a essa.
        if (distance < DEAD_ZONE) {
            return;
        }

        consumeFuel(0.02f);

        distance = Math.min(distance, MAX_CURSOR_DISTANCE);

        float speed_multiplier = distance / DISTANCE_SCALE;
        setPosition(getPosition().add(direction.normalize().multiply(ship_stats.getStat(StatType.SPEED) * speed_multiplier)));
    }

    /**
     * Controlla che la navicella abbia del carburante.
     * @return {@code true} se la quantità di carburante rimasta è positiva.
     */
    public boolean hasFuel() {
        return !getResource(ResourceType.FUEL).isEmpty();
    }

    /**
     * Consuma il carburante quando la navicella è in movimento.
     * @param amount la quantità di carburante da consumare.
     * @throws IllegalArgumentException se l'{@code amount} passato è strettamente negativo.
     */
    public void consumeFuel(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Il carburante da consumare deve essere maggiore o uguale a 0.");
        }
        getResource(ResourceType.FUEL).decreaseResourceBy(amount);
    }


    public void update() {
        update_shoot_cooldown();
        update_invulnerability();
    }

    /**
     * Controlla se la navicella può sparare, e il suo firerate.
     * @return {@code true} se la navicella può sparare, ovvero se {@code shootCooldown} è negativo o 0.
     */
    public boolean canShoot() {
        if (shootCooldown > 0) {
            return false;
        }
        shootCooldown = 1f / ship_stats.getStat(StatType.FIRE_RATE);
        return true;
    }

    /**
     * Orienta la navicella verso un bersaglio.
     * @param targetPosition il bersaglio verso cui ruotare la navicella
     */
    public void lookAt(Vector2 targetPosition) {
        //Ho ricevo supporto dall'AI per la formula del calcolo della direzione con l'arcotangente.
        Vector2 direction = targetPosition.sub(getPosition());
        rotation = (float) Math.toDegrees(Math.atan2(direction.y(), direction.x()));
    }

    public ShipStats getShip_stats() {
        return ship_stats;
    }

    public Map<ResourceType, ResourceStat> getShip_resources() {
        return ship_resources;
    }

    /**
     * Restituisce una certa risorsa della navicella.
     * @param type Risorsa da restituire.
     * @return Una risorsa {@link ResourceType}.
     * @throws IllegalArgumentException se la risorsa non esiste.
     */
    public ResourceStat getResource(ResourceType type) {
        if (!ship_resources.containsKey(type)) {
            throw new IllegalArgumentException("La nave non possiede la risorsa " + type);
        }
        return ship_resources.get(type);
    }

    /**
     * Applica del danno alla navicella quando collide con i pianeti. Successivamente, conferisce un breve
     * periodo di invulnerabilità per evitare numerose collisioni consecutive e bilanciare il gameplay.
     */
    public void receive_collision_damage() {
        if (invulnerable) {
            return;
        }
        ResourceStat ship_current_health = getResource(ResourceType.HEALTH);
        ship_current_health.decreaseResourceBy(30);
        AudioManager.playDamage();
        invulnerable = true;
        invulnerability_time = 3.0f;
    }

    /**
     * Aggiorna lo shootCooldown.
     */
    private void update_shoot_cooldown() {
        shootCooldown = Math.max(shootCooldown-0.02f,0);
    }

    /**
     * Aggiorna il periodo di invulnerabilità della navicella, dopo aver subito danni da collisione.
     */
    private void update_invulnerability() {

        if (!invulnerable) {
            return;
        }

        invulnerability_time = Math.max(invulnerability_time - 0.02f, 0);
        if (invulnerability_time == 0) {
            invulnerable = false;
        }
    }

    /**
     * Controlla che la navicella sia distrutta.
     * @return {@code true} se la navicella non ha più vita.
     */
    public boolean isDestroyed() {
        return getResource(ResourceType.HEALTH).isEmpty();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public float getRotation() {
        return rotation;
    }
}
