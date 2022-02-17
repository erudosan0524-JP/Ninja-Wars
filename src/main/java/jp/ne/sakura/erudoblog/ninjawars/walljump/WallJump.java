package jp.ne.sakura.erudoblog.ninjawars.walljump;


import org.bukkit.plugin.java.JavaPlugin;

public class WallJump {

    public WallJump(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new WallJumpListener(), plugin);
    }


}
