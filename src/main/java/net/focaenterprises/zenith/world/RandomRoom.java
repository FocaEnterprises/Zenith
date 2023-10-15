package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.game.IGameContext;

import java.util.Random;

public class RandomRoom extends Room {
  public RandomRoom(int width, int height, int tileSize, IGameContext context) {
    super(width, height, tileSize, context);

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
