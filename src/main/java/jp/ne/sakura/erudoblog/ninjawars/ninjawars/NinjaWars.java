package jp.ne.sakura.erudoblog.ninjawars.ninjawars;

import jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NinjaWars extends JavaPlugin {

    private static NinjaWars instance;

    public CommandManager command;

    @Override
    public void onEnable() {
        setInstance(this);


        //コマンドのセットアップ
        command = new CommandManager(getInstance());
        command.setup();

    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(getInstance());
    }

    public static NinjaWars getInstance() {
        return instance;
    }

    public static void setInstance(NinjaWars instance) {
        NinjaWars.instance = instance;
    }
}
