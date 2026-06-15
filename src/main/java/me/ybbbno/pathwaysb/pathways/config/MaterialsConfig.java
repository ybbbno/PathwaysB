package me.ybbbno.pathwaysb.pathways.config;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialsConfig {
    private final Map<Material, List<Material>> materials = new HashMap<>();

    public void addMaterial(Material from, List<Material> to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Material and list cannot be null");
        }
        materials.put(from, to);
    }

    public Material getFromMaterial(Material to) {
        if (to == null) {
            return null;
        }

        for (Map.Entry<Material, List<Material>> entry : materials.entrySet()) {
            if (entry.getValue().contains(to)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public List<Material> getToMaterials(Material from) {
        if (from == null) {
            return List.of();
        }

        List<Material> toMaterials = materials.get(from);
        return toMaterials != null ? toMaterials : List.of();
    }

    public boolean hasMaterial(Material from) {
        return from != null && materials.containsKey(from);
    }

    public void clearAllMaterials() {
        materials.clear();
    }
}
