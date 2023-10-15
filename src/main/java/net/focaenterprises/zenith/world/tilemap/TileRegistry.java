package net.focaenterprises.zenith.world.tilemap;

import net.focaenterprises.zenith.graphics.Sprite;

import java.util.HashMap;
import java.util.Map;

public class TileRegistry {
  private static final Map<Integer, TileType> tileTypes = new HashMap<>();

  public static void newTileType(Sprite sprite, boolean isSolid) {
    tileTypes.put(tileTypes.size(), new TileType(sprite, isSolid));
  }

  public static TileType getTileType(int index) {
    if (index - 1 < 0 || index - 1 > tileTypes.size()) {
      return null;
    }

    return tileTypes.get(index - 1);
  }

  public static int getTileTypeId(TileType tileType) {
    for (Map.Entry<Integer, TileType> entry : tileTypes.entrySet()) {
      if (entry.getValue().equals(tileType)) {
        return entry.getKey();
      }
    }

    return 0;
  }
}
