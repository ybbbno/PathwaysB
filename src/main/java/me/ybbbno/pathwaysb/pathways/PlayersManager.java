package me.ybbbno.pathwaysb.pathways;

import me.deadybbb.ybmj.BasicManagerHandler;
import me.deadybbb.ybmj.PluginProvider;
import me.ybbbno.pathwaysb.pathways.config.PathwaysConfig;
import me.ybbbno.pathwaysb.pathways.config.PathwaysConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PlayersManager extends BasicManagerHandler {
    private final Random random = new Random();
    private final PathwaysConfigManager configManager;
    private final HashMap<UUID, Location> locations = new HashMap<>();

    private BukkitTask checkTask;
    private PathwaysConfig config;

    public PlayersManager(PluginProvider plugin) {
        super(plugin);

        configManager = new PathwaysConfigManager(plugin);
    }

    @Override
    protected void onInit() {
        locations.clear();
        config = configManager.getConfig();

        checkTask = Bukkit.getScheduler().runTaskTimer(plugin, this::checkPlayers, 0L, config.timeUpdate());
    }

    @Override
    protected void onDeinit() {
        config = null;

        if (checkTask != null && !checkTask.isCancelled()) {
            checkTask.cancel();
            checkTask = null;
        }
    }

    private void checkPlayers() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            UUID uuid = player.getUniqueId();

            Location playerLocation = player.getLocation().subtract(0, 1, 0);
            Location oldLocation = locations.getOrDefault(uuid, null);
            if (oldLocation == null || playerLocation.getWorld() != oldLocation.getWorld()) {
                locations.put(uuid, playerLocation);
                oldLocation = playerLocation;
            }

            if (oldLocation.distanceSquared(playerLocation) < config.distance()) return;

            Block block = playerLocation.getBlock();

            List<Material> materials = config.materials().getToMaterials(block.getType());
            Material next = materials.get(random.nextInt(0, materials.size()));

            if (next == null || random.nextInt(100) > config.chance()) return;

            block.setType(next);
            locations.put(uuid, playerLocation);
        });
    }
}
