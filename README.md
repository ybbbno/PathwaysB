### It's rewritten plugin based on [Pathways](https://modrinth.com/plugin/pathways)

Why? It simply fixes the distance check. In the original plugin, this didn't work with teleportation between worlds, and attempts to replace a block's material occurred within the "distance" parameter, not beyond it.

### Plugin adds a touch of realism, now each player leaves a trail, trampling the grass.

### Fully configurable
```yaml
# distance between each try to replace block's material
"distance": 3.0
# time in ticks when plugin checks
"timeUpdate": 20
# chance to replace block's material in percent
"chance": 30

# material that will be replaced: [materials that will be chosen (on random one of them)]
"materials": {
  "GRASS_BLOCK": ["DIRT", "DIRT_PATH"],
  "DIRT_PATH": ["DIRT"],
  "DIRT": ["COARSE_DIRT"]
}
```

### Command `/pathwaysb reload` for reloading the plugin