package jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.commands.subcommands;

import jp.ne.sakura.erudoblog.ninjawars.ninjawars.NinjaWars;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.commands.SubCommand;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.MessageManager;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.NinjaPlayer;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Warp extends SubCommand {
    public Warp(NinjaWars plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            System.out.println(p.getName());
            if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == getPlugin().getMyConfig().getWarpBlockType()) {
                NinjaPlayer ninja = new NinjaPlayer(p, PlayerStatus.GAME_PLAYER);
                NinjaWars.addNinjaPlayer(ninja);
                p.sendMessage("ゲームに参戦しました");
            }
        }
    }

    @Override
    public String name() {
        return "wp";
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
