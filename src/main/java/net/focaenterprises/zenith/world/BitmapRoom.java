package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.game.IGameContext;
import net.focaenterprises.zenith.world.tilemap.TileMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BitmapRoom extends Room {
  public BitmapRoom(int width, int height, int tileSize, IGameContext context, String roomName) {
    super(width, height, tileSize, context);

    BufferedImage image;

    try {
      image = ImageIO.read(getClass().getResource("/" + roomName + ".png"));
    } catch (IOException e) {
      throw new IllegalArgumentException("Image does not exists");
    }

    generate(image);
  }

  private void generate(BufferedImage bitmap) {
    TileMap tileMap = this.getTileMap();

    for(int x = 0; x < tileMap.getWidth(); x++) {
      for (int y = 0; y < tileMap.getHeight(); y++) {
        int type = getTileType(bitmap.getRGB(x, y));
        tileMap.setTileType(x, y, type);
      }
    }
  }

  private int getTileType(int rgb) {
    return switch (rgb) {
      case 0xFFFFFFFF -> 2;
      case 0xFF808080 -> 3;
      default -> 1;
    };
  }
}
