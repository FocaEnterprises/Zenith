package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.game.IGameContext;
import net.focaenterprises.zenith.world.tilemap.TileMap;

import java.util.Random;

public class RandomRoom extends Room {
  public RandomRoom(int width, int height, int tileSize, IGameContext context) {
    super(width, height, tileSize, context);

    Random random = new Random();
    TileMap tileMap = this.getTileMap();

    for (int x = 0; x < tileMap.getWidth(); x++) {
      for (int y = 0; y < tileMap.getHeight(); y++) {
        int chance = random.nextInt(100);

        if (chance > 15) {
          tileMap.setTileType(x, y, 1);
        } else if (chance > 10){
          tileMap.setTileType(x, y, 2);
        } else {
          tileMap.setTileType(x, y, 3);
        }
      }
    }
  }
}
