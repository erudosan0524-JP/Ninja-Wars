package jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.commands.subcommands;

import jp.ne.sakura.erudoblog.ninjawars.ninjawars.NinjaWars;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.commands.SubCommand;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.MessageManager;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {

    public Reload(NinjaWars plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        getPlugin().getMyConfig().reload();
        MessageManager.sendMessageAll("リロードが完了しました");
    }

    @Override
    public String name() {
        return "reload";
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
