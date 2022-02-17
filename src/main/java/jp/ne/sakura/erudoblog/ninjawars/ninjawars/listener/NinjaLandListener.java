package jp.ne.sakura.erudoblog.ninjawars.ninjawars.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NinjaLandListener implements Listener {

    public NinjaLandListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDamageEntity(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) e.getEntity();

        if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if(player.isSneaking()) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5F, 1);
                e.setCancelled(true);
            }
        }
    }


}
