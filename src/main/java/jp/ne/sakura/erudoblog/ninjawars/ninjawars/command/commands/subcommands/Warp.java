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
        List<Player> warpPlayers = new ArrayList<>();

        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == getPlugin().getMyConfig().getWarpBlockType()) {
                warpPlayers.add(player);
            }
        }

        Collections.shuffle(warpPlayers);
        Player warpPlayer = warpPlayers.get(0);
        NinjaPlayer ninja = new NinjaPlayer(player, PlayerStatus.GAME_PLAYER);
        NinjaWars.addNinjaPlayer(ninja);

        StringBuilder sb = new StringBuilder();
        sb.append(warpPlayer.getName());
        sb.append("が参戦しました");
        MessageManager.sendMessageAll(sb.toString());

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
