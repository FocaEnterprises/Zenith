package net.focaenterprises.zenith.world;

import java.util.Random;

public class RandomRoom extends Room {
  public RandomRoom(int width, int height, int tileSize) {
    super(width, height, tileSize);

    Random random = new Random();
    for (int x = 0; x < this.getTileMap().getWidth(); x++) {
      for (int y = 0; y < this.getTileMap().getHeight(); y++) {
        if (random.nextInt(100) > 11) {
          this.getTileMap().setTileType(x, y, 1);
        } else {
          this.getTileMap().setTileType(x, y, 2);
        }
      }
    }
  }
}
