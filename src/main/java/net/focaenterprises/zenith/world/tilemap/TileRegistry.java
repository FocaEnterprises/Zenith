package net.focaenterprises.zenith.world.tilemap;

import java.util.HashMap;
import java.util.Map;

public class TileRegistry {
  public static final Map<Integer, TileType> TILE_TYPES = new HashMap<>();

  public static final TileType GRASS = newTileType("grass", false);
  public static final TileType DIRT = newTileType("dirt", false);
  public static final TileType STONE = newTileType("stone", true);

  public static TileType newTileType(String name, boolean isSolid) {
    TileType type = new TileType(name, isSolid);
    TILE_TYPES.put(TILE_TYPES.size(), type);

    return type;
  }

  public static TileType getTileType(int index) {
    if (index - 1 < 0 || index - 1 > TILE_TYPES.size()) {
      return null;
    }

    return TILE_TYPES.get(index - 1);
  }

  public static int getTileTypeId(TileType tileType) {
    for (Map.Entry<Integer, TileType> entry : TILE_TYPES.entrySet()) {
      if (entry.getValue().equals(tileType)) {
        return entry.getKey();
      }
    }

    return 0;
  }
}
