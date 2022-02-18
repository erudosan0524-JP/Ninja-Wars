package jp.ne.sakura.erudoblog.ninjawars.ninjawars;

import jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.CommandManager;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.listener.JoinLeaveListener;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.listener.NinjaAttackListener;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.runnable.CountDown;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.runnable.GameTask;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.Config;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.GameState;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.NinjaPlayer;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.listener.NinjaMoveListener;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils.PlayerStatus;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public final class NinjaWars extends JavaPlugin {

    private static NinjaWars instance;

    public static List<NinjaPlayer> players = new ArrayList<>();

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
        new NinjaMoveListener(getInstance());
        new JoinLeaveListener(getInstance());
        new NinjaAttackListener(getInstance());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(getInstance());
        players.removeAll(players);
    }

    public void gameStart(int countdownTime, int gameTime) {
        gameState = GameState.COUNTDOWN;
        for (NinjaPlayer ninja : players) {
            if (ninja.getStatus() != PlayerStatus.GAME_PLAYER) {
                ninja.setStatus(PlayerStatus.SPECTATOR);
                ninja.getPlayer().setGameMode(GameMode.SPECTATOR);
            } else {
                Location loc = getMyConfig().getTPLocation().clone();
                loc.setWorld(ninja.getPlayer().getWorld());
                ninja.getPlayer().teleport(loc);
                ninja.getPlayer().getInventory().addItem(new ItemStack(Material.ARROW));
            }
        }

        BukkitTask countDown = new CountDown(countdownTime).runTaskTimer(this, 0L, 20L);
        BukkitTask gameTask = new GameTask(gameTime).runTaskTimer(this, 0L, 20L);
    }

    public static void addNinjaPlayer(NinjaPlayer ninja) {
        boolean flag = false;

        for(NinjaPlayer np : players) {
            if(np.getPlayer().getUniqueId().toString().equals(ninja.getPlayer().getUniqueId().toString())) {
                int index = players.indexOf(np);
                NinjaPlayer np2 = players.get(index);
                np2.setHp(ninja.getHp());
                np2.setJumping(ninja.isJumping());
                np2.setClimbing(ninja.isClimbing());
                np2.setStatus(ninja.getStatus());
                flag = true;
            }
        }

        if(!flag) {
            players.add(ninja);
        }
    }

    public static void addNinjaPlayer(Player player) {
        players.add(new NinjaPlayer(player));
    }

    public static NinjaPlayer getNinjaPlayer(Player player) {
        for (NinjaPlayer np : players) {
            if (np.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                return np;
            }
        }
        return null;
    }


}
