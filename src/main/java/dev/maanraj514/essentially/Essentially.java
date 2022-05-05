package dev.maanraj514.essentially;

import dev.maanraj514.essentially.commands.*;
import dev.maanraj514.essentially.listeners.EntityDamageListener;
import dev.maanraj514.essentially.listeners.PlayerJoinListener;
import dev.maanraj514.essentially.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Essentially extends JavaPlugin {

    List<UUID> godModePlayers;

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.options().copyDefaults();
        saveDefaultConfig();

        godModePlayers = new ArrayList<>();

        registerCommands();
        registerListeners();

        Bukkit.getConsoleSender().sendMessage(Color.colorize("&aPlugin has enabled: Essentially -- Maanraj514"));
    }

    @Override
    public void onDisable() {
        godModePlayers.clear();

        Bukkit.getConsoleSender().sendMessage(Color.colorize("&cPlugin has enabled: Essentially -- Maanraj514"));
    }

    public void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand());

        getCommand("ping").setExecutor(new PingCommand());

        getCommand("tpworld").setExecutor(new TpWorldCommand());
        getCommand("tpworld").setTabCompleter(new TpWorldCommand());

        getCommand("bring").setExecutor(new BringCommand());

        getCommand("godmode").setExecutor(new GodModeCommand(this));

        getCommand("heal").setExecutor(new HealCommand());

        getCommand("repair").setExecutor(new RepairCommand());
    }

    public List<UUID> getGodModePlayers() {
        return godModePlayers;
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new EntityDamageListener(this), this);
    }
}