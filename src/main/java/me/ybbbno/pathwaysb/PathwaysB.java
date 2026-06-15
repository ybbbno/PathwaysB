package me.ybbbno.pathwaysb;

import me.deadybbb.ybmj.PluginProvider;
import me.ybbbno.pathwaysb.pathways.PlayersManager;
import org.bukkit.Bukkit;

public final class PathwaysB extends PluginProvider {
    private PlayersManager manager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        manager = new PlayersManager(this);
        manager.init();

        registerCommand("pathwaysb", new PathwaysBCommand(this));
    }

    @Override
    public void onDisable() {
        manager.deinit();
    }

    public void reload() {
        Bukkit.getScheduler().runTask(this, this::onDisable);
        Bukkit.getScheduler().runTask(this, this::onEnable);
    }

    public PlayersManager getManager() { return manager; }
}
