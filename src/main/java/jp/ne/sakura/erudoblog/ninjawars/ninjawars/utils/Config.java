package jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class Config {
    private Plugin plugin;
    private FileConfiguration config;

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

        config.getString()

    }

    public void reload() {
        plugin.reloadConfig();
        plugin.saveConfig();
    }
}
