package jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class Config {
    private Plugin plugin;
    private FileConfiguration config;

    @Getter
    private int countdownTime, gameTime;

    public Config(Plugin plugin) {
        this.plugin = plugin;

        load();
    }

    private void load() {
        plugin.saveDefaultConfig();

        if (Objects.nonNull(config)) {
            reload();
        }

        config = plugin.getConfig();

        countdownTime = config.getInt("countdown-time");
        gameTime = config.getInt("game-time");

    }

    public void reload() {
        plugin.reloadConfig();
        plugin.saveConfig();
    }
}
