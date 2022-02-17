package jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.commands.subcommands;

import jp.ne.sakura.erudoblog.ninjawars.ninjawars.NinjaWars;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.commands.SubCommand;
import org.bukkit.entity.Player;

public class Help extends SubCommand {

    public Help(NinjaWars plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        player.sendMessage("===================",
                "HELP",
                "===================");
    }

    @Override
    public String name() {
        return "help";
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
