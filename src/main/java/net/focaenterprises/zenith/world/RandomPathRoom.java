package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.game.IGameContext;
import net.focaenterprises.zenith.world.tilemap.TileMap;

import java.util.Random;

public class RandomPathRoom extends Room {
  public RandomPathRoom(int width, int height, int tileSize, IGameContext context, int pathSize) {
    super(width, height, tileSize, context);

    TileMap tileMap = getTileMap();
    Random random = new Random();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        tileMap.setTileType(x, y, 1);
      }
    }

    boolean nextDir = false;
    int dir = 0;
    int x = width / 2;
    int y = height / 2;

    for (int i = 0; i < pathSize; i++) {
      if (random.nextInt(100) < 25) nextDir = true;
      if(nextDir) dir = random.nextInt(4);

      switch (dir) {
        case 0 -> x++;
        case 1 -> x--;
        case 2 -> y++;
        case 3 -> y--;
      }

      nextDir = false;

      if(x < 0) {
        x = 0;
        nextDir = true;
      }
      if(x >= width) {
        x = width -1;
        nextDir = true;
      }
      if(y < 0) {
        y = 0;
        nextDir = true;
      }
      if(y >= height) {
        y = height -1;
        nextDir = true;
      }

      tileMap.setTileType(x, y, 2);
    }
  }
}
