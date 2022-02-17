package jp.ne.sakura.erudoblog.ninjawars.ninjawars;

import jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.CommandManager;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.listener.NinjaLandListener;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.runnable.CountDown;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.runnable.GameTask;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.Config;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.GameState;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.NinjaPlayer;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.listener.WallJumpListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public final class NinjaWars extends JavaPlugin {

    private static NinjaWars instance;
    public List<NinjaPlayer> players = new ArrayList<>();

    @Getter
    private CommandManager command;
    @Getter
    private Config myConfig;

    @Getter
    @Setter
    private GameState gameState;

    public static NinjaWars getInstance() {
        return instance;
    }

    public static void setInstance(NinjaWars instance) {
        NinjaWars.instance = instance;
    }

    @Override
    public void onEnable() {
        setInstance(this);

        gameState = GameState.NONE;

        //コマンドのセットアップ
        command = new CommandManager(getInstance());
        command.setup();

        //コンフィグのセットアップ
        myConfig = new Config(getInstance());

        //リスナーのセットアップ
        new WallJumpListener(getInstance());
        new NinjaLandListener(getInstance());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(getInstance());
    }

    public void gameStart(int countdownTime, int gameTime) {
        gameState = GameState.COUNTDOWN;

        BukkitTask countDown = new CountDown(countdownTime).runTaskTimer(this, 0L, 20L);
        BukkitTask gameTask = new GameTask(gameTime).runTaskTimer(this,0L,20L);

    }


}
