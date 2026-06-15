### It's rewritten plugin based on [Pathways](https://modrinth.com/plugin/pathways)

### Plugin adds a touch of realism, now each player leaves a trail, trampling the grass.

### Fully configurable
```yaml
# distance between each try to replace block material
"distance": 3.0
# time in ticks when plugin checks
"timeUpdate": 20
# chance to replace block material in percent
"chance": 30

# material that will be replaced: [materials that will be chosen (on random one of them)]
"materials": {
  "GRASS_BLOCK": ["DIRT", "DIRT_PATH"],
  "DIRT_PATH": ["DIRT"],
  "DIRT": ["COARSE_DIRT"]
}
```

### Command `/pathwaysb reload` for reloading the plugin