package me.ybbbno.pathwaysb.pathways.config;

import me.deadybbb.ybmj.BasicConfigHandler;
import me.deadybbb.ybmj.PluginProvider;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class PathwaysConfigManager extends BasicConfigHandler {
    public PathwaysConfigManager(PluginProvider plugin) {
        super(plugin, "config.json");
    }

    public PathwaysConfig getConfig() {
        reloadConfig();

        double distance = config.getDouble("distance", 3.0);
        int timeUpdate = config.getInt("timeUpdate", 20);
        int chance = config.getInt("chance", 30);
        MaterialsConfig materialsDefault = new MaterialsConfig();

        materialsDefault.addMaterial(Material.GRASS_BLOCK, List.of(Material.DIRT, Material.DIRT_PATH));
        materialsDefault.addMaterial(Material.DIRT_PATH, List.of(Material.DIRT));
        materialsDefault.addMaterial(Material.DIRT, List.of(Material.COARSE_DIRT));

        ConfigurationSection materialsSection = config.getConfigurationSection("materials");
        if (materialsSection == null) {
            return new PathwaysConfig(distance, timeUpdate, chance, materialsDefault);
        }

        MaterialsConfig materials = new MaterialsConfig();

        Set<String> keys = materialsSection.getKeys(false);
        for (String fromKey : keys) {
            Material fromMaterial;
            try {
                fromMaterial = Material.valueOf(fromKey);
            } catch (IllegalArgumentException ignored) {
                plugin.logger.severe(fromKey + " is incorrect material");
                continue;
            }

            List<?> toList = materialsSection.getList(fromKey);
            if (toList == null || toList.isEmpty()) {
                continue;
            }

            List<Material> toMaterials = new ArrayList<>();
            for (Object toObj : toList) {
                if (!(toObj instanceof String)) {
                    plugin.logger.severe(toObj + " in " + fromKey + " is not string");
                    continue;
                }

                try {
                    Material toMaterial = Material.valueOf((String) toObj);
                    toMaterials.add(toMaterial);
                } catch (IllegalArgumentException ignored) {
                    plugin.logger.severe(toObj + " in " + fromKey + " is incorrect material");
                }
            }

            if (!toMaterials.isEmpty()) {
                materials.addMaterial(fromMaterial, toMaterials);
            }
        }

        return new PathwaysConfig(distance, timeUpdate, chance, materials);
    }
}
