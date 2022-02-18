package jp.ne.sakura.erudoblog.ninjawars.ninjawars.listener;

import jp.ne.sakura.erudoblog.ninjawars.ninjawars.NinjaWars;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.GameState;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.NinjaPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class NinjaAttackListener implements Listener {

    private NinjaWars plugin;

    public NinjaAttackListener(NinjaWars plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if(NinjaWars.getInstance().getGameState() != GameState.INGAME) {
            return;
        }

        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if(player.getInventory().getItemInMainHand().getType() == Material.ARROW) {
                Vector vec = player.getEyeLocation().getDirection().normalize().multiply(1.2);
                player.launchProjectile(Arrow.class, vec);
            }
        }
    }

    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Arrow)) {
           return;
        }

        if(!(e.getEntity() instanceof Player)) {
            return;
        }

        if(NinjaWars.getInstance().getGameState() != GameState.INGAME) {
            return;
        }

        Player player =  (Player) e.getEntity();

        if(NinjaWars.getNinjaPlayer(player) != null) {
            e.setCancelled(true);
            NinjaPlayer ninja = NinjaWars.getNinjaPlayer(player);
            ninja.decHP();
        }
    }
}
