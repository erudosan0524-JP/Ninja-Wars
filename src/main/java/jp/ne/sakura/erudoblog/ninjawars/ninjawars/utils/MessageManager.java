package jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class MessageManager {

    public static void sendMessageAll(String message) {
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }


}
