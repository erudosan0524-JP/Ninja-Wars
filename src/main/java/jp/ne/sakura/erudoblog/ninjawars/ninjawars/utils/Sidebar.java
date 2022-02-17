package jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

public class Sidebar {

    private final String objectiveName = "sidebar";
    private Objective obj;
    private Team time;

    public void setup() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        obj = board.registerNewObjective(objectiveName, "dummy", "player");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    }
}
